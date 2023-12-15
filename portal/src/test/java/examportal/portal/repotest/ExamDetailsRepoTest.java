package examportal.portal.repotest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Repo.ExamDetailsRepo;

@SpringBootTest(classes= ExamDetailsRepoTest.class)
public class ExamDetailsRepoTest {

    // @Autowired
    @Mock
    private ExamDetailsRepo examDetailsRepo;

    
    @Test
    public void testGetExamDetailsByPaperID() {
        // Arrange
        String paperId = "123";
        ExamDetails mockExamDetails = new ExamDetails();
    
        when(examDetailsRepo.getExamDetailsByPaperID(anyString())).thenReturn(mockExamDetails);

        // Act
        ExamDetails result = examDetailsRepo.getExamDetailsByPaperID(paperId);

        // Assert
        assertEquals(mockExamDetails, result);
    }

}
