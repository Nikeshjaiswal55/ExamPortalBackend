package examportal.portal.RepoTest;



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
        
        String orgnizationId = "org123";
        Pageable pageable = Pageable.unpaged();
        List<Assessment> mockAssessments = new ArrayList<>();
        

        when(assessmentRepo.getAssessmentsBy_orgnizationId(eq(orgnizationId), eq(pageable))).thenReturn(Page.empty());

       
        Page<Assessment> result = assessmentRepo.getAssessmentsBy_orgnizationId(orgnizationId, pageable);

        assertEquals(Page.empty(), result);
    }

    @Test
    public void testGetAssessmentsByUserId() {
       
        String userId = "user123";
        List<Assessment> mockAssessments = new ArrayList<>();
        
        when(assessmentRepo.getAssessmentsBy_userId(eq(userId))).thenReturn(mockAssessments);
        List<Assessment> result = assessmentRepo.getAssessmentsBy_userId(userId);

       
        assertEquals(mockAssessments, result);
    }

   

    @Test
    public void testGetAssessmentByStudentAndPaperId() {
        
        String userId = "user123";
        String paperId = "paper123";
        Assessment mockAssessment = new Assessment();
       
        when(assessmentRepo.getAssessmentByStudentAndpaperId(eq(userId), eq(paperId))).thenReturn(mockAssessment);

      
        Assessment result = assessmentRepo.getAssessmentByStudentAndpaperId(userId, paperId);

      
        assertEquals(mockAssessment, result);
    }

   
}