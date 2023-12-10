package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Course;
import examportal.portal.Payloads.CourseDto;
public interface CourseService {

    List<Course>getAllCourse(Integer pageNumber, int size, String sortField, String sortOrder);

     List<Course>getAllCourseByStudentName(String name);

    Course getCourseByCouseId(String getId);

    Course addCourse(CourseDto course);

    Course updateCourse(Course course);

    void deleteCourseById(String getId);

    
} 