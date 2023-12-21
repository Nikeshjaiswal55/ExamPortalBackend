package examportal.portal.RepoTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.Questions;
import examportal.portal.Repo.QuestionsRepo;

@SpringBootTest(classes=QuestionsRepoTest.class)
public class QuestionsRepoTest {

    @Mock
    private QuestionsRepo questionsRepo;

    @Test
    public void testGetAllQuestionsByPaperId() {
        
        String paperId = "123";
        List<Questions> mockQuestions = new ArrayList<>();
      
        when(questionsRepo.getAllQuestionsByPaperId(eq(paperId))).thenReturn(mockQuestions);

    
        List<Questions> result = questionsRepo.getAllQuestionsByPaperId(paperId);

   
        assertEquals(mockQuestions, result);
    }

    

  
}
