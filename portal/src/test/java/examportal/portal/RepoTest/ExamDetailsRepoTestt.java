package examportal.portal.RepoTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Repo.ExamDetailsRepo;

@SpringBootTest(classes= ExamDetailsRepoTestt.class)
public class ExamDetailsRepoTestt {

 
    @Mock
    private ExamDetailsRepo examDetailsRepo;

    
    @Test
    public void testGetExamDetailsByPaperID() {
   
        String paperId = "123";
        ExamDetails mockExamDetails = new ExamDetails();
    
        when(examDetailsRepo.getExamDetailsByPaperID(anyString())).thenReturn(mockExamDetails);

      
        ExamDetails result = examDetailsRepo.getExamDetailsByPaperID(paperId);

      
        assertEquals(mockExamDetails, result);
    }

}