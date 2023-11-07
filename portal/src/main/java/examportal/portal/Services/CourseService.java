package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Course;

public interface CourseService {

    List<Course>getCourse();

    Course getCourseById(String getId);

   // Course addCourse(Course course);
   
    Course postCourse(Course course);

    Course putCourse(Course course);

    void deleteCourseById(String getId);
    
}
