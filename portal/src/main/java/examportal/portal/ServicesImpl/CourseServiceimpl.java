package examportal.portal.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Course;
import examportal.portal.Repo.CourseRepo;
import examportal.portal.Services.CourseService;
@Service
public class CourseServiceimpl implements CourseService{
    @Autowired
    public CourseRepo courseRepo;
    
    @Override
    public List<Course>getCourse(){
        List<Course>course=courseRepo.findAll();
        return course;
    }

    @Override
    public Course getCourseById(String getId) {
        return courseRepo.findById(getId).orElseThrow();
    }

    // @Override
    // public Course addCourse(Course course){
    //     Course course2=courseRepo.save(course);
    //     return course2;
    // }
    
      @Override
    public Course postCourse(Course course){
       return this.courseRepo.save(course);
    }
    @Override
    public Course putCourse(Course course){
        return courseRepo.save(course);
    }

    @Override
    public void deleteCourseById(String getId){
        courseRepo.deleteById(getId);
    }

    // public List<Course> getAllCourses() {
    //     Liit
    //     return this.courseRepo.;
    // }

  
    
    // public examportal.portal.Controllers.List<Course> getAllCourses() {
    //     return null;
    // }

   
}
