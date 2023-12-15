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
import org.springframework.data.domain.Pageable;

import examportal.portal.Entity.Course;
import examportal.portal.Repo.CourseRepo;


@SpringBootTest(classes=CourseRepoTest.class)
public class CourseRepoTest {

    @Mock
    private CourseRepo courseRepo;

    @Test
    public void testGetCourseByUseId() {
        // Arrange
        String userId = "123";
        Pageable pageable = mock(Pageable.class);
        List<Course> mockCourses = new ArrayList<>();
        // Add mock courses to the list based on your requirements

        // Mocking the behavior of the repository method
        when(courseRepo.getCourseByUseId(eq(userId), eq(pageable))).thenReturn(mockCourses);

        // Act
        List<Course> result = courseRepo.getCourseByUseId(userId, pageable);

        // Assert
        assertEquals(mockCourses, result);
    }

    @Test
    public void testSearchCourse() {
        // Arrange
        String userName = "John";
        String courseName = "Math";
        List<Course> mockCourses = new ArrayList<>();
        // Add mock courses to the list based on your requirements

        // Mocking the behavior of the repository method
        when(courseRepo.SearchCouse(eq(userName), eq(courseName))).thenReturn(mockCourses);

        // Act
        List<Course> result = courseRepo.SearchCouse(userName, courseName);

        // Assert
        assertEquals(mockCourses, result);
    }

    @Test
    public void testFindByUserName() {
        // Arrange
        String userName = "John";
        List<Course> mockCourses = new ArrayList<>();
        // Add mock courses to the list based on your requirements

        // Mocking the behavior of the repository method
        when(courseRepo.findByUserName(eq(userName))).thenReturn(mockCourses);

        // Act
        List<Course> result = courseRepo.findByUserName(userName);

        // Assert
        assertEquals(mockCourses, result);
    }
}

