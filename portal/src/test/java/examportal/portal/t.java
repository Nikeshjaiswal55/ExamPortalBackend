package examportal.portal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Repo.AttemptepaperRepo;


@SpringBootTest(classes=t.class)
public class t {

    @Mock
    private AttemptepaperRepo attemptedPapersRepo;

    @Test
    public void testGetAttemptedStudentsByPaperId() {
        // Arrange
        String paperId = "paper123";
        AttemptedPapers mockAttemptedPapers = new AttemptedPapers();
        // Set properties of mockAttemptedPapers based on your requirements

        // Mocking the behavior of the repository method
        when(attemptedPapersRepo.GetattemptedStudentsByPaperId(eq(paperId))).thenReturn(mockAttemptedPapers);

        // Act
        AttemptedPapers result = attemptedPapersRepo.GetattemptedStudentsByPaperId(paperId);

        // Assert
        assertEquals(mockAttemptedPapers, result);
    }

    @Test
    public void testGetAllAttemptedPaperByStudentID() {
        // Arrange
        String studentId = "student123";
        String paperId = "paper123";
        AttemptedPapers mockAttemptedPapers = new AttemptedPapers();
        // Set properties of mockAttemptedPapers based on your requirements

        // Mocking the behavior of the repository method
        when(attemptedPapersRepo.getAllAttemptedPaperbyStudentID(eq(studentId), eq(paperId))).thenReturn(mockAttemptedPapers);

        // Act
        AttemptedPapers result = attemptedPapersRepo.getAllAttemptedPaperbyStudentID(studentId, paperId);

        // Assert
        assertEquals(mockAttemptedPapers, result);
    }

    // You can add more test cases as needed for other methods or scenarios
}