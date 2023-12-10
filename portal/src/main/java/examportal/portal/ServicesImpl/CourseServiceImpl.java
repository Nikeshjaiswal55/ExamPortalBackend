
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
<<<<<<< HEAD
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.CourseDto;
import examportal.portal.Repo.CourseRepo;
=======
import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.CourseDto;
import examportal.portal.Payloads.EmailsDto;
import examportal.portal.Payloads.PaginationDto;
import examportal.portal.Repo.CourseRepo;
import examportal.portal.Repo.StudentRepo;
>>>>>>> krishna
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.CourseService;
import jakarta.el.ELException;
import net.bytebuddy.utility.RandomString;

@Service
public class CourseServiceImpl implements CourseService {
  @Autowired
  private CourseRepo courseRepo;
<<<<<<< HEAD
  @Autowired
  private UserRepo userRepo;

=======
  
  @Autowired
  private UserRepo userRepo;

  @Autowired
  private StudentRepo studentRepo;


>>>>>>> krishna
  @Deprecated
  @Autowired
  private Auth0Service auth0Service;

  Logger log = LoggerFactory.getLogger("CourseServiceimpl.class");

  @Override
<<<<<<< HEAD
  public List<Course> getAllCourse(Integer pageNumber) {
    log.info("CourseServiceimpl,getCourse Method Start");
  
    Integer pageSize = 2;  
    Sort s = Sort.by("userId").ascending();
    Pageable p = PageRequest.of(pageNumber, pageSize,s);
=======
  public List<Course> getAllCourse(Integer pageNumber, int size, String sortField, String sortOrder) {
    log.info("CourseServiceimpl,getCourse Method Start");

    Sort s = (sortOrder.equalsIgnoreCase("ASC"))?Sort.by(sortField).ascending():Sort.by(sortField).descending();
    Pageable p = PageRequest.of(pageNumber, size, s);
>>>>>>> krishna
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
<<<<<<< HEAD
    User us = userRepo.findById(course.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", course.getUserId()));
=======
    User us = userRepo.findById(course.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", course.getUserId()));
>>>>>>> krishna
    String response = "";

    Course c = new Course();
    c.setCourse_name(course.getCourse_name());
    c.setUserId(course.getUserId());
    c.setUserName(us.getName());
<<<<<<< HEAD
<<<<<<< HEAD

    for (String i : course.getMails()) {

      String password = RandomString.make(8) + i;
      User user = userRepo.findByEmail(i);

      if (user != null) {
=======
    Course savedcourse = this.courseRepo.save(c);
=======
    c.setDuration(course.getDuration());
    this.courseRepo.save(c);
>>>>>>> krishna

    List<EmailsDto> dtos = course.getEmailsDto();

    for (EmailsDto email : dtos) {

      String password = RandomString.make(12) + "K80";
      Student st= this.studentRepo.getszStudentByEmail(email.getEmail());

      if (st!= null) {
>>>>>>> krishna
        System.out.println("User Allready Exist");

      } else {

        try {
<<<<<<< HEAD
          System.out.println("+++++++++++Auth0Service Method Enter");
          response = this.auth0Service.createUser(i, password, course.getToken());
          System.out.println("UserID++++++++++" + response);
          // res = userId
          User use = new User();
          use.setUserId(response);
          use.setEmail(i);
          use.setPassword(password);
          use.setRole("Student");
          userRepo.save(use);
=======
          response = this.auth0Service.createUser(email.getEmail(), password, course.getToken());
          // res = userId
          User use = new User();
          use.setUserId(response);
          use.setEmail(email.getEmail());
          use.setPassword(password);
          use.setRole("Student");
          User savedUser = this.userRepo.save(use);

          Student student = new Student();
          student.setBranch(email.getBranch()); 
          student.setName(email.getName());
          student.setEmail(email.getEmail());
          student.setOrgnizationId(course.getOrgnizationId());
          student.setStudentid(savedUser.getUserId());
          Student savedst =  this.studentRepo.save(student);
>>>>>>> krishna

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
<<<<<<< HEAD
=======
  }

<<<<<<< HEAD
  @Override
  public List<Course> getAllCourseByStudentName(String name) {
    log.info("CourseServiceimpl, getAllCourseByStudentName  Method Start");
    List<Course> list = courseRepo. getAllCourseByStudentName(name);
     if(list.isEmpty()){
      throw new NoSuchElementException("The Paper list is empty");
  }
    log.info("CourseServiceimpl, getAllCourseByStudentName  Method and");
   return list;
>>>>>>> krishna
  }
=======
  // @Override
  // public List<Course> getAllCourseByStudentName(String name) {
  //   log.info("CourseServiceimpl, getAllCourseByStudentName  Method Start");
  //   List<Course> list = courseRepo. getAllCourseByStudentName(name);
  //    if(list.isEmpty()){
  //     throw new NoSuchElementException("The Paper list is empty");
  // }
  //   log.info("CourseServiceimpl, getAllCourseByStudentName  Method and");
  //  return list;
// }
>>>>>>> krishna

@Override
  public List<Course>getAllCourseByUserId(String userId,PaginationDto dto){
    
    log.info("CourseServiceimpl, getAllCourseByUserId Method Start");
     Sort s = (dto.getSortDirection().equalsIgnoreCase("ASC"))?Sort.by(dto.getProperty()).ascending():Sort.by(dto.getProperty()).descending();
    Pageable p = PageRequest.of(dto.getPageNo(), dto.getPageSize(), s);
    
    List<Course> allCourses = courseRepo.getCourseByUseId(userId,p);

    
    return allCourses;
  }
}
  
  