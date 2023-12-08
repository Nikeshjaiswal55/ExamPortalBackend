package examportal.portal.ServicesImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import examportal.portal.Entity.AttemptedQuestions;
import examportal.portal.Entity.Cheating;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Questions;
import examportal.portal.Entity.Result;
import examportal.portal.Entity.Student;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.ResultDto;
import examportal.portal.Payloads.checkpaperDto;
import examportal.portal.Repo.AttemptedQuestionsRepo;
import examportal.portal.Repo.CheatingRepo;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.Repo.ResultRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Services.ResultService;
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

    Logger log = LoggerFactory.getLogger("ResultServiceImpl.class");

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
                    attemptedQuestions.setQuestions(question.getQuestion());
                    attemptedQuestions.setPaperID(dto.getResult().getPaperID());
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
        examDetails.set_Active(true);
        examDetails.set_Setup(false);
        examDetails.set_attempted(true);
        this.examDetailsRepo.save(examDetails);

        // 3. Save Result
        Result newResult = new Result();
        newResult.setStudentID(dto.getResult().getStudentID());
        this.resultRepo.save(newResult);

        // 4. Save Cheating
        Cheating cheating = new Cheating();
        cheating.setAudios(dto.getCheating().getAudios());
        cheating.setImages(dto.getCheating().getImages());
        cheating.setResultId(newResult.getResultID());
        cheating.setStudentId(newResult.getStudentID());
        Cheating stdCheating = this.cheatingRepo.save(cheating);

        // 5. Build ResultDto
        ResultDto resultDto = new ResultDto();
        resultDto.setQuestions(questions);
        resultDto.setResultID(newResult.getResultID());
        resultDto.setCheating(stdCheating);
        resultDto.setResult(newResult);

        log.info("ResultServiceImpl, createResult Method Ends");

        return resultDto;
    }

    // @Override
    // public ResultDto createResult(ResultDto dto) {
    // log.info("ResultServiceImpl, createResult Method Start");

    // List<Questions> questions = dto.getQuestions();

    // List<Questions> attemptQuestions = new ArrayList<>();

    // for (Questions questions2 : questions) {

    // AttemptedQuestions attemptedQuestions = new AttemptedQuestions();
    // // this.mapper.map(result, AttemptedQuestions.class);
    // attemptedQuestions.setCorrectAns(questions2.getCorrectAns());
    // attemptedQuestions.setOptions(questions2.getOptions());
    // attemptedQuestions.setQuestions(questions2.getQuestion());
    // attemptedQuestions.setPaperID(dto.getResult().getPaperID());
    // attemptedQuestions.setStudentID(dto.getResult().getStudentID());
    // attemptedQuestions.setCorrectAns(questions2.getCorrectAns());

    // AttemptedQuestions question =
    // this.attemptedQuestionsRepo.save(attemptedQuestions);
    // Questions att = this.mapper.map(question, Questions.class);
    // attemptQuestions.add(att);
    // }

    // ExamDetails examDetails =
    // this.examDetailsRepo.getExamDetailsByPaperID(dto.getResult().getPaperID());
    // examDetails.setPaperChecked(true);
    // ExamDetails updatecheck = this.examDetailsRepo.save(examDetails);

    // Result newResult = this.resultRepo.save(dto.getResult());

    // Cheating cheating = new Cheating();
    // cheating.setAudios(dto.getCheating().getAudios());
    // cheating.setImages(dto.getCheating().getImages());
    // cheating.setResultId(newResult.getResultID());
    // cheating.setStudentId(newResult.getStudentID());
    // Cheating stdcheating = this.cheatingRepo.save(cheating);

    // ResultDto resultDto = new ResultDto();
    // resultDto.setQuestions(attemptQuestions);
    // resultDto.setResultID(newResult.getResultID());
    // resultDto.setCheating(stdcheating);
    // resultDto.setResult(newResult);
    // log.info("ResultServiceImpl, createResult Method Ends");

    // return resultDto;
    // }

    @Override
    public ResultDto getResultByStudentAndPaperId(String resultID) {
        log.info("ResultServiceImpl, createResult Method Start");

        Result result = this.resultRepo.findById(resultID)
                .orElseThrow(() -> new ResourceNotFoundException("result ", "Result Id", resultID));

        List<Questions> questions = new ArrayList();

        List<AttemptedQuestions> questions2 = this.attemptedQuestionsRepo
                .getAllQuestionsByStudentID(result.getStudentID(), result.getPaperID());

                for (AttemptedQuestions attemptedQuestions : questions2) {
                    Questions q = this.mapper.map(attemptedQuestions, Questions.class);
                    questions.add(q);
                }
        ResultDto dto = new ResultDto();
        dto.setQuestions(questions);
        dto.setResultID(result.getResultID());
        log.info("ResultServiceImpl, createResult Method Ends");

        return dto;

    }

    @Override
    public ResultDto checkPaper(checkpaperDto dto) {

        int obtainmarks = 0;
        float percentage = 0;

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = date.format(formatter);

        List<Questions> questions2 = new ArrayList();

        ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(dto.getPaperId());

        int eachqMarks = (examDetails.getTotalMarks() / dto.getQuestions().size());

        for (Questions ques : dto.getQuestions()) {
            Questions q = this.questionsRepo.findById(ques.getQuestionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Question", "QuestionId", ques.getQuestionId()));
            if (q.getCorrectAns().equals(ques.getUserAns())) {
                obtainmarks += eachqMarks;
                q.setUserAns(ques.getUserAns());
                questions2.add(q);
            }
            questions2.add(ques);
        }

        percentage = ((float) obtainmarks / (float) examDetails.getTotalMarks()) * 100;

        if (obtainmarks > examDetails.getMinimum_marks()) {
            dto.setResultstatus("pass");
        } else {
            dto.setResultstatus("fail");
        }

        Result newResult = new Result();
        newResult.setPaperID(dto.getPaperId());
        newResult.setStudentID(dto.getStudentId());
        newResult.setDate(formattedDate);
        newResult.setMarks(obtainmarks);
        newResult.setResultStatus(dto.getResultstatus());
        newResult.setPercentage(percentage);

        ResultDto dto2 = new ResultDto();
        dto2.setQuestions(questions2);
        dto2.setCheating(dto.getCheating());
        dto2.setResult(newResult);
        System.out.println("my  dt0 --=---========-" + dto2);
        ResultDto paperrResultDto = createResult(dto2);

        return paperrResultDto;
    }

    @Override
    public List<Student> getTopThreeStudentByPaper(String paperId) {

        List<Result> results = this.resultRepo.findAllByPaperIdOrderByPercentageDesc(paperId);
        List<Student> TopThree = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Result resu = results.get(i);
            Student student = this.studentRepo.findById(resu.getStudentID())
                    .orElseThrow(() -> new ResourceNotFoundException("Student", "StudentId", resu.getStudentID()));
            TopThree.add(student);
        }

        return TopThree;
    }

}
