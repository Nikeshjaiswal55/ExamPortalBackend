
package examportal.portal.ServicesImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import examportal.portal.Entity.Course;
import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.EmailsDto;
import examportal.portal.Payloads.PaginationDto;
import examportal.portal.Repo.CourseRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Response.CourseResponce;
import examportal.portal.Services.CourseService;
import jakarta.el.ELException;
import net.bytebuddy.utility.RandomString;

@Service
public class CourseServiceImpl implements CourseService {
  @Autowired
  private CourseRepo courseRepo;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private StudentRepo studentRepo;

  @Deprecated
  @Autowired
  private Auth0Service auth0Service;

  Logger log = LoggerFactory.getLogger("CourseServiceimpl");

  @Override
  public List<Course> getAllCourse(Integer pageNumber, int size, String sortField, String sortOrder) {
    log.info("CourseServiceimpl,getAllCourse Method Start");

    Sort s = (sortOrder.equalsIgnoreCase("ASC")) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    Pageable p = PageRequest.of(pageNumber, size, s);
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
    log.info("CourseServiceimpl,getAllCourse Method Ends");
    return courses;
  }

  @Override
  public Course getCourseByCouseId(String getId) {
    log.info("CourseServiceimpl,getCourseByCouseId Method Start");
    Course c = this.courseRepo.findById(getId)
        .orElseThrow(() -> new ResourceNotFoundException("Course", "CourseId", getId));
    User user = this.userRepo.findById(c.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", c.getUserId()));
    c.setUserName(user.getName());
    log.info("CourseServiceimpl,getCourseByCouseId Method Ends");

    return c;
  }

  @Override
  @Deprecated
  public Course addCourse(Course course) {

    log.info("CourseServiceimpl,addCourse Method Start");
    User us = userRepo.findById(course.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", course.getUserId()));

    Course c = new Course();
    c.setCourse_name(course.getCourse_name());
    c.setUserId(course.getUserId());
    c.setUserName(us.getName());
    c.setDuration(course.getDuration());
    this.courseRepo.save(c);

    log.info("CourseServiceimpl,addCourse Method Ends");
    return c;

  }

  @Async
  @Deprecated
  @Override
  public CompletableFuture<String> creatingStudentInBackGround(List<EmailsDto> dto, String token) {
    log.info("CourseServiceimpl,creatingStudentInBackGround Method Start");
    Course course = this.courseRepo.findById(dto.get(0).getCourseId())
        .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", dto.get(0).getCourseId()));

    for (EmailsDto email : dto) {
      String response = "";
      String password = RandomString.make(12) + "K80";
      Student st = this.studentRepo.getszStudentByEmail(email.getEmail());

      if (st != null) {
        System.out.println("User Allready Exist");

      } else {

        try {
          response = this.auth0Service.createUser(email.getEmail(), password, token);
          // res = userI

        } catch (Exception e) {

          e.printStackTrace();
        }

        User use = new User();
        use.setUserId(response);
        use.setEmail(email.getEmail());
        use.setPassword(password);
        use.setRole("Student");
        User savedUser = this.userRepo.save(use);

        Student student = new Student();
        student.setBranch(email.getCourseId());
        student.setYear(email.getYear());
        student.setEmail(email.getEmail());
        student.setOrgnizationId(email.getOrgnizationId());
        student.setStudentid(savedUser.getUserId());
        Student savedst = this.studentRepo.save(student);

      }
    }
    log.info("CourseServiceimpl,creatingStudentInBackGround Method End");
    return CompletableFuture.completedFuture("Student are creating in background");

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
  public String deleteCourseById(String getId, String token) throws IOException {
    log.info("CourseServiceImpl, deleteCourse Method Start");
    String tokenUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com/oauth/token";
    MultiValueMap<String, String> tokenRequest = new LinkedMultiValueMap<>();
    tokenRequest.add("grant_type", "client_credentials");
    tokenRequest.add("client_id", "vD5PYsjkk2y9676Uee5UoXtb4rH4Svx5");
    tokenRequest.add("client_secret", "P1Gtft8O_lf9DdCEJ9filW3bYcSymhrTp72gw8OAy1yOfg_bp6wHIkKyJ1nb568H");
    tokenRequest.add("audience", "https://dev-uil1ecwkoehr31jg.us.auth0.com/api/v2/");

    RestTemplate restTemplate = new RestTemplate();
    String managementApiToken = restTemplate.postForObject(tokenUrl, tokenRequest, String.class);;
    // System.out.println(managementApiToken+"//////////////////////////////////////");
    JSONObject jsonObject = new JSONObject(managementApiToken);

    // Extract the access_token
    String accessToken = jsonObject.getString("access_token");
    System.out.println(accessToken+"===============================================");

        // Get the user_id using the email address
    // String Id = "auth0|65d04e77a259fd3e778e07f0";
    List<Student> students = this.studentRepo.getAllStudentBYBranch(getId);
    System.out.println(students.size()+"sixe=========");

    for (Student student : students) {
      System.out.println("entered in  loooop===========================");
      if (student.getStudentid() != null) {
        System.out.println("enterd in if====");
          String Id = "auth0|" + student.getStudentid();
          String apiUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com/api/v2/users/" + Id;

          HttpHeaders deleteHeaders = new HttpHeaders();
          deleteHeaders.add("Authorization", "Bearer " + accessToken);

          restTemplate.exchange(apiUrl, HttpMethod.DELETE, new HttpEntity<>(deleteHeaders), String.class);
          System.out.println("User with email deleted successfully");
          this.studentRepo.deleteById(student.getStudentid());
          this.userRepo.deleteById(student.getStudentid());
          // return only after successful deletio
      }
      this.courseRepo.deleteById(getId);
  }

  return "record deleted successfully";
    // if (Id != null) {
    //   String apiUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com/api/v2/users/" +Id;

    //   // Prepare headers for the delete request
    //   HttpHeaders deleteHeaders = new HttpHeaders();
    //   deleteHeaders.add("Authorization", "Bearer "+accessToken);
    //   // Delete the user using the obtained token
    //   restTemplate.exchange(apiUrl, HttpMethod.DELETE, new HttpEntity<>(deleteHeaders), String.class);
    //   System.out.println("User with email  deleted successfully");
    //   return "deleted successfully";
    // } else {
    //   System.out.println("User with email not found.");
    //   return "failed to deleted";
    // }
  }

  @Override
  public CourseResponce getAllCourseByUserId(String userId, PaginationDto dto) {

    log.info("CourseServiceimpl, getAllCourseByUserId Method Start");
    Sort s = (dto.getSortDirection().equalsIgnoreCase("ASC")) ? Sort.by(dto.getProperty()).ascending()
        : Sort.by(dto.getProperty()).descending();
    Pageable p = PageRequest.of(dto.getPageNo(), dto.getPageSize(), s);

    Page<Course> page = courseRepo.getCourseByUseId(userId, p);
    List<Course> list = page.getContent();

    CourseResponce courseResponce = new CourseResponce();
    courseResponce.setCurrentPage(page.getNumber() + 1);
    courseResponce.setData(list);
    courseResponce.setIslastPage(page.isLast());
    courseResponce.setPagesize(page.getSize());
    courseResponce.setTotalElements(page.getTotalElements());
    courseResponce.setTotalPages(page.getTotalPages());
    log.info("CourseServiceimpl, getAllCourseByUserId Method End");
    return courseResponce;
  }
}

// @Override
// public String deleteCourseById(String userId,String token) {

// // Request a Management API token
// String tokenUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com/oauth/token";
// MultiValueMap<String, String> tokenRequest = new LinkedMultiValueMap<>();
// tokenRequest.add("grant_type", "client_credentials");
// tokenRequest.add("client_id", "HFjnwkNDl3VtcyC83VfiGWtmLXBT6Pvz");
// tokenRequest.add("client_secret",
// "gxmU3TaCMa5UBEl0R6cVspXn31yxMMV8OleGBYLU16cp6BpewmtJIQh0izcA4oNb");
// tokenRequest.add("audience",
// "https://dev-uil1ecwkoehr31jg.us.auth0.com/api/v2/");

// RestTemplate restTemplate = new RestTemplate();
// String managementApiToken = restTemplate.postForObject(tokenUrl,
// tokenRequest, String.class);

// JSONObject jsonObject = new JSONObject(managementApiToken);

// // Extract the access_token
// String accessToken = token;
// // Get the user_id using the email address
// String Id = userId;

// if (Id != null) {

// String apiUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com/api/v2/users/" +
// userId;

// // Prepare headers for the delete request
// HttpHeaders deleteHeaders = new HttpHeaders();
// deleteHeaders.add("Authorization", "Bearer " + accessToken);

// // Delete the user using the obtained token
// restTemplate.exchange(apiUrl, HttpMethod.DELETE, new
// HttpEntity<>(deleteHeaders), String.class);
// System.out.println("User with email deleted successfully");
// return "deleted successfully";
// } else {
// System.out.println("User with email not found.");
// return "failed to deleted";
// }
// }