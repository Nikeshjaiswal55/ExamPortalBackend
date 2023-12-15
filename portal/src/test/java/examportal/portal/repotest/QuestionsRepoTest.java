package examportal.portal.repotest;
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
        // Arrange
        String paperId = "123";
        List<Questions> mockQuestions = new ArrayList<>();
        // Add mock questions to the list based on your requirements

        // Mocking the behavior of the repository method
        when(questionsRepo.getAllQuestionsByPaperId(eq(paperId))).thenReturn(mockQuestions);

        // Act
        List<Questions> result = questionsRepo.getAllQuestionsByPaperId(paperId);

        // Assert
        assertEquals(mockQuestions, result);
    }

    @Test
    public void testGetAllQuestionsByName() {
        // Arrange
        String name = "Sample Question";
        List<Questions> mockQuestions = new ArrayList<>();
        // Add mock questions to the list based on your requirements

        // Mocking the behavior of the repository method
        when(questionsRepo.getAllQuestionsByName(eq(name))).thenReturn(mockQuestions);

        // Act
        List<Questions> result = questionsRepo.getAllQuestionsByName(name);

        // Assert
        assertEquals(mockQuestions, result);
    }

    // You can add more test cases as needed for other methods or scenarios
}
