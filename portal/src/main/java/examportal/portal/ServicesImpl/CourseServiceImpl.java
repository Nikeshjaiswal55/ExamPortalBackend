package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Course;
import examportal.portal.Repo.CourseRepo;
// import examportal.portal.Repo.Servicerepo;
import examportal.portal.Services.CourseService;
import jakarta.el.ELException;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
      public CourseRepo courseRepo;


    Logger log = LoggerFactory.getLogger("CourseServiceimpl.class");
    
    @Override
    public List<Course>getCourse(){
      log.info("Find All mthod is Strtimg ");
        List<Course>course=courseRepo.findAll();
        log.info(" Find All Mathod is Closing ");
        return course;
      }


      
    @Override
    public Course getCourseById(String getId) {
      log.info(" getCourseById  method is string ");
        Course c = this.courseRepo.findById(getId).orElseThrow();
      log.info(" getCoursebyid method is closing");

        return c;
    }


      @Override
      public Course addCourse(Course course){
        log.info("addCourse method is string");
        Course c=this.courseRepo.save(course);
        log.info(" addCourse method is closing");
         return c;
      }

     
      @Override
      public Course putCourse(Course course){
        log.info("PutCourse method is String");
          Course c= this.courseRepo.findById(course.getId()).orElseThrow(()-> new ELException("Course Not Found"));
          c.setCname(course.getCname());
          c.setUserId(course.getUserId());
        log.info("putCourse methos is closing");
          return courseRepo.save(course);
      }
  
      @Override
      public void deleteCourseById(String getId){
        log.info("delete method id string");
          courseRepo.deleteById(getId);
          log.info("delete methos is closing");
      }
  



    }