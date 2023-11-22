package examportal.portal.ServicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.AttemptedQuestions;
import examportal.portal.Entity.Cheating;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Questions;
import examportal.portal.Entity.Result;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.ResultDto;
import examportal.portal.Repo.AttemptedQuestionsRepo;
import examportal.portal.Repo.CheatingRepo;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.ResultRepo;
import examportal.portal.Services.ResultService;

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

    @Override
    public ResultDto createResult(ResultDto result) {
    

        List<Questions> questions = result.getQuestions();
        System.out.println("My Quesetions =============================="+result.getQuestions());

        List<Questions> attemptQuestions = new ArrayList<>();

        for (Questions questions2 : questions) {

            AttemptedQuestions attemptedQuestions = new AttemptedQuestions();
            //  this.mapper.map(result, AttemptedQuestions.class);
            attemptedQuestions.setCorrectAns(questions2.getCorrectAns());
            attemptedQuestions.setOptions(questions2.getOptions());
            attemptedQuestions.setQuestions(questions2.getQuestions());
            attemptedQuestions.setPaperID(result.getPaperID());
            attemptedQuestions.setStudentID(result.getStudentID());

            AttemptedQuestions question = this.attemptedQuestionsRepo.save(attemptedQuestions);
            System.out.println(question);

            attemptQuestions.add(questions2);

        }

        ExamDetails examDetails = this.examDetailsRepo.getexExamDetailsByPaperID(result.getPaperID());
        examDetails.setPaperChecked(true);
        ExamDetails updatecheck = this.examDetailsRepo.save(examDetails);
        System.out.println(updatecheck);

         Result newResult = new Result();
        newResult.setPaperID(result.getPaperID());
        newResult.setStudentID(result.getStudentID());
        Result savedResult = this.resultRepo.save(newResult);

        Cheating cheating = new Cheating();
        cheating.setImages(result.getCheating().getImages());
        cheating.setAudios(result.getCheating().getAudios());
        cheating.setStudentId(result.getStudentID());
        cheating.setResultId(savedResult.getResultID());
        Cheating savedCheating = this.cheatingRepo.save(cheating);

        ResultDto dto = new ResultDto();
        dto.setQuestions(attemptQuestions);
        dto.setPaperID(result.getPaperID());
        dto.setStudentID(result.getStudentID());
        dto.setResultID(savedResult.getResultID());
        dto.setCheating(savedCheating);

        return dto;
    }

    @Override
    public ResultDto getResultByResultId(String resultID) {

        Result result = this.resultRepo.findById(resultID).orElseThrow(()-> new ResourceNotFoundException("result ", "Result Id", resultID));

        List<AttemptedQuestions>  questions2 = this.attemptedQuestionsRepo.getAllQuestionsByStudentID(result.getStudentID());

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

        return dto;

    }

}

