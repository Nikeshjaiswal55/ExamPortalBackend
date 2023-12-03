
package examportal.portal.ServicesImpl;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import examportal.portal.Entity.Course;
import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.CourseDto;
import examportal.portal.Payloads.EmailsDto;
import examportal.portal.Repo.CourseRepo;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.CourseService;
import jakarta.el.ELException;
import net.bytebuddy.utility.RandomString;

@Service
public class CourseServiceImpl implements CourseService {
  @Autowired
  private CourseRepo courseRepo;
  @Autowired
  private UserRepo userRepo;

  @Deprecated
  @Autowired
  private Auth0Service auth0Service;

  Logger log = LoggerFactory.getLogger("CourseServiceimpl.class");

  @Override
  public List<Course> getAllCourse(Integer pageNumber) {
    log.info("CourseServiceimpl,getCourse Method Start");
  
    Integer pageSize = 2;  
    Sort s = Sort.by("userId").ascending();
    Pageable p = PageRequest.of(pageNumber, pageSize,s);
    Page<Course> page = courseRepo.findAll(p);
    List<Course> courseAll = page.getContent();
    System.out.println(courseAll.size());
    System.out.println(page.isLast());
    List<Course> courses = new ArrayList<>();
    for (Course course2 : courseAll) {
      User user = this.userRepo.findById(course2.getUserId())
          .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", course2.getUserId()));
      course2.setUserName(user.getName());
      courses.add(course2);
    }
    log.info("CourseServiceimpl,getCourse Method Ends");
    return courses;
  }

  @Override
  public Course getCourseByCouseId(String getId) {
    log.info("CourseServiceimpl,getCourseById Method Start");
    Course c = this.courseRepo.findById(getId)
        .orElseThrow(() -> new ResourceNotFoundException("Course", "CourseId", getId));
    User user = this.userRepo.findById(c.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", c.getUserId()));
    c.setUserName(user.getName());
    log.info("CourseServiceimpl,getCourseById Method Ends");

    return c;
  }

  @Override
  @Deprecated
  public Course addCourse(CourseDto course) {

    log.info("CourseServiceimpl,addCourse Method Start");
    User us = userRepo.findById(course.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", course.getUserId()));
    String response = "";

    Course c = new Course();
    c.setCourse_name(course.getCourse_name());
    c.setUserId(course.getUserId());
    c.setUserName(us.getName());

    List<EmailsDto> dtos = course.getEmailsDto();
    for (EmailsDto email :dtos) {

      String password = RandomString.make(8) + email.getEmail();
      User user = userRepo.findByEmail(email.getEmail());

      if (user != null) {
        System.out.println("User Allready Exist");

      } else {

        try {
          System.out.println("+++++++++++Auth0Service Method Enter");
          response = this.auth0Service.createUser(email.getEmail(), password, course.getToken());
          System.out.println("UserID++++++++++" + response);
          // res = userId
          User use = new User();
          use.setUserId(response);
          use.setEmail(email.getEmail());
          use.setPassword(password);
          use.setRole("Student");
          User savedUser= this.userRepo.save(use);

          Student student = new Student();

          student.setBranch(email.getBranch());
          student.setName(email.getName());
          student.setEmail(email.getEmail());
          student.setOrgnizationId(course.getOrgnizationId());
          student.setStudentid(savedUser.getUserId());

        } catch (Exception e) {

          e.printStackTrace();
        }

      }

    }

    this.courseRepo.save(c);
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
