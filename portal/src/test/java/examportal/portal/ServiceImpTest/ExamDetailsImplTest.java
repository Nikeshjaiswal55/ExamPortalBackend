package examportal.portal.ServiceImpTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;  // Add this import
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import examportal.portal.Entity.ExamDetails;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.ServicesImpl.ExamDetailsImpl;

@ExtendWith(MockitoExtension.class)
public class ExamDetailsImplTest {

    @Mock
    private ExamDetailsRepo examDetailsRepo;

    @Mock
    private Logger log;

    @InjectMocks
    private ExamDetailsImpl examDetailsService;

    @Test
    public void testCreateExamDetails() {
      
    ExamDetails inputExamDetails = new ExamDetails(
        "duration", "mode", null, 1, true, "branch", "session", "assessment",
        "active", true, true, 100, 50, "paperId", "createdDate", "publishedDate",
        "description", true, "auto check", "introduction"
    );

      
        ExamDetails savedExamDetails = new ExamDetails(
            null, "duration", "mode", 1, true, "branch", "session", "assessment",
            "active", true, true, 100, 50, "paperId", "createdDate", "publishedDate",
            "description", true, "auto check", "introduction"
        );
        when(examDetailsRepo.save(any(ExamDetails.class))).thenReturn(savedExamDetails);

        ExamDetails result = examDetailsService.createExamDetails(inputExamDetails);

     
        assertEquals(savedExamDetails, result);
        
        verify(examDetailsRepo, times(1)).save(any(ExamDetails.class));
        
        
        verify(log).info("ExamDetails CreateExamDetails method Starts ");
        verify(log).info("ExamDetails CreateExamDetails method Ends ");
    }
}

