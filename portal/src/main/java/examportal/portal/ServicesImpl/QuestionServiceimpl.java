package examportal.portal.ServicesImpl;

import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Questions;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.Services.QuestionService;
@Service
public class QuestionServiceimpl implements QuestionService  {

    @Autowired
    private QuestionsRepo questionsRepo;

    Logger log = LoggerFactory.getLogger("QuestionServiceImpl");

    @Override
    public Questions createQuestions(Questions questions) {
      log.info("Question Service Create Question Method Starts");

     Questions newQuestions = this.questionsRepo.save(questions);

       log.info("Question Service Create Question Method End's");

     return newQuestions;
    }

    @Override
    public List<Questions> updateQuestions(List<Questions> questions) {

       log.info("Question Service Update Question Method Starts");
       List<Questions> qList = new ArrayList<>();
      for (Questions updateq : questions) {
         
       Questions updateqQuestions = this.questionsRepo.findById(updateq.getQuestionId()).orElseThrow(()-> new ResourceNotFoundException("Question ", "questionId", updateq.getQuestionId()));
       updateqQuestions = updateq;
       Questions update = this.questionsRepo.save(updateqQuestions);
       qList.add(update);
      }
       log.info("Question Service Update Question Method End's");

       return qList;
    }


    @Override
    public List<Questions> getAllQuestionsById(String paperId) {

       log.info("Question Service getAllQuestionsByID Question Method Starts");
      
        List<Questions> questions = this.questionsRepo.getAllQuestionsByPaperId(paperId);

       log.info("Question Service getAllQuestionByID Method End's");

        return questions;
    }

    @Override
    public Questions getQuestionsByID(String questionId) {

      log.info("Question Service Create getQuestionById Method Starts");

       Questions questions = this.questionsRepo.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("Question", "QuestionID", questionId));

      log.info("Question Service Create getQuesionById Method End's");

       return questions;
    }

    @Override
    public String deleteQuestion(String QuestionID) {
       log.info("Question Service deleteQuestion Method Starts");

       Questions questions = this.questionsRepo.findById(QuestionID).orElseThrow(()-> new ResourceNotFoundException("Question", "QuestionID", QuestionID));
       this.questionsRepo.delete(questions);

       log.info("Question Service Create deleteQuestion Method End");

       return "deleted Successfully";
    }

  
}
