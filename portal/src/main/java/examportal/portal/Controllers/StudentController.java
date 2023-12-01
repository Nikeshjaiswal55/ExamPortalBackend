package examportal.portal.Controllers;

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
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Student;
import examportal.portal.Payloads.PageableDto;
import examportal.portal.Payloads.StudentDto;
import examportal.portal.Response.PageResponce;
import examportal.portal.Services.StudentSevices;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentSevices studentSevices;

    private Logger log = LoggerFactory.getLogger("StudentController.class");

    // Add student
    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto student) {

        log.info("StudentController , addStudent Method Start");
        Student savedStudent = this.studentSevices.addStudent(student);
        log.info("StudentController , addStudent Method Ends");
        return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);

    }

    // Getting All Student
    @GetMapping("/student/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        log.info("StudentController , getAllStudent Method Start");

        List<Student> list = this.studentSevices.getAllStudents();

        log.info("StudentController , getAllStudent Method Ends");
        return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
    }

    // getting Student By Id
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getAllStudentById(@PathVariable String studentId) {
        log.info("StudentController , getAllStudent Method Start");

        Student s = studentSevices.getSingleStudent(studentId);

        log.info("StudentController , getAllStudent Method Ends");
        return new ResponseEntity<Student>(s, HttpStatus.OK);
    }

    // Get All Student By paperId
    @GetMapping("/GetAllStudentByPaperId/{paperId}")
    public ResponseEntity<List<Student>> getAllStudentByPaperId(@PathVariable String paperId) {
        log.info("StudentController , getAllStudent Method Start");

        List<Student> st = this.studentSevices.getAllStudentByPaperId(paperId);
        log.info("StudentController , getAllStudent Method Ends");
        return new ResponseEntity<List<Student>>(st, HttpStatus.OK);
    }

    // updating a student
    @PutMapping("/student/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        log.info("StudentController , updateStudent Method Start");
        Student updatedStudent = studentSevices.updateStudent(student);
        log.info("StudentController , updateStudent Method Ends");
        return new ResponseEntity<Student>(updatedStudent, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/student/delete/{Id}")
    public String deleteStudent(@PathVariable String Id) {
        log.info("StudentController , DeleteStudent Method Start");
        String msg = studentSevices.deleteStudent(Id);
        log.info("StudentController , DeleteStudent Method Ends");
        return msg;

    }

}
