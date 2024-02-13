package examportal.portal.ServicesImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Entity.AttemptedQuestions;
import examportal.portal.Entity.Cheating;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Paper;
import examportal.portal.Entity.Questions;
import examportal.portal.Entity.Result;
import examportal.portal.Entity.Student;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.ResultDto;
import examportal.portal.Payloads.checkpaperDto;
import examportal.portal.Repo.AssessmentRepo;
import examportal.portal.Repo.AttemptedQuestionsRepo;
import examportal.portal.Repo.AttemptepaperRepo;
import examportal.portal.Repo.CheatingRepo;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.PaperRepo;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.Repo.ResultRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Services.ResultService;
import examportal.portal.Services.StorageService;
import jakarta.transaction.Transactional;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private AttemptedQuestionsRepo attemptedQuestionsRepo;

    @Autowired
    private ResultRepo resultRepo;

    @Autowired
    private CheatingRepo cheatingRepo;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ExamDetailsRepo examDetailsRepo;

    @Autowired
    private QuestionsRepo questionsRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private AssessmentRepo assessmentRepo;

    @Autowired
    private AttemptepaperRepo attemptepaperRepo;


    @Autowired
    private StorageService service;

    @Autowired
    private PaperRepo paperRepo;

    Logger log = LoggerFactory.getLogger("ResultServiceImpl");

    @Override
    @Transactional
    public ResultDto createResult(ResultDto dto) {
        log.info("ResultServiceImpl, createResult Method Start");

        // 1. Batch Insertion for AttemptedQuestions
        List<AttemptedQuestions> attemptedQuestionsList = dto.getQuestions().stream()
                .map(question -> {
                    AttemptedQuestions attemptedQuestions = new AttemptedQuestions();
                    attemptedQuestions.setCorrectAns(question.getCorrectAns());
                    attemptedQuestions.setOptions(question.getOptions());
                    attemptedQuestions.setQuestions(question.getQuestions());
                    attemptedQuestions.setPaperID(dto.getResult().getPaperID());

                    attemptedQuestions.setUserAns(question.getUserAns());
                    attemptedQuestions.setStudentID(dto.getResult().getStudentID());
                    return attemptedQuestions;
                })
                .collect(Collectors.toList());

        List<AttemptedQuestions> savedQuestions = this.attemptedQuestionsRepo.saveAll(attemptedQuestionsList);
        List<Questions> questions = new ArrayList();
        for (AttemptedQuestions attemptedQuestions : savedQuestions) {
            Questions question = this.mapper.map(attemptedQuestions, Questions.class);
            questions.add(question);
        }
        // 2. Update ExamDetails
        ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(dto.getResult().getPaperID());
        examDetails.setPaperChecked(true);
        examDetails.setIs_Active("true");
        examDetails.set_Setup(false);
        
        this.examDetailsRepo.save(examDetails);

        // 3. Save Result
        Result newResult = this.resultRepo.save(dto.getResult());

        
        Student s = this.studentRepo.findById(newResult.getStudentID())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "StudentId", newResult.getStudentID()));

        if (s.getTopMarks() < newResult.getMarks()) {
            s.setTopMarks(newResult.getMarks());
            s.setTop_paperId(newResult.getPaperID());
            this.studentRepo.save(s);
        }

        // 4. Save Cheating
        Cheating cheating = dto.getCheating();
        cheating.setPaperId(newResult.getPaperID());
        cheating.setStudentId(newResult.getStudentID());
        cheating.setResultId(newResult.getResultID());
        Cheating stdCheating = this.cheatingRepo.save(cheating);


        // 5. Build ResultDto
        ResultDto resultDto = new ResultDto();
        resultDto.setQuestions(questions);
        resultDto.setResultID(newResult.getResultID());
        resultDto.setCheating(stdCheating);
        resultDto.setResult(newResult);
        resultDto.set_attempted(true);
        log.info("ResultServiceImpl, createResult Method Ends");

        return resultDto;
    }

    @Override
    public ResultDto getResultByStudentAndPaperId(String resultID) {
        log.info("ResultServiceImpl, getResultByStudentAndPaperId Method Start");

        Result result = this.resultRepo.findById(resultID)
                .orElseThrow(() -> new ResourceNotFoundException("result ", "Result Id", resultID));

        List<Questions> questions = new ArrayList();

        List<AttemptedQuestions> questions2 = this.attemptedQuestionsRepo
                .getAllQuestionsByStudentID(result.getStudentID(), result.getPaperID());

        for (AttemptedQuestions attemptedQuestions : questions2) {
            Questions q = this.mapper.map(attemptedQuestions, Questions.class);
            questions.add(q);
        }
        Cheating cheating = this.cheatingRepo.getCheatingByStudentAndPaperId(result.getResultID(), result.getPaperID());

        ResultDto dto = new ResultDto();
        dto.setQuestions(questions);
        dto.setResult(result);
        dto.setResultID(result.getResultID());
        dto.setCheating(cheating);
        dto.set_attempted(true);
        log.info("ResultServiceImpl, getResultByStudentAndPaperId Method Ends");

        return dto;

    }
    // is published ko string me convert krna h

    @Override
    public ResultDto checkPaper(checkpaperDto dto) {
        log.info("ResultServiceImpl, checkPaper Method Start");
        Result r = this.resultRepo.getResultByStudentAndPaperId(dto.getPaperId(), dto.getStudentId());
    
        if (r != null) {
            ResultDto d = new ResultDto();
            d.setResult(r);
            d.set_attempted(true);
            System.out.println("I am here in result ==============");
            return d;
        } else {
            int obtainmarks = 0;
            float percentage = 0;
    
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            StringBuilder formattedDate = new StringBuilder(date.format(formatter));
    
            List<Questions> questions2 = new ArrayList<>();
    
            ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(dto.getPaperId());
            int eachqMarks = examDetails.getTotalMarks() / dto.getQuestions().size();
            System.out.println(dto.getQuestions()+"my alll questions i s herere");
    
            for (Questions ques : dto.getQuestions()) {
                try {
                    Questions q = this.questionsRepo.findById(ques.getQuestionId())
                            .orElseThrow(() -> new ResourceNotFoundException("Question", "QuestionId", ques.getQuestionId()));
    
                    if (q.getCorrectAns().equals(ques.getUserAns())) {
                        obtainmarks += eachqMarks;
                    }
    
                    q.setUserAns(ques.getUserAns());
                    questions2.add(q);
                    System.out.println("Hello, I am checking =============================================");
                } catch (ResourceNotFoundException ex) {
                    System.err.println("Error fetching question with ID: " + ques.getQuestionId());
                    // You might want to consider skipping this question or handling the error in some way
                }
            }
    
            Paper paper = this.paperRepo.findById(dto.getPaperId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paper", "paperId", dto.getPaperId()));
    
            percentage = ((float) obtainmarks / (float) examDetails.getTotalMarks()) * 100;
    
            dto.setResultstatus(obtainmarks > examDetails.getMinimum_marks() ? "pass" : "fail");
    
            Student s = this.studentRepo.findById(dto.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student", "StudentId", dto.getStudentId()));
    
            Result newResult = new Result();
            newResult.setPaperID(dto.getPaperId());
            newResult.setStudentID(dto.getStudentId());
            newResult.setDate(formattedDate.toString());
            newResult.setMarks(obtainmarks);
            newResult.setResultStatus(dto.getResultstatus());
            newResult.setPercentage(percentage);
            newResult.setAssesment_Name(examDetails.getAssessmentName());
            newResult.setStudent_email(s.getEmail());
    
            if ("true".equals(paper.getIs_auto_check())) {
                newResult.setIs_published("approved");
            } else {
                newResult.setIs_published("pending");
            }
    
            Assessment assessment = this.assessmentRepo.getAssessmentByStudentAndpaperId(dto.getStudentId(),
                    dto.getPaperId());
    
            AttemptedPapers attemptedPapers = new AttemptedPapers();
            attemptedPapers.setPaperId(dto.getPaperId());
            attemptedPapers.setStudentId(dto.getStudentId());
            attemptedPapers.set_attempted(true);
            attemptedPapers.setAssmentId(assessment.getAssessmentID());
            this.attemptepaperRepo.save(attemptedPapers);
    
            Cheating cheating = dto.getCheating();
            cheating.setPaperId(dto.getPaperId());
            cheating.setImages(dto.getCheating().getImages());
    
            ResultDto dto2 = new ResultDto();
            dto2.setQuestions(questions2);
            dto2.setCheating(dto.getCheating());
            dto2.setResult(newResult);
            log.info("ResultServiceImpl, checkPaper Method End");
            return createResult(dto2);
        }
    }
    
    @Override
    public List<Student> getTopThreeStudentByPaper(String paperId) {
        log.info("ResultServiceImpl, getTopThreeStudentByPaper Method Start");
        List<Result> results = this.resultRepo.findAllByPaperIdOrderByPercentageDesc(paperId);
        List<Student> TopThree = new ArrayList<>();
        if (results.size() != 3) {
            return null;
        } else {

            for (int i = 0; i < 3; i++) {
                Result resu = results.get(i);
                Student student = this.studentRepo.findById(resu.getStudentID())
                        .orElseThrow(() -> new ResourceNotFoundException("Student", "StudentId", resu.getStudentID()));
                TopThree.add(student);
            }
            log.info("ResultServiceImpl, getTopThreeStudentByPaper Method End");
            return TopThree;
        }

    }

    @Override
    public ResultDto getResultByStudentIdAndPaperId(String papeId, String studentId) {
        log.info("ResultServiceImpl, getResultByStudentIdAndPaperId Method Start");
        Result result = this.resultRepo.getResultByStudentAndPaperId(papeId, studentId);
        if (result==null) {
            return null;
            
        }else{
             Student s = this.studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student", "studentId", studentId));

        if (result.getIs_published().equals("approved")) {
            List<Questions> questions = new ArrayList<>();
            List<AttemptedQuestions> attemptedQuestions = this.attemptedQuestionsRepo
                    .getAllQuestionsByStudentID(studentId, papeId);

            for (AttemptedQuestions attemptedQuestions2 : attemptedQuestions) {
                Questions q = this.mapper.map(attemptedQuestions2, Questions.class);
                questions.add(q);
            }

            ResultDto dto = new ResultDto();
            result.setStudent_email(s.getEmail());
            dto.setResult(result);
            dto.setQuestions(questions);
            dto.setIs_published(result.getIs_published());
            log.info("ResultServiceImpl, getResultByStudentIdAndPaperId Method End");
            return dto;
        } else {

            

            ResultDto dto = new ResultDto();

            dto.setIs_published("requested");
            // dto.setQuestions(questions);
            log.info("ResultServiceImpl, getResultByStudentIdAndPaperId Method End");
            return dto;
        }
        }
       
    }

    @Override
    public List<Result> getTopFiveResultOfStudentByStudentId(String studentId) {
        log.info("ResultServiceImpl, getTopFiveResultOfStudentByStudentId Method Start");
        List<Result> allResult = this.resultRepo.findAllResutlByStudentID(studentId);
        List<Result> top5 = new ArrayList<>();
        int size = allResult.size();
        if (size>15) {
            size=15;
        }

        if (allResult.isEmpty()) {
            
            log.info("ResultServiceImpl, getTopFiveResultOfStudentByStudentId Method End");
            return top5;
        } else {
            for (int i = 0; i <size; i++) {
                Result result = new Result();
                result = allResult.get(i);
                top5.add(result);
            }
            log.info("ResultServiceImpl, getTopFiveResultOfStudentByStudentId Method End");
            return top5;
        }

    }

    @Override
    public ResultDto getAvidenceByStudentIdAndPaperId(String papeId, String studentId) {
        log.info("ResultServiceImpl, getAvidenceByStudentIdAndPaperId Method Start");
        Result result = this.resultRepo.getResultByStudentAndPaperId(papeId, studentId);

        Cheating cheating = this.cheatingRepo.getCheatingByStudentAndPaperId(studentId, papeId);

        ResultDto dto = new ResultDto();
        dto.setResult(result);
        dto.setCheating(cheating);
        // dto.setQuestions(questions);
        log.info("ResultServiceImpl, getAvidenceByStudentIdAndPaperId Method End");
        return dto;
    }

    @Override
    public String publishStudentResult(String studentId, String paperId) {
        log.info("ResultServiceImpl, publishStudentResult Method Start");
        Result result = this.resultRepo.getResultByStudentAndPaperId(paperId, studentId);

        result.setIs_published("approved");
        this.resultRepo.save(result);
        log.info("ResultServiceImpl, publishStudentResult Method End");
        return "Result published  Successfully";
        // }
    }

    @Override
    public String DeactiveStudentResult(String studentId, String paperId) {
        log.info("ResultServiceImpl, DeactiveStudentResult Method Start");
        Result result = this.resultRepo.getResultByStudentAndPaperId(paperId, studentId);
        result.setIs_published("Rejected");
        this.resultRepo.save(result);
        log.info("ResultServiceImpl, DeactiveStudentResult Method End");
        return null;
    }

    @Override
    public List<Result> gettopAssesmentsByOrgnizationId(String orgnizationId) {
        List<Paper> papers = this.paperRepo.getAllPapersByOrgnizationId(orgnizationId);
        
        List<Result> results = new ArrayList<>(5);
        
        for (Paper paper : papers) {

            List<Result> allTopResults= resultRepo.findAllByPaperIdOrderByPercentageDescAndPass(paper.getPaperId());
            if (!allTopResults.isEmpty()) {
                
                results.add(allTopResults.get(0)); 
                if(results.size()==5){
                    return results;
                }
            }
            
        }
        return results;
    }


}
