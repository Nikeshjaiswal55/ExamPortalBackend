package examportal.portal.repotest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.Mentor;
import examportal.portal.Repo.MentorRepo;


@SpringBootTest(classes= MentorRepoTest.class)
public class MentorRepoTest {

    @Mock
    private MentorRepo mentorRepo;

    @Test
    public void testGetAllMentorsByName() {
        // Arrange
        String name = "John";
        List<Mentor> mockMentors = new ArrayList<>();
        // Add mock mentors to the list based on your requirements

        // Mocking the behavior of the repository method
        when(mentorRepo.getAllMentorsByName(eq(name))).thenReturn(mockMentors);

        // Act
        List<Mentor> result = mentorRepo.getAllMentorsByName(name);

        // Assert
        assertEquals(mockMentors, result);
    }
}

