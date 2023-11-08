package examportal.portal.Controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Course;
import examportal.portal.Services.CourseService;




@RestController
public class CourseController {
    @Autowired
   public CourseService courseService;
   Logger logger = LoggerFactory.getLogger(CourseController.class);

     @GetMapping("/course/getAll")
         public ResponseEntity<List<Course>>getcourse(){

            logger.info(" getAll Mathod is startong");

          List<Course>l= courseService.getCourse();

          logger.info(" GetAll mathod is closeing ");
            return new ResponseEntity<List<Course>>(l,HttpStatus.OK);

     }
      @GetMapping("/course/{getId}")
         public ResponseEntity<Course>getcourseById(@PathVariable String getId){
            logger.info(" get mathos is starting ");
          Course list= courseService.getCourseById(getId);
          logger.info("get Corse by is is closeing ");
            return new ResponseEntity<Course>(list,HttpStatus.OK);

     }
      
     
       @PostMapping("/course/create")
       public ResponseEntity<Course> postCourseById(@RequestBody Course course){
        logger.info("post corce by id is stsrting");
        Course course2=courseService.addCourse(course);
        logger.info("post course is closeing");
        return new ResponseEntity<Course>(course2,HttpStatus.OK);
       }



    @PutMapping("/course/update")
    public ResponseEntity<Course>putCourse(@RequestBody Course course){
        logger.info("put couse is Stsrting ");
        Course course3= courseService.putCourse(course);
        logger.info("put course is closing");
        return new ResponseEntity<Course>(course3,HttpStatus.OK);
    }

    @DeleteMapping("/course/{getId}")
    public void deleteCourseById(@PathVariable String getId){
        courseService.deleteCourseById(getId);
    }

}