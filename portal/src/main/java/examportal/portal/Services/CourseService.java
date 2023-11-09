package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Course;
public interface CourseService {

      List<Course>getAllCourse();

     Course getCourseById(String getId);

     Course addCourse(Course course);

    Course updateCourse(Course course);

    void deleteCourseById(String getId);

    
} 