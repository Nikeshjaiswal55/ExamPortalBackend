package examportal.portal.RepoTest;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Repo.AttemptepaperRepo;


@SpringBootTest(classes=AttemptedPapersRepoTest.class)
public class AttemptedPapersRepoTest {

    @Mock
    private AttemptepaperRepo attemptedPapersRepo;

    @Test
    public void testGetAttemptedStudentsByPaperId() {
        
        String paperId = "paper123";
        AttemptedPapers mockAttemptedPapers = new AttemptedPapers();
        
        when(attemptedPapersRepo.GetattemptedStudentsByPaperId(eq(paperId))).thenReturn(mockAttemptedPapers);

       
        AttemptedPapers result = attemptedPapersRepo.GetattemptedStudentsByPaperId(paperId);

       
        assertEquals(mockAttemptedPapers, result);
    }

    @Test
    public void testGetAllAttemptedPaperByStudentID() {
       
        String studentId = "student123";
        String paperId = "paper123";
        AttemptedPapers mockAttemptedPapers = new AttemptedPapers();
       
        when(attemptedPapersRepo.getAllAttemptedPaperbyStudentID(eq(studentId), eq(paperId))).thenReturn(mockAttemptedPapers);

       
        AttemptedPapers result = attemptedPapersRepo.getAllAttemptedPaperbyStudentID(studentId, paperId);

       
        assertEquals(mockAttemptedPapers, result);
    }

   }