package examportal.portal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import examportal.portal.Entity.Student;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.ServicesImpl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes= TestStudentService.class)
public class TestStudentService {
    
    @Mock
    private StudentRepo studentRepo;
    
    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        // Any setup or initialization before each test can go here
    }



    @Test
    void getAllStudents() {
        // Arrange
        List<Student> mockStudents = Arrays.asList(new Student(), new Student());
        when(studentRepo.findAll()).thenReturn(mockStudents);

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert
        assertEquals(mockStudents, result);
        verify(studentRepo, times(1)).findAll();
    }

    // @Test
    // void testGetAllStudents() {
    //     // Arrange
    //     int page = 0;
    //     int size = 10;
    //     String sortField = "name";
    //     String sortOrder = "asc";
    //     Sort sort = Sort.by(sortField).ascending();
    //     Pageable pageable = PageRequest.of(page, size, sort);
    //     Page<Student> mockedPage = mockPageWithOneStudent(); // Assuming a helper method to create a mock page

    //     when(studentRepo.findAll(pageable)).thenReturn(mockedPage);

    //     // Act
    //     List<Student> result = studentService.getAllStudents(page, size, sortField, sortOrder);

    //     // Assert
    //     assertEquals(1, result.size());  // Adjust based on the actual mocked data
    //     // Add more assertions based on your specific requirements
    // }

    // private Page<Student> mockPageWithOneStudent() {
    //     // You can create a mock Page with a single student for testing purposes
    //     List<Student> students = Collections.singletonList(new Student());
    //     return new PageImpl<>(students);
    // }
    @Test
    void testGetSingleStudent() {
        // Arrange
        String studentId = "1";
        when(studentRepo.findById(studentId)).thenReturn(java.util.Optional.of(new Student()));

        // Act
        Student result = studentService.getSingleStudent(studentId);

        // Assert
        assertNotNull(result);
        // Add more assertions based on your specific requirements
    }

    @Test
    void testGetSingleStudent_NotFound() {
        // Arrange
        String studentId = "nonexistent";
        when(studentRepo.findById(studentId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> studentService.getSingleStudent(studentId));
    }

    @Test
    void testUpdateStudent() {
        // Arrange
        Student existingStudent = new Student();
        existingStudent.setStudentid("1");
        when(studentRepo.findById(existingStudent.getStudentid())).thenReturn(java.util.Optional.of(existingStudent));
        when(studentRepo.save(any(Student.class))).thenReturn(existingStudent);

        // Act
        Student result = studentService.updateStudent(existingStudent);

        // Assert
        assertNotNull(result);
        assertEquals(existingStudent.getStudentid(), result.getStudentid());
        // Add more assertions based on your specific requirements
    }



    @Test
    void testDeleteStudent() {
        // Arrange
        String studentId = "1";
        doNothing().when(studentRepo).deleteById(studentId);

        // Act
        String result = studentService.deleteStudent(studentId);

        // Assert
        assertEquals("Record Deleted", result);
        verify(studentRepo, times(1)).deleteById(studentId);
    }

}