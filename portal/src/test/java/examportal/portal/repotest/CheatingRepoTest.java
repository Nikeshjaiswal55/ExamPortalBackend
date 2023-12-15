package examportal.portal.repotest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.Cheating;
import examportal.portal.Repo.CheatingRepo;

@SpringBootTest(classes=CheatingRepoTest.class)
public class CheatingRepoTest {

    @Mock
    private CheatingRepo cheatingRepo;

    @Test
    public void testGetCheatingByStudentAndPaperId() {
        // Arrange
        String studentId = "123";
        String paperId = "456";
        Cheating mockCheating = new Cheating();
        // Set properties of mockCheating based on your requirements

        // Mocking the behavior of the repository method
        when(cheatingRepo.getCheatingByStudentAndPaperId(eq(studentId), eq(paperId))).thenReturn(mockCheating);

        // Act
        Cheating result = cheatingRepo.getCheatingByStudentAndPaperId(studentId, paperId);

        // Assert
        assertEquals(mockCheating, result);
    }

    // You can add more test cases as needed for other methods or scenarios
}
