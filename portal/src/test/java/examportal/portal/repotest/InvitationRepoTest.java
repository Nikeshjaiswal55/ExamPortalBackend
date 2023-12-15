package examportal.portal.repotest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import examportal.portal.Entity.InvitedStudents;
import examportal.portal.Repo.InvitationRepo;


@SpringBootTest(classes=InvitationRepoTest.class)
public class InvitationRepoTest {

    @Mock
    private InvitationRepo invitationRepo;

    @Test
    public void testGetAllStudentByPaperId() {
        // Arrange
        String paperId = "123";
        List<InvitedStudents> mockInvitedStudents = new ArrayList<>();
        // Add mock invited students to the list based on your requirements

        // Mocking the behavior of the repository method
        when(invitationRepo.getAllStudentByPaperId(eq(paperId))).thenReturn(mockInvitedStudents);

        // Act
        List<InvitedStudents> result = invitationRepo.getAllStudentByPaperId(paperId);

        // Assert
        assertEquals(mockInvitedStudents, result);
    }

    @Test
    public void testGetStudentByStudentId() {
        // Arrange
        String studentId = "456";
        InvitedStudents mockInvitedStudent = new InvitedStudents();
        // Set properties of mockInvitedStudent based on your requirements

        // Mocking the behavior of the repository method
        when(invitationRepo.getStudentByStudentId(eq(studentId))).thenReturn(mockInvitedStudent);

        // Act
        InvitedStudents result = invitationRepo.getStudentByStudentId(studentId);

        // Assert
        assertEquals(mockInvitedStudent, result);
    }
}

