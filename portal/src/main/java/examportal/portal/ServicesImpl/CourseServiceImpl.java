package examportal.portal.ServicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Course;
import examportal.portal.Entity.User;
import examportal.portal.Repo.CourseRepo;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.CourseService;
import jakarta.el.ELException;

@Service
public class CourseServiceImpl implements CourseService {
  @Autowired
 private CourseRepo courseRepo;
 private UserRepo userRepo;

  Logger log = LoggerFactory.getLogger("CourseServiceimpl.class");

  @Override
  public List<Course> getAllCourse() {
   log.info("CourseServiceimpl,getCourse Method Start");
    List<Course> course = courseRepo.findAll();
    List<Course> courses = new ArrayList<>();
    for (Course course2 : course) {
       User user =  this.userRepo.findById(course2.getUserId()).orElseThrow(()-> new ELException("User Not found"));
       course2.setUserName(user.getName());
       courses.add(course2);
    }
    log.info("CourseServiceimpl,getCourse Method Ends");
    return courses;
  }

  @Override
  public Course getCourseById(String getId) {
    log.info("CourseServiceimpl,getCourseById Method Start");
    Course c = this.courseRepo.findById(getId).orElseThrow();
   User user =  this.userRepo.findById(c.getUserId()).orElseThrow();
    c.setUserName(user.getName());
    log.info("CourseServiceimpl,getCourseById Method Ends");

    return c;
  }

  @Override
  public Course addCourse(Course course) {
    log.info("CourseServiceimpl,addCourse Method Start");
    Course c = this.courseRepo.save(course);
    log.info("CourseServiceimpl,addCourse Method Ends");
    return c;
  }

  @Override
  public Course updateCourse(Course course) {
    log.info("CourseServiceimpl, updateCourse Method Start");
    Course c = this.courseRepo.findById(course.getCourse_id()).orElseThrow(() -> new ELException("Course Not Found"));
    c.setCourse_name(course.getCourse_name());
    c.setUserId(course.getUserId());
    log.info("CourseServiceimpl, updateCourse Method Ends");
    return courseRepo.save(course);
  }

  @Override
  public void deleteCourseById(String getId) {
    log.info("CourseServiceimpl, deleteCourse Method Start");
    courseRepo.deleteById(getId);
    log.info("CourseServiceimpl, deleteCourse Method Ends");
  }

}
