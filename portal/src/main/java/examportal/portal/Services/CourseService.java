package examportal.portal.Services;

import java.util.List;
import java.util.concurrent.Future;

import examportal.portal.Entity.Course;
import examportal.portal.Payloads.EmailsDto;
import examportal.portal.Payloads.PaginationDto;
import examportal.portal.Response.CourseResponce;
public interface CourseService {

    List<Course>getAllCourse(Integer pageNumber, int size, String sortField, String sortOrder);

    //  List<Course>getAllCourseByStudentName(String name);

    Course getCourseByCouseId(String getId);

    Course addCourse(Course course);

    Course updateCourse(Course course);

    void deleteCourseById(String getId);

    CourseResponce getAllCourseByUserId(String userId,PaginationDto dto);

    Future<String> creatingStudentInBackGround(List<EmailsDto> dto ,String token);
    
} 