package examportal.portal;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import examportal.portal.Entity.Course;
import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.CourseDto;
import examportal.portal.Payloads.EmailsDto;
import examportal.portal.Payloads.PaginationDto;
import examportal.portal.Repo.CourseRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Repo.UserRepo;
import examportal.portal.ServicesImpl.Auth0Service;
import examportal.portal.ServicesImpl.CourseServiceImpl;
import jakarta.el.ELException;


@SpringBootTest(classes= CourseServiceImplTestt.class)
class CourseServiceImplTestt {

    @Mock
    private CourseRepo courseRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private Auth0Service auth0Service;

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;



@Test
void testGetAllCourse() {
    // Arrange
    Integer pageNumber = 0;
    int size = 10;
    String sortField = "course_name";
    String sortOrder = "asc";

    List<Course> mockCourses = Arrays.asList(
            new Course("1", "Course 1", "userId1", null, null),
            new Course("2", "Course 2", "userId2", null, null)
    );

    // Mocking the behavior of courseRepo.findAll
    Page<Course> mockPage = new PageImpl<>(mockCourses);
    when(courseRepo.findAll(any(Pageable.class))).thenReturn(mockPage);

    // Mocking the behavior of userRepo.findById for userId1
    User mockUser1 = new User("userId1", "John Doe", null, null, null, null);
    when(userRepo.findById("userId1")).thenReturn(Optional.of(mockUser1));

    // Mocking the behavior of userRepo.findById for userId2
    User mockUser2 = new User("userId2", "Jane Doe", null, null, null, null);
    when(userRepo.findById("userId2")).thenReturn(Optional.of(mockUser2));

    // Act
    List<Course> result = courseServiceImpl.getAllCourse(pageNumber, size, sortField, sortOrder);

    // Assert
    assertNotNull(result, "Result should not be null");
    assertEquals(mockCourses.size(), result.size(), "Result size should match");

    // Verify method invocations
    verify(courseRepo, times(1)).findAll(any(Pageable.class));
    verify(userRepo, times(mockCourses.size())).findById(anyString());
}


    @Test
    void testGetCourseByCourseId() {
        // Arrange
        String courseId = "1";
        Course mockCourse = new Course(courseId, "Course 1", "userId1", courseId, courseId);

        when(courseRepo.findById(courseId)).thenReturn(Optional.of(mockCourse));
        when(userRepo.findById(mockCourse.getUserId())).thenReturn(Optional.of(new User("userId1", "John Doe", courseId, courseId, courseId, courseId)));

        // Act
        Course result = courseServiceImpl.getCourseByCouseId(courseId);

        // Assert
        assertEquals(mockCourse, result);
        verify(courseRepo, times(1)).findById(courseId);
        verify(userRepo, times(1)).findById(mockCourse.getUserId());
    }

    @Test
    void testGetCourseByCourseId_NotFound() {
        // Arrange
        String courseId = "999";

        when(courseRepo.findById(courseId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> courseServiceImpl.getCourseByCouseId(courseId));

        // Verify
        verify(courseRepo, times(1)).findById(courseId);
    }
    

    @Test
    @Deprecated
    void testAddCourse() {
        // Arrange
        CourseDto courseDto = new CourseDto();
        courseDto.setUserId("userId1");
        courseDto.setCourse_name("New Course");
        courseDto.setDuration("4 weeks");

        when(userRepo.findById(courseDto.getUserId())).thenReturn(Optional.of(new User("userId1", "John Doe", null, null, null, null)));
        when(userRepo.findByEmail(anyString())).thenReturn(null);
        // when(auth0Service.createUser(anyString(), anyString(), anyString())).thenReturn("newUserId");

        // Act
        Course result = courseServiceImpl.addCourse(courseDto);

        // Assert
        assertNotNull(result);
        assertEquals(courseDto.getCourse_name(), result.getCourse_name());
        assertEquals(courseDto.getUserId(), result.getUserId());
        verify(userRepo, times(1)).findById(courseDto.getUserId());
        verify(userRepo, times(courseDto.getEmailsDto().size())).findByEmail(anyString());
        // verify(auth0Service, times(courseDto.getEmailsDto().size())).createUser(anyString(), anyString(), anyString());
        verify(courseRepo, times(1)).save(any(Course.class));
    }

    // @Test
    // @Deprecated
    // void testCreatingStudentInBackGround() {
    //     // Arrange
    //     List<EmailsDto> emailsDtoList = new ArrayList<>();
    //     emailsDtoList.add(new EmailsDto());
    //     String courseId = "1";
    //     String token = "token123";

    //     Course course = new Course(courseId, "Course 1", "userId1", null, null);

    //     when(courseRepo.findById(courseId)).thenReturn(Optional.of(course));
    //     when(studentRepo.getszStudentByEmail(anyString())).thenReturn(null);
    //     // when(auth0Service.createUser(anyString(), anyString(), anyString())).thenReturn("newUserId");
    //     when(userRepo.save(any(User.class))).thenReturn(new User("newUserId", "John Doe", "john.doe@example.com", "password", "Student", token));
    //     when(studentRepo.save(any(Student.class))).thenReturn(new Student());

    //     // Act
    //     String result = courseServiceImpl.creatingStudentInBackGround(emailsDtoList, courseId, token);

    //     // Assert
    //     assertNotNull(result);
    //     assertEquals(" All Student Created Successfully ",result);
    //     verify(courseRepo, times(1)).findById(courseId);
    //     // verify(studentRepo, times(emailsDtoList.size())).getszStudentByEmail(anyString());
    //      verify(studentRepo, times(emailsDtoList.size())).getszStudentByEmail(anyString());
    //     // verify(auth0Service, times(emailsDtoList.size())).createUser(anyString(), anyString(), anyString());
    //     verify(userRepo, times(emailsDtoList.size())).save(any(User.class));
    //     verify(studentRepo, times(emailsDtoList.size())).save(any(Student.class));
    // }


    // @Test
    // void testCreatingStudentInBackground() {
    //     List<EmailsDto> emailsDtoList = new ArrayList<>();
    //     emailsDtoList.add(new EmailsDto("student@example.com", "John Doe", "Branch", "org123"));

    //     String courseId = "course123";
    //     String token = "token123";

    //     when(courseRepo.findById(courseId)).thenReturn(java.util.Optional.of(new Course()));
    //     when(studentRepo.getszStudentByEmail("student@example.com")).thenReturn(null);
    //     when(auth0Service.createUser(any(), any(), any())).thenReturn("userId123");

    //     String result = courseServiceImpl.creatingStudentInBackGround(emailsDtoList, courseId, token);

    //     assertEquals("All Student Created Successfully", result);

    //     verify(courseRepo, times(1)).findById(courseId);
    //     verify(studentRepo, times(1)).getszStudentByEmail("student@example.com");
    //     verify(auth0Service, times(1)).createUser("student@example.com", any(), "token123");
    //     verify(userRepo, times(1)).save(any());
    //     verify(studentRepo, times(1)).save(any());
    // }


    
    @Test
    void testUpdateCourse() {
        Course course = new Course();
        course.setCourse_id("course123");
        course.setCourse_name("Updated Course");
        course.setUserId("user123");

        when(courseRepo.findById("course123")).thenReturn(java.util.Optional.of(course));
        when(courseRepo.save(any())).thenReturn(course);

        Course updatedCourse = courseServiceImpl.updateCourse(course);

        assertNotNull(updatedCourse);
        assertEquals("Updated Course", updatedCourse.getCourse_name());

        verify(courseRepo, times(1)).findById("course123");
        verify(courseRepo, times(1)).save(any());
    }


    @Test
    void testDeleteCourseById() {
        String courseId = "course123";

        assertDoesNotThrow(() -> courseServiceImpl.deleteCourseById(courseId));

        verify(courseRepo, times(1)).deleteById(courseId);
    }

    @Test
    void testGetAllCourseByUserId() {
        String userId = "user123";
        PaginationDto paginationDto = new PaginationDto(null, null, userId, userId);
        paginationDto.setPageNo(0);
        paginationDto.setPageSize(10);
        paginationDto.setSortDirection("ASC");
        paginationDto.setProperty("course_name");

        List<Course> mockCourses = new ArrayList<>();
        when(courseRepo.getCourseByUseId(eq(userId), any(Pageable.class))).thenReturn(mockCourses);

        List<Course> result = courseServiceImpl.getAllCourseByUserId(userId, paginationDto);

        assertNotNull(result);
        assertEquals(0, result.size());

        verify(courseRepo, times(1)).getCourseByUseId(eq(userId), any(Pageable.class));
    }
   

    @Test
    void testUpdateCourseNotFound() {
        Course course = new Course();
        course.setCourse_id("course123");
        course.setCourse_name("Updated Course");
        course.setUserId("user123");

        when(courseRepo.findById("course123")).thenReturn(java.util.Optional.empty());

        assertThrows(ELException.class, () -> courseServiceImpl.updateCourse(course));

        verify(courseRepo, times(1)).findById("course123");
        verify(courseRepo, never()).save(any());
    }


}