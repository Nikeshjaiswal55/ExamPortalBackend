package examportal.portal.RepoTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import examportal.portal.Entity.Course;
import examportal.portal.Repo.CourseRepo;



@SpringBootTest(classes= CourseRepoTest.class)
public class CourseRepoTest {

    @Mock
    private CourseRepo courseRepo;

     
      @Test
    public void testGetCourseByUserId() {
        
        String userId = "testUserId";
        Pageable pageable = mock(Pageable.class);
        List<Course> mockCourses = new ArrayList<>(); 
        Page<Course> page = new PageImpl<>(mockCourses);
        when(courseRepo.getCourseByUseId(userId, pageable)).thenReturn(page);

        Page<Course> result = courseRepo.getCourseByUseId(userId, pageable);

        
        assertEquals(mockCourses, result.getContent());
    }

    @Test
    public void testSearchCourse() {
       
        String userName = "testUserName";
        String courseName = "testCourseName";
        List<Course> mockCourses = new ArrayList<>(); 
        when(courseRepo.SearchCouse(userName, courseName)).thenReturn(mockCourses);

      
        List<Course> result = courseRepo.SearchCouse(userName, courseName);

    
        assertEquals(mockCourses, result);
    }

}

