package examportal.portal.RepoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import examportal.portal.Entity.InvitedStudents;
import examportal.portal.Repo.InvitationRepo;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes=InvitationRepoTests.class)

public class InvitationRepoTests {

    @Mock
    private InvitationRepo invitationRepo;



    @Test
    public void testGetAllStudentByPaperId() {
        // Arrange
        String paperId = "yourPaperId";
        InvitedStudents student1 = new InvitedStudents(/* initialize with data */);
        InvitedStudents student2 = new InvitedStudents(/* initialize with data */);
        List<InvitedStudents> expectedStudents = Arrays.asList(student1, student2);

        when(invitationRepo.getAllStudentByPaperId(paperId)).thenReturn(expectedStudents);

        
        List<InvitedStudents> actualStudents = invitationRepo.getAllStudentByPaperId(paperId);

        assertEquals(expectedStudents, actualStudents);
    }

    @Test
    public void testGetStudentByStudentId() {
       
        String studentId = "yourStudentId";
        InvitedStudents expectedStudent = new InvitedStudents(/* initialize with data */);

        when(invitationRepo.getStudentByStudentId(studentId)).thenReturn(expectedStudent);

       
        InvitedStudents actualStudent = invitationRepo.getStudentByStudentId(studentId);

     
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void testGetInvitedStudentsByStudentAndPaperId() {
       
        String studentId = "yourStudentId";
        String paperId = "yourPaperId";
        InvitedStudents expectedStudent = new InvitedStudents(/* initialize with data */);

        when(invitationRepo.getInvitedStudentsByStudentAndPaperId(studentId, paperId)).thenReturn(expectedStudent);

        
        InvitedStudents actualStudent = invitationRepo.getInvitedStudentsByStudentAndPaperId(studentId, paperId);

        assertEquals(expectedStudent, actualStudent);
    }
}
