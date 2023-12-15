package examportal.portal;


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
        // Arrange
        ExamDetails inputExamDetails = new ExamDetails( "duration", "mode", null, 1, true, "branch", "session", "assessment", "active", true, true, 100, 50,
    "paperId", "createdDate", "publishedDate");
        // You may set properties of inputExamDetails as needed

        ExamDetails savedExamDetails = new ExamDetails("duration", "mode", null, 1, true, "branch", "session", "assessment", "active", true, true, 100, 50,
        "paperId", "createdDate", "publishedDate");
        // You may set properties of savedExamDetails as expected after saving

        when(examDetailsRepo.save(any(ExamDetails.class))).thenReturn(savedExamDetails);

        // Act
        ExamDetails result = examDetailsService.createExamDetails(inputExamDetails);

        // Assert
        assertEquals(savedExamDetails, result);
        
        // Verify that the save method of examDetailsRepo was called once with any ExamDetails
        verify(examDetailsRepo, times(1)).save(any(ExamDetails.class));
        
        // Verify that log.info was called with the specified message
        verify(log).info("ExamDetails CreateExamDetails method Starts ");
        verify(log).info("ExamDetails CreateExamDetails method Ends ");
    }
}

