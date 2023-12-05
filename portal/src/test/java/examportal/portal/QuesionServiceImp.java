package examportal.portal;


import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import examportal.portal.Entity.Questions;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.ServicesImpl.QuestionServiceimpl;

// ... (imports)
  @ExtendWith(SpringExtension.class)
  @SpringBootTest(classes = QuesionServiceImp.class)
  
public class QuesionServiceImp {

    // ... (other methods)

     
    @Mock
    private QuestionsRepo questionsRepo;

    @InjectMocks
    private QuestionServiceimpl questionService;





    private Questions createSampleQuestion() {
        Questions question = new Questions();
        question.setQuestionId("1");  // Set a specific question ID for testing
        question.setOptions(Arrays.asList("Option A", "Option B", "Option C"));
        question.setQuestions("what is the  capital of france?");
        question.setCorrectAns("Option A");
        question.setUserAns("Option B");
        question.setPaperID("Exam123");
        return question;
    }

    @Test
    void testCreateQuestions() {
        // Arrange
        Questions mockQuestion = createSampleQuestion();
        // when(questionsRepo.save(any(Questions.class))).thenReturn(mockQuestion);
        when(questionsRepo.save(any(Questions.class))).thenReturn(mockQuestion);

        // Act
        Questions result = questionService.createQuestions(new Questions());

        // Assert

        
         // Adjust this based on your actual test data

        assertEquals("1", result.getQuestionId());
         verify(questionsRepo,times(1)).save(any(Questions.class));
    }

  
//     @Test
// void testUpdateQuestions_ThrowsResourceNotFoundException() {
//     // Arrange
//     Questions inputQuestion = createSampleQuestion(); // Use a valid question for testing
//     when(questionsRepo.findById("1")).thenReturn(Optional.empty()); // Mock the case where no entity is found

//     // Act & Assert
//     ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
//         questionService.updateQuestions(inputQuestion);
//     });

//     // Assert the exception message
//     assertEquals("Question not found with ID: " + inputQuestion.getQuestionId(), exception.getMessage());

//     // Verify that findById was called with the correct ID
//     verify(questionsRepo, times(1)).findById(eq(inputQuestion.getQuestionId()));

//     // Ensure that save is not called when entity is not found
//     verify(questionsRepo, never()).save(any(Questions.class));
// }
    
@Test
void testUpdateQuestions() {
    // Arrange
    Questions mockQuestion = createSampleQuestion();
    when(questionsRepo.findById(anyString())).thenReturn(Optional.of(mockQuestion));
    when(questionsRepo.save(any(Questions.class))).thenReturn(mockQuestion);

    // Act
    Questions result = questionService.updateQuestions(new Questions());

    // Assert
    assertNotNull(result);
    assertEquals("1", result.getQuestionId());  // Adjust this based on your actual test data
    verify(questionsRepo, times(1)).findById(anyString());
    verify(questionsRepo, times(1)).save(any(Questions.class));
}


   
     @Test
     void testDeleteQuestion() {
     // Arrange
       String questionId = "1";
     Questions mockQuestion = createSampleQuestion();
     when(questionsRepo.findById(anyString())).thenReturn(Optional.of(mockQuestion));

     // Act
      String result = questionService.deleteQuestion(questionId);

     // Assert
     assertEquals("deleted Successfully", result);
     verify(questionsRepo, times(1)).findById(questionId);
     verify(questionsRepo, times(1)).delete(mockQuestion);
    }


    @Test
    void testGetAllQuestionsById() {
        // Arrange
        String paperId = "Exam123";
        List<Questions> mockQuestionsList = Arrays.asList(createSampleQuestion(), createSampleQuestion());
        when(questionsRepo.getAllQuestionsByPaperId(anyString())).thenReturn(mockQuestionsList);

        // Act
        List<Questions> result = questionService.getAllQuestionsById(paperId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(questionsRepo, times(1)).getAllQuestionsByPaperId(paperId);
    }
    



    @Test
    void testGetQuestionsByID() {
        // Arrange
        String questionId = "1";
        Questions mockQuestion = new Questions();
        when(questionsRepo.findById(anyString())).thenReturn(Optional.of(mockQuestion));

        // Act
        Questions result = questionService.getQuestionsByID(questionId);

        // Assert
        assertNotNull(result);
        assertEquals(mockQuestion, result);
        verify(questionsRepo, times(1)).findById(questionId);
    }

    }





    
   

