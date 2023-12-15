package examportal.portal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import examportal.portal.Entity.Cheating;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Result;
import examportal.portal.Entity.Questions;
import examportal.portal.Entity.AttemptedQuestions;
import examportal.portal.Payloads.ResultDto;
import examportal.portal.Repo.AttemptedQuestionsRepo;
import examportal.portal.Repo.ResultRepo;
import examportal.portal.Repo.CheatingRepo;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.AttemptepaperRepo;
import examportal.portal.Services.ResultService;
import examportal.portal.ServicesImpl.ResultServiceImpl;

public class ResultServiceImplTest {

    private ResultService resultService;

    private AttemptedQuestionsRepo attemptedQuestionsRepoMock;
    private ResultRepo resultRepoMock;
    private CheatingRepo cheatingRepoMock;
    private ModelMapper modelMapperMock;
    private ExamDetailsRepo examDetailsRepoMock;
    private AttemptepaperRepo attemptepaperRepoMock;

    @BeforeEach
    public void setUp() {
        attemptedQuestionsRepoMock = mock(AttemptedQuestionsRepo.class);
        resultRepoMock = mock(ResultRepo.class);
        cheatingRepoMock = mock(CheatingRepo.class);
        modelMapperMock = mock(ModelMapper.class);
        examDetailsRepoMock = mock(ExamDetailsRepo.class);
        attemptepaperRepoMock = mock(AttemptepaperRepo.class);

        resultService = new ResultServiceImpl(
            attemptedQuestionsRepoMock,
            resultRepoMock,
            cheatingRepoMock,
            modelMapperMock,
            examDetailsRepoMock,
            null,  // Inject other dependencies here
            attemptepaperRepoMock
        );
    }

    @Test
    public void testCreateResult() {
        // Mocking data
        ResultDto dto = createMockResultDto();
        AttemptedQuestions attemptedQuestions = createMockAttemptedQuestions();
        List<AttemptedQuestions> attemptedQuestionsList = new ArrayList<>();
        attemptedQuestionsList.add(attemptedQuestions);

        // Mocking behavior
        when(attemptedQuestionsRepoMock.saveAll(anyList())).thenReturn(attemptedQuestionsList);
        when(examDetailsRepoMock.getExamDetailsByPaperID(anyString())).thenReturn(createMockExamDetails());
        when(resultRepoMock.save(any(Result.class))).thenReturn(createMockResult());
        when(cheatingRepoMock.save(any(Cheating.class))).thenReturn(createMockCheating());
        when(modelMapperMock.map(any(AttemptedQuestions.class), eq(Questions.class))).thenReturn(createMockQuestion());

        // Perform the test
        ResultDto resultDto = resultService.createResult(dto);

        // Verify that the necessary methods were called
        verify(attemptedQuestionsRepoMock, times(1)).saveAll(anyList());
        verify(examDetailsRepoMock, times(1)).getExamDetailsByPaperID(anyString());
        verify(resultRepoMock, times(1)).save(any(Result.class));
        verify(cheatingRepoMock, times(1)).save(any(Cheating.class));
        verify(modelMapperMock, times(1)).map(any(AttemptedQuestions.class), eq(Questions.class));

        // Perform assertions on the result
        // Add your assertions here based on the expected behavior and your actual implementation
    }

    // Helper methods to create mock objects for testing 

    private ResultDto createMockResultDto() {
        // Implement as needed
        return null;
    }

    private AttemptedQuestions createMockAttemptedQuestions() {
        // Implement as needed
        return null;
    }

    private ExamDetails createMockExamDetails() {
        // Implement as needed
        return null;
    }

    private Result createMockResult() {
        // Implement as needed
        return null;
    }

    private Cheating createMockCheating() {
        // Implement as needed
        return null;
    }

    private Questions createMockQuestion() {
        // Implement as needed
        return null;
    }
}

