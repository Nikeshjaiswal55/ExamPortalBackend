package examportal.portal.ServiceImpTest;


import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import examportal.portal.Entity.Questions;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.ServicesImpl.QuestionServiceimpl;


  @ExtendWith(SpringExtension.class)
  @SpringBootTest(classes = QuesionServiceImpTest.class)
  
public class QuesionServiceImpTest {


     
    @Mock
    private QuestionsRepo questionsRepo;

    @InjectMocks
    private QuestionServiceimpl questionService;





    private Questions createSampleQuestion() {
        Questions question = new Questions();
        question.setQuestionId("1");  
        question.setOptions(Arrays.asList("Option A", "Option B", "Option C"));
        question.setQuestions("what is the  capital of france?");
        question.setCorrectAns("Option A");
        question.setUserAns("Option B");
        question.setPaperID("Exam123");
        return question;
    }

    @Test
    void testCreateQuestions() {
        
        Questions mockQuestion = createSampleQuestion();
        
        when(questionsRepo.save(any(Questions.class))).thenReturn(mockQuestion);

       
        Questions result = questionService.createQuestions(new Questions());


        assertEquals("1", result.getQuestionId());
         verify(questionsRepo,times(1)).save(any(Questions.class));
    }

  

   
     @Test
     void testDeleteQuestion() {
    
       String questionId = "1";
     Questions mockQuestion = createSampleQuestion();
     when(questionsRepo.findById(anyString())).thenReturn(Optional.of(mockQuestion));

    
      String result = questionService.deleteQuestion(questionId);

     assertEquals("deleted Successfully", result);
     verify(questionsRepo, times(1)).findById(questionId);
     verify(questionsRepo, times(1)).delete(mockQuestion);
    }


    @Test
    void testGetAllQuestionsById() {
  
        String paperId = "Exam123";
        List<Questions> mockQuestionsList = Arrays.asList(createSampleQuestion(), createSampleQuestion());
        when(questionsRepo.getAllQuestionsByPaperId(anyString())).thenReturn(mockQuestionsList);

 
        List<Questions> result = questionService.getAllQuestionsById(paperId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(questionsRepo, times(1)).getAllQuestionsByPaperId(paperId);
    }
    



    @Test
    void testGetQuestionsByID() {
        
        String questionId = "1";
        Questions mockQuestion = new Questions();
        when(questionsRepo.findById(anyString())).thenReturn(Optional.of(mockQuestion));

        Questions result = questionService.getQuestionsByID(questionId);

 
        assertNotNull(result);
        assertEquals(mockQuestion, result);
        verify(questionsRepo, times(1)).findById(questionId);
    }

    }





    
   

