package examportal.portal.Services;

import examportal.portal.Entity.Questions;
import java.util.List;

public interface QuestionService {

    Questions createQuestions(Questions questions);
    List<Questions> updateQuestions(List<Questions> questions);
    List<Questions> getAllQuestionsById(String paperId);
    Questions getQuestionsByID(String questionId);
    String deleteQuestion(String QuestionID);

    
    
}
