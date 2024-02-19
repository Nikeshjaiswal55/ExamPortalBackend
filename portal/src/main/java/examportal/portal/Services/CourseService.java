package examportal.portal.Services;

import java.io.IOException;
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

    String deleteCourseById(String getId,String token) throws IOException;

    CourseResponce getAllCourseByUserId(String userId,PaginationDto dto);

    Future<String> creatingStudentInBackGround(List<EmailsDto> dto ,String token);
    
} 