package examportal.portal.Controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import examportal.portal.Entity.Student;

import examportal.portal.Payloads.StudentDto;
import examportal.portal.Repo.StudentRepo;

import examportal.portal.Services.StudentSevices;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentSevices studentSevices;

    @Autowired
    private StudentRepo studentRepo;

    private Logger log = LoggerFactory.getLogger("StudentController.class");

    // Add student
    @PostMapping("/student")
    public ResponseEntity<String> addStudent(@RequestBody StudentDto student) {

        log.info("StudentController , addStudent Method Start");
        String savedStudent = this.studentSevices.addStudentPaper(student);
        log.info("StudentController , addStudent Method Ends");
        return new ResponseEntity<String>(savedStudent, HttpStatus.CREATED);

    }

    // Getting All Student
     @GetMapping("/student/getAll")
    public ResponseEntity< Page<Student>> getAllStudents(
            @RequestParam(name = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10",required = false) Integer size,
            @RequestParam(name = "sortField", defaultValue = "id",required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "asc",required = false) String sortOrder) {
                if (!"asc".equalsIgnoreCase(sortOrder) && !"desc".equalsIgnoreCase(sortOrder)) {
                    return ResponseEntity.badRequest().build();
                }
        // Create a Pageable object for pagination and sorting
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortField));

        // Call the service method to get paginated and sorted students
       Page<Student> s= studentSevices.getAllStudents(pageable);
        return new ResponseEntity<Page<Student>>(s, HttpStatus.OK);
    }

    // getting Student By Id
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getAllStudentById(@PathVariable String studentId) {
        log.info("StudentController , getAllStudent Method Start");

        Student s = studentSevices.getSingleStudent(studentId);

        log.info("StudentController , getAllStudent Method Ends");
        return new ResponseEntity<Student>(s, HttpStatus.OK);
    }
    //get studet by name
     @GetMapping("/GetAllStudentByName/{name}")
    public ResponseEntity<List<Student>> getAllStudentByName(@PathVariable String name) {
        log.info("StudentController , getAllStudentByName Method Start");

        List<Student> st = this.studentSevices.getAllStudentsbyName(name);
        log.info("StudentController , getAllStudentByName Method Ends");
        return new ResponseEntity<List<Student>>(st, HttpStatus.OK);
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

}
