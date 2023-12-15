package examportal.portal.repotest;

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
        // Arrange
        String email = "test@example.com";
        Student mockStudent = new Student();
        // Set properties of mockStudent based on your requirements

        // Mocking the behavior of the repository method
        when(studentRepo.getszStudentByEmail(eq(email))).thenReturn(mockStudent);

        // Act
        Student result = studentRepo.getszStudentByEmail(email);

        // Assert
        assertEquals(mockStudent, result);
    }

    @Test
    public void testFindAllStudentByPaperId() {
        // Arrange
        String paperId = "123";
        List<Student> mockStudents = new ArrayList<>();
        // Add mock students to the list based on your requirements

        // Mocking the behavior of the repository method
        when(studentRepo.findAllStudentByPaperId(eq(paperId))).thenReturn(mockStudents);

        // Act
        List<Student> result = studentRepo.findAllStudentByPaperId(paperId);

        // Assert
        assertEquals(mockStudents, result);
    }

    @Test
    public void testGetAllStudentByBranch() {
        // Arrange
        String branch = "Computer Science";
        List<Student> mockStudents = new ArrayList<>();
        // Add mock students to the list based on your requirements

        // Mocking the behavior of the repository method
        when(studentRepo.getAllStudentBYBranch(eq(branch))).thenReturn(mockStudents);

        // Act
        List<Student> result = studentRepo.getAllStudentBYBranch(branch);

        // Assert
        assertEquals(mockStudents, result);
    }

    @Test
    public void testGetAllStudentByName() {
        // Arrange
        String name = "John Doe";
        List<Student> mockStudents = new ArrayList<>();
        // Add mock students to the list based on your requirements

        // Mocking the behavior of the repository method
        when(studentRepo.getAllStudentByName(eq(name))).thenReturn(mockStudents);

        // Act
        List<Student> result = studentRepo.getAllStudentByName(name);

        // Assert
        assertEquals(mockStudents, result);
    }

    // You can add more test cases as needed for other methods or scenarios
}

