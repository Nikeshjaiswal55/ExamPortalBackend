package examportal.portal.RepoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import examportal.portal.Entity.AttemptedQuestions;
import examportal.portal.Repo.AttemptedQuestionsRepo;


@SpringBootTest(classes=AttemptedQuestionsRepoTest .class)
public class AttemptedQuestionsRepoTest {

    @Mock
    private AttemptedQuestionsRepo attemptedQuestionsRepo;

    @Test
    public void testGetAllQuestionsByStudentID() {
       
        String studentID = "student123";
        String paperID = "paper123";
        List<AttemptedQuestions> mockAttemptedQuestions = new ArrayList<>();
       
        when(attemptedQuestionsRepo.getAllQuestionsByStudentID(eq(studentID), eq(paperID))).thenReturn(mockAttemptedQuestions);

        List<AttemptedQuestions> result = attemptedQuestionsRepo.getAllQuestionsByStudentID(studentID, paperID);

      
        assertEquals(mockAttemptedQuestions, result);
    }

}
