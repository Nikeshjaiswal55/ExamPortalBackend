package examportal.portal.repotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.Result;
import examportal.portal.Repo.ResultRepo;

@SpringBootTest(classes= ResultRepoTest.class)
public class ResultRepoTest {

    @Mock
    private ResultRepo resultRepo;

    @Test
    public void testFindAllByPaperIdOrderByPercentageDesc() {
        // Arrange
        String paperId = "123";
        List<Result> mockResults = new ArrayList<>();
        // Add mock results to the list based on your requirements

        // Mocking the behavior of the repository method
        when(resultRepo.findAllByPaperIdOrderByPercentageDesc(eq(paperId))).thenReturn(mockResults);

        // Act
        List<Result> result = resultRepo.findAllByPaperIdOrderByPercentageDesc(paperId);

        // Assert
        assertEquals(mockResults, result);
    }

    @Test
    public void testFindAllResutlByStudentID() {
        // Arrange
        String studentId = "456";
        List<Result> mockResults = new ArrayList<>();
        // Add mock results to the list based on your requirements

        // Mocking the behavior of the repository method
        when(resultRepo.findAllResutlByStudentID(eq(studentId))).thenReturn(mockResults);

        // Act
        List<Result> result = resultRepo.findAllResutlByStudentID(studentId);

        // Assert
        assertEquals(mockResults, result);
    }

    @Test
    public void testGetResultByStudentAndPaperId() {
        // Arrange
        String paperId = "123";
        String studentId = "456";
        Result mockResult = new Result();
        // Set properties of mockResult based on your requirements

        // Mocking the behavior of the repository method
        when(resultRepo.getResultByStudentAndPaperId(eq(paperId), eq(studentId))).thenReturn(mockResult);

        // Act
        Result result = resultRepo.getResultByStudentAndPaperId(paperId, studentId);

        // Assert
        assertEquals(mockResult, result);
    }

    // You can add more test cases as needed for other methods or scenarios
}

