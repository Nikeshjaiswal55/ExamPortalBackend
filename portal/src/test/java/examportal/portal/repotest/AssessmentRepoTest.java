package examportal.portal.repotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import examportal.portal.Entity.Assessment;
import examportal.portal.Repo.AssessmentRepo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@SpringBootTest(classes=AssessmentRepoTest.class)
public class AssessmentRepoTest {

    @Mock
    private AssessmentRepo assessmentRepo;

    @Test
    public void testGetAssessmentsByOrgnizationId() {
        // Arrange
        String orgnizationId = "org123";
        Pageable pageable = Pageable.unpaged();
        List<Assessment> mockAssessments = new ArrayList<>();
        // Add mock assessments to the list based on your requirements

        // Mocking the behavior of the repository method
        when(assessmentRepo.getAssessmentsBy_orgnizationId(eq(orgnizationId), eq(pageable))).thenReturn(Page.empty());

        // Act
        Page<Assessment> result = assessmentRepo.getAssessmentsBy_orgnizationId(orgnizationId, pageable);

        // Assert
        assertEquals(Page.empty(), result);
    }

    @Test
    public void testGetAssessmentsByUserId() {
        // Arrange
        String userId = "user123";
        List<Assessment> mockAssessments = new ArrayList<>();
        // Add mock assessments to the list based on your requirements

        // Mocking the behavior of the repository method
        when(assessmentRepo.getAssessmentsBy_userId(eq(userId))).thenReturn(mockAssessments);

        // Act
        List<Assessment> result = assessmentRepo.getAssessmentsBy_userId(userId);

        // Assert
        assertEquals(mockAssessments, result);
    }

    @Test
    public void testGetAllAssessmenByName() {
        // Arrange
        String name = "Assessment Name";
        List<Assessment> mockAssessments = new ArrayList<>();
        // Add mock assessments to the list based on your requirements

        // Mocking the behavior of the repository method
        when(assessmentRepo.getAllAssesmenByName(eq(name))).thenReturn(mockAssessments);

        // Act
        List<Assessment> result = assessmentRepo.getAllAssesmenByName(name);

        // Assert
        assertEquals(mockAssessments, result);
    }

    @Test
    public void testGetAssessmentByStudentAndPaperId() {
        // Arrange
        String userId = "user123";
        String paperId = "paper123";
        Assessment mockAssessment = new Assessment();
        // Set properties of mockAssessment based on your requirements

        // Mocking the behavior of the repository method
        when(assessmentRepo.getAssessmentByStudentAndpaperId(eq(userId), eq(paperId))).thenReturn(mockAssessment);

        // Act
        Assessment result = assessmentRepo.getAssessmentByStudentAndpaperId(userId, paperId);

        // Assert
        assertEquals(mockAssessment, result);
    }

    // You can add more test cases as needed for other methods or scenarios
}

