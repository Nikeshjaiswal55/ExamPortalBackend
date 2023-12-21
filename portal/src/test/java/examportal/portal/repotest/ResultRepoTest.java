package examportal.portal.RepoTest;



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
     
        String paperId = "123";
        List<Result> mockResults = new ArrayList<>();
    
        when(resultRepo.findAllByPaperIdOrderByPercentageDesc(eq(paperId))).thenReturn(mockResults);

        
        List<Result> result = resultRepo.findAllByPaperIdOrderByPercentageDesc(paperId);

     
        assertEquals(mockResults, result);
    }

    @Test
    public void testFindAllResutlByStudentID() {
      
        String studentId = "456";
        List<Result> mockResults = new ArrayList<>();
      
        when(resultRepo.findAllResutlByStudentID(eq(studentId))).thenReturn(mockResults);

        
        List<Result> result = resultRepo.findAllResutlByStudentID(studentId);

        
        assertEquals(mockResults, result);
    }

    @Test
    public void testGetResultByStudentAndPaperId() {
     
        String paperId = "123";
        String studentId = "456";
        Result mockResult = new Result();
     
        when(resultRepo.getResultByStudentAndPaperId(eq(paperId), eq(studentId))).thenReturn(mockResult);

        Result result = resultRepo.getResultByStudentAndPaperId(paperId, studentId);

        assertEquals(mockResult, result);
    }


}
