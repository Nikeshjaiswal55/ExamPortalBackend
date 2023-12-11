package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Course;
import examportal.portal.Payloads.CourseDto;
import examportal.portal.Payloads.EmailsDto;
import examportal.portal.Payloads.PaginationDto;
public interface CourseService {

    List<Course>getAllCourse(Integer pageNumber, int size, String sortField, String sortOrder);

    //  List<Course>getAllCourseByStudentName(String name);

    Course getCourseByCouseId(String getId);

    Course addCourse(CourseDto course);

    Course updateCourse(Course course);

    void deleteCourseById(String getId);

    List<Course>getAllCourseByUserId(String userId,PaginationDto dto);

    String creatingStudentInBackGround(List<EmailsDto> dto,String courseId ,String token);
    
} 