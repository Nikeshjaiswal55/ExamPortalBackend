package examportal.portal.Controllers;

import examportal.portal.Entity.Course;
import examportal.portal.Payloads.CourseDto;
import examportal.portal.Repo.CourseRepo;
import examportal.portal.Services.CourseService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestParam(name = "page", defaultValue = "0",required = false) Integer page,
    @RequestParam(name = "size", defaultValue = "10",required = false) Integer size,
    @RequestParam(name = "sortField", defaultValue = "email",required = false) String sortField,
    @RequestParam(name = "sortOrder", defaultValue = "asc",required = false) String sortOrder
  ) {
    log.info("CourseController,getCourse Method Start");
    
    List<Course> l = courseService.getAllCourse(page,size,sortField,sortOrder);

    log.info("CourseController,getCourse Method Ends");
    return new ResponseEntity<List<Course>>(l, HttpStatus.OK);
  }

  //Get Course by courseId
  @GetMapping("/course/{getId}")
  public ResponseEntity<Course> getCourseById(@PathVariable String getId) {
    log.info("CourseController,getCourseById Method Start");
    Course list = courseService.getCourseByCouseId(getId);
    log.info("CourseController,getCourseById Method Ends");
    return new ResponseEntity<Course>(list, HttpStatus.OK);
  }
  //get All Student by name
  @GetMapping("/GetAllCourseByName")
  public ResponseEntity<List<Course>> getAllCourseByName( @RequestParam(name = "name", defaultValue = "null",required = false) String name) {
    log.info("CourseController,getCourseById Method Start");
    List<Course> list = courseService.getAllCourseByStudentName(name);
    log.info("CourseController,getCourseById Method Ends");
    return new ResponseEntity<List<Course>>(list, HttpStatus.OK);
  }

  // Get Course by UserId
  @GetMapping("/course/byUserId/{userId}")
  public ResponseEntity<List<Course>> getCourseByUserId(@PathVariable String userId) {
    log.info("CourseController,getCourseById Method Start");
    List<Course> ls = courseRepo.getCourseByUseId(userId);
    log.info("CourseController,getCourseById Method Ends");
    return new ResponseEntity<List<Course>>(ls, HttpStatus.OK);
  }

  //create 
  @PostMapping("/course/create")
  public ResponseEntity<Course> addCourses(@RequestBody CourseDto course,HttpServletRequest request) {
    log.info("CourseController,addCourses Method Start");
    String token = request.getHeader("Authorization");
    course.setToken(token);
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
}
