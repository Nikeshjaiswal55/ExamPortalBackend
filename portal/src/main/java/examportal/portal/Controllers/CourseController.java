package examportal.portal.Controllers;

import examportal.portal.Entity.Course;
import examportal.portal.Payloads.CourseDto;
import examportal.portal.Payloads.EmailsDto;
import examportal.portal.Payloads.PaginationDto;
import examportal.portal.Repo.CourseRepo;
import examportal.portal.Response.CourseResponce;
import examportal.portal.Services.CourseService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {

  @Autowired
  public CourseService courseService;

  @Autowired
  private CourseRepo courseRepo;

  Logger log = LoggerFactory.getLogger("CourseController.class");

  // Get All
  @GetMapping("/course/getAll")
  public ResponseEntity<List<Course>> getCourses(
      @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
      @RequestParam(name = "sortField", defaultValue = "course_name", required = false) String sortField,
      @RequestParam(name = "sortOrder", defaultValue = "asc", required = false) String sortOrder) {
    log.info("CourseController,getCourse Method Start");

    List<Course> l = courseService.getAllCourse(page, size, sortField, sortOrder);

    log.info("CourseController,getCourse Method Ends");
    return new ResponseEntity<List<Course>>(l, HttpStatus.OK);
  }

  // Get Course by courseId
  @GetMapping("/course/{getId}")
  public ResponseEntity<Course> getCourseById(@PathVariable String getId) {
    log.info("CourseController,getCourseById Method Start");
    Course list = courseService.getCourseByCouseId(getId);
    log.info("CourseController,getCourseById Method Ends");
    return new ResponseEntity<Course>(list, HttpStatus.OK);
  }

  // Get Course by UserId
  @GetMapping("/course/byUserId/{userId}")
  public ResponseEntity<CourseResponce> getCourseByUserId(@PathVariable String userId,
      @RequestParam(name = "page", defaultValue = "0", required = false) Integer pageNo,
      @RequestParam(name = "size", defaultValue = "10", required = false) Integer Pagesize,
      @RequestParam(name = "sortField", defaultValue = "course_name", required = false) String sortField,
      @RequestParam(name = "sortOrder", defaultValue = "ASC", required = false) String sortOrder) {

    log.info("CourseController,getCourseById Method Start");

    CourseResponce courseResponce = courseService.getAllCourseByUserId(userId,
        new PaginationDto(pageNo, Pagesize, sortField, sortOrder));
    log.info("CourseController,getCourseById Method Ends");
    return new ResponseEntity<CourseResponce>(courseResponce, HttpStatus.OK);
  }

  // create
  @PostMapping("/course/create")
  public ResponseEntity<Course> addCourses(@RequestBody Course course) {
    log.info("CourseController,addCourses Method Start");
    // String token = request.getHeader("Authorization");
    // course.setToken(token);
    Course course2 = courseService.addCourse(course);
    log.info("CourseController,addCourses Method Ends");
    return new ResponseEntity<Course>(course2, HttpStatus.OK);
  }

  @PutMapping("/course/update")
  public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
    log.info("CourseController,updateCourse Method Start");
    Course course3 = courseService.updateCourse(course);
    log.info("CourseController,updateCourse Method Ends");
    return new ResponseEntity<Course>(course3, HttpStatus.OK);
  }

  @DeleteMapping("/course/{getId}")
  public String deleteCourse(@PathVariable String getId) {
    log.info("CourseController,deleteCourse Method Start");
    courseService.deleteCourseById(getId);
    log.info("CourseController,deleteCourse Method Ends");
    return "Record deleted";
  }

  @GetMapping("/searchCouser")
  public ResponseEntity<List<Course>> searchCouse(
      @RequestParam(name = "course_Name", required = false) String course_Name,
      @RequestParam(name = "userName", required = false) String userName) {

    List<Course> c = courseRepo.SearchCouse(userName, course_Name);

    return new ResponseEntity<>(c, HttpStatus.OK);
  }

  @PostMapping("/creatStudentInBackgound")
  public String StudentCreatingProceeInBackground(@RequestBody List<EmailsDto> dto, HttpServletRequest request)
      throws ExecutionException, InterruptedException {

    String token = request.getHeader("Authorization");
    Future<String> future = courseService.creatingStudentInBackGround(dto, token);

    return "Background processing started Creating Student for Course : " + dto.get(0).getCourseId();
  }
}
