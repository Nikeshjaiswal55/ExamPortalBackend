package examportal.portal.Controllers;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import examportal.portal.Entity.Course;
import examportal.portal.Services.CourseService;
//  import examportal.portal.ServicesImpl.CourseServiceimpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController

public class CourseController {
    @Autowired
    public CourseService courseService;
     
    @GetMapping("/Course")
    public ResponseEntity<List<Course>>getCourse(){
        List<Course>list= courseService.getCourse();
        return new ResponseEntity<List<Course>>(list,HttpStatus.OK);

    }
    @GetMapping("/Course/{getId}")
    public ResponseEntity<Course>getCourseById(@PathVariable String getId){
        Course course=courseService.getCourseById(getId);
        return new ResponseEntity<Course>(course,HttpStatus.OK);
    }
    
    // @PostMapping("/add/Course") 
    // public ResponseEntity<Course>postCourseById(@RequestBody Course course){
    //     System.out.println("my course name ============"+course.getCname());
    //     Course course2= courseService.addCourse(course);
    //     return new ResponseEntity<Course>(course2,HttpStatus.OK);
    // }

    @PostMapping("/Course")
    public ResponseEntity<Course>postCourseById(@RequestBody Course course){
        Course course2=courseService.postCourse(course);
        return new ResponseEntity<Course>(course2,HttpStatus.OK);
    }

    @PutMapping("/Course")
    public ResponseEntity<Course>putCourse(@RequestBody Course course){
        Course course3= courseService.putCourse(course);
        return new ResponseEntity<Course>(course3,HttpStatus.OK);
    }

    @DeleteMapping("/Course/{getId}")
    public void deleteCourseById(@PathVariable String getId){
        courseService.deleteCourseById(getId);
    }
}



