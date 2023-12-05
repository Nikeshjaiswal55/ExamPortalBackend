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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Student;
import examportal.portal.Payloads.InvitationDto;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Services.StudentSevices;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentSevices studentSevices;

    @Autowired
    private StudentRepo studentRepo;

    private Logger log = LoggerFactory.getLogger("StudentController.class");

    // Add student
    // @PostMapping("/student")
    // public ResponseEntity<String> addStudent(@RequestBody StudentDto student) {

    //     log.info("StudentController , addStudent Method Start");
    //     String savedStudent = this.studentSevices.inviteStudents(student);
    //     log.info("StudentController , addStudent Method Ends");
    //     return new ResponseEntity<String>(savedStudent, HttpStatus.CREATED);

    // }

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
    public ResponseEntity<List<Student>> getAllStudentByPaperIds(@PathVariable String paperId) {
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

     @GetMapping("/student/getAll/Bybranch/{branch}")
    public ResponseEntity<List<Student>> getAllStudentbybranch(@PathVariable String branch) {
        log.info("StudentController , getAllStudent Method Start");

        List<Student> list = this.studentRepo.getAllStudentBYBranch(branch);

        log.info("StudentController , getAllStudent Method Ends");
        return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
    }

    @PostMapping("/inviteStudents/")
    public ResponseEntity<String> InviteStudentsByEmail(@RequestBody InvitationDto invitationDto,HttpServletRequest request)
    {
        log.info("StudentController , InviteStudentsByEmail Method Start");
        invitationDto.setToken(request.getHeader("Authorization"));
        String response  = this.studentSevices.inviteStudents(invitationDto);

        log.info("StudentController , InviteStudentsByEmail Method end");

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
