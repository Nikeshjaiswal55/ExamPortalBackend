package examportal.portal.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Questions;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.Services.QuestionService;
import jakarta.el.ELException;
@Service
public class QuestionServiceimpl implements QuestionService  {

    @Autowired
    private QuestionsRepo questionsRepo;

    @Override
    public Questions createQuestions(Questions questions) {
     Questions newQuestions = this.questionsRepo.save(questions);
     return newQuestions;
    }

    @Override
    public Questions updateQuestions(Questions questions) {
       Questions updateqQuestions = this.questionsRepo.findById(questions.getQuestionId()).orElseThrow(()-> new ELException("Question not found"));
       updateqQuestions.setUserAns(questions.getUserAns());
       Questions update = this.questionsRepo.save(updateqQuestions);
       return update;
    }


    @Override
    public List<Questions> getAllQuestion(String paperId) {
      System.out.println("hello==================================================================");
        List<Questions> questions = this.questionsRepo.getAllQuestions(paperId);

        return questions;
    }

    @Override
    public Questions getQuestionsByID(String questionId) {
       Questions questions = this.questionsRepo.findById(questionId).orElseThrow(()-> new ELException("Question not found"));
       return questions;
    }

    @Override
    public String deleteQuestion(String QuestionID) {
       Questions questions = this.questionsRepo.findById(QuestionID).orElseThrow(()-> new ELException("Question Not found"));
       this.questionsRepo.delete(questions);
       return "deleted Successfully";
    }
    
}
