package examportal.portal.ServiceImpTest;

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

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes= TestStudentServiceTest.class)
public class TestStudentServiceTest {
    
    @Mock
    private StudentRepo studentRepo;
    
    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        // Any setup or initialization before each test can go here
    }



    // @Test
    // void getAllStudents() {
    //     // Arrange
    //     List<Student> mockStudents = Arrays.asList(new Student(), new Student());
    //     when(studentRepo.findAll()).thenReturn(mockStudents);

    //     // Act
    //     List<Student> result = studentService.getAllStudents(null, 0, null, null);
        
    //     // Assert
    //     assertEquals(mockStudents, result);
    //     verify(studentRepo, times(1)).findAll();
    // }
    

//     @Test
//     void getAllStudents() {
//     // Arrange
//     List<Student> mockStudents = Arrays.asList(new Student(), new Student());
//     when(studentRepo.findAll()).thenReturn(mockStudents);

//     // Act
//     List<Student> result = studentService.getAllStudents(null, 0, null, "asc"); // Provide a non-null sortOrder

//     // Assert
//     assertEquals(mockStudents, result);
//     verify(studentRepo, times(1)).findAll();
// }





    @Test
    void testGetAllStudents() {
     
        int page = 0;
        int size = 10;
        String sortField = "name";
        String sortOrder = "asc";
        Sort sort = Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> mockedPage = mockPageWithOneStudent(); 

        when(studentRepo.findAll(pageable)).thenReturn(mockedPage);

       
        List<Student> result = studentService.getAllStudents(page, size, sortField, sortOrder);

        assertEquals(1, result.size());  
    }

    private Page<Student> mockPageWithOneStudent() {
      
        List<Student> students = Collections.singletonList(new Student());
        return new PageImpl<>(students);
    }
    @Test
    void testGetSingleStudent() {
      
        String studentId = "1";
        when(studentRepo.findById(studentId)).thenReturn(java.util.Optional.of(new Student()));

        
        Student result = studentService.getSingleStudent(studentId);

        
        assertNotNull(result);
        
    }

    @Test
    void testGetSingleStudent_NotFound() {
      
        String studentId = "nonexistent";
        when(studentRepo.findById(studentId)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getSingleStudent(studentId));
    }

    @Test
    void testUpdateStudent() {
      
        Student existingStudent = new Student();
        existingStudent.setStudentid("1");
        when(studentRepo.findById(existingStudent.getStudentid())).thenReturn(java.util.Optional.of(existingStudent));
        when(studentRepo.save(any(Student.class))).thenReturn(existingStudent);

      
        Student result = studentService.updateStudent(existingStudent);

        
        assertNotNull(result);
        assertEquals(existingStudent.getStudentid(), result.getStudentid());

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