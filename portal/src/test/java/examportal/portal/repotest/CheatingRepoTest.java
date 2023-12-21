package examportal.portal.RepoTest;

    
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
       
        String studentId = "123";
        String paperId = "456";
        Cheating mockCheating = new Cheating();
      

        when(cheatingRepo.getCheatingByStudentAndPaperId(eq(studentId), eq(paperId))).thenReturn(mockCheating);

        Cheating result = cheatingRepo.getCheatingByStudentAndPaperId(studentId, paperId);

        
        assertEquals(mockCheating, result);
    }

}

