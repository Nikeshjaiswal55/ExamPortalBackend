package examportal.portal.ServicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import examportal.portal.Entity.AttemptedQuestions;
import examportal.portal.Entity.Cheating;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Questions;
import examportal.portal.Entity.Result;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.PaperDto;
import examportal.portal.Payloads.ResultDto;
import examportal.portal.Payloads.checkpaperDto;
import examportal.portal.Repo.AttemptedQuestionsRepo;
import examportal.portal.Repo.CheatingRepo;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.Repo.ResultRepo;
import examportal.portal.Services.ResultService;
import java.util.Date;

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

    Logger log = LoggerFactory.getLogger("ResultServiceImpl.class");

    @Override
    public ResultDto createResult(ResultDto result) {
        log.info("ResultServiceImpl, createResult Method Start");

        List<Questions> questions = result.getQuestions();

        List<Questions> attemptQuestions = new ArrayList<>();

        for (Questions questions2 : questions) {

            AttemptedQuestions attemptedQuestions = new AttemptedQuestions();
            // this.mapper.map(result, AttemptedQuestions.class);
            attemptedQuestions.setCorrectAns(questions2.getCorrectAns());
            attemptedQuestions.setOptions(questions2.getOptions());
            attemptedQuestions.setQuestions(questions2.getQuestions());
            attemptedQuestions.setPaperID(result.getPaperID());
            attemptedQuestions.setStudentID(result.getStudentID());

            AttemptedQuestions question = this.attemptedQuestionsRepo.save(attemptedQuestions);

            attemptQuestions.add(questions2);

        }

        ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(result.getPaperID());
        examDetails.setPaperChecked(true);
        ExamDetails updatecheck = this.examDetailsRepo.save(examDetails);

        Result newResult = new Result();
        newResult.setPaperID(result.getPaperID());
        newResult.setStudentID(result.getStudentID());
        Result savedResult = this.resultRepo.save(newResult);

        Cheating cheating = this.cheatingRepo.save(result.getCheating());

        ResultDto dto = new ResultDto();
        dto.setQuestions(attemptQuestions);
        dto.setPaperID(result.getPaperID());
        dto.setStudentID(result.getStudentID());
        dto.setResultID(savedResult.getResultID());
        dto.setCheating(cheating);
        log.info("ResultServiceImpl, createResult Method Ends");

        return dto;
    }

    @Override
    public ResultDto getResultByResultId(String resultID) {
        log.info("ResultServiceImpl, createResult Method Start");

        Result result = this.resultRepo.findById(resultID)
                .orElseThrow(() -> new ResourceNotFoundException("result ", "Result Id", resultID));

        List<AttemptedQuestions> questions2 = this.attemptedQuestionsRepo
                .getAllQuestionsByStudentID(result.getStudentID());

        List<Questions> questions = new ArrayList<>();

        for (AttemptedQuestions attemptedQuestions : questions2) {
            Questions question = this.mapper.map(attemptedQuestions, Questions.class);
            questions.add(question);

        }

        ResultDto dto = new ResultDto();

        dto.setQuestions(questions);
        dto.setPaperID(result.getPaperID());
        dto.setResultID(result.getResultID());
        dto.setStudentID(result.getStudentID());
        log.info("ResultServiceImpl, createResult Method Ends");

        return dto;

    }

    @Override
    public ResultDto checkPaper(checkpaperDto dto) {
        int count = 0;
        float percentage;
        int eachqMarks = 0;
        Date date = new Date();
        List<Questions> questions2 = new ArrayList();
        for (Questions ques : dto.getQuestions()) {
            Questions q = this.questionsRepo.findById(ques.getQuestionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Question", "QuestionId", ques.getQuestionId()));
            if (q.getCorrectAns().equals(ques.getUserAns())) {
                count++;
                q.setUserAns(ques.getUserAns());
                questions2.add(q);
            }
            questions2.add(ques);
        }
        ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(dto.getPaperId());
        eachqMarks = (examDetails.getTotalMarks()) / dto.getQuestions().size();

        percentage = (count / examDetails.getTotalMarks()) * 100;

        if (count > examDetails.getMinimum_marks()) {
           dto.setResultstatus("pass");
        } else {
         dto.setResultstatus("fail");
        }

        ResultDto dto2 = new ResultDto();
        dto2.setMarks(count);
        dto2.setPaperID(dto.getPaperId());
        dto2.setPercentage(percentage);
        dto2.setQuestions(questions2);
        dto2.setStudentID(dto.getStudentId());
        dto2.setResult(dto.getResultstatus());
        dto2.setCheating(dto.getCheating());
        ResultDto newresult = createResult(dto2);

        return newresult;
    }

}
