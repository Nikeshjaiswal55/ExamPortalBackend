package examportal.portal.ServiceImpTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import examportal.portal.Entity.AttemptedQuestions;
import examportal.portal.Repo.AttemptedQuestionsRepo;
import examportal.portal.ServicesImpl.AttemptedQuestionImpl;

@SpringBootTest(classes=AttemptedQUestionImplTest.class)
public class AttemptedQUestionImplTest {

    @Mock
    private AttemptedQuestionsRepo attemptedQuestionsRepo;

    @InjectMocks
    private AttemptedQuestionImpl attemptedQuestionService;
       @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAttemptedQuestions() {
        
        AttemptedQuestions sampleAttemptedQuestions = new AttemptedQuestions();
        sampleAttemptedQuestions.setOptions(List.of("Option1", "Option2"));
        sampleAttemptedQuestions.setQuestions("Sample Question");
        sampleAttemptedQuestions.setCorrectAns("Option1");
        sampleAttemptedQuestions.setUserAns("Option2");
        sampleAttemptedQuestions.setPaperID("Paper123");
        sampleAttemptedQuestions.setStudentID("Student456");

        when(attemptedQuestionsRepo.save(any(AttemptedQuestions.class))).thenReturn(sampleAttemptedQuestions);

       
        AttemptedQuestions result = attemptedQuestionService.createAttemptedQuestions(sampleAttemptedQuestions);

    
        verify(attemptedQuestionsRepo, times(1)).save(any(AttemptedQuestions.class));

                assertEquals(sampleAttemptedQuestions.getAttempteQuestionId(), result.getAttempteQuestionId());
        assertEquals(sampleAttemptedQuestions.getOptions(), result.getOptions());
        assertEquals(sampleAttemptedQuestions.getQuestions(), result.getQuestions());
        assertEquals(sampleAttemptedQuestions.getCorrectAns(), result.getCorrectAns());
        assertEquals(sampleAttemptedQuestions.getUserAns(), result.getUserAns());
        assertEquals(sampleAttemptedQuestions.getPaperID(), result.getPaperID());
        assertEquals(sampleAttemptedQuestions.getStudentID(), result.getStudentID());
    }
}
