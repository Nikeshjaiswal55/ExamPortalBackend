package examportal.portal.RepoTest;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.Student;
import examportal.portal.Repo.StudentRepo;
 
@SpringBootTest(classes=StudentRepoTest.class)
public class StudentRepoTest {

    @Mock
    private StudentRepo studentRepo;

    @Test
    public void testGetStudentByEmail() {
       
        String email = "test@example.com";
        Student mockStudent = new Student();
       
        when(studentRepo.getszStudentByEmail(eq(email))).thenReturn(mockStudent);

      
        Student result = studentRepo.getszStudentByEmail(email);

        assertEquals(mockStudent, result);
    }

    @Test
    public void testFindAllStudentByPaperId() {
      
        String paperId = "123";
        List<Student> mockStudents = new ArrayList<>();
        
        when(studentRepo.findAllStudentByPaperId(eq(paperId))).thenReturn(mockStudents);

      
        List<Student> result = studentRepo.findAllStudentByPaperId(paperId);

     
        assertEquals(mockStudents, result);
    }

    
 }

