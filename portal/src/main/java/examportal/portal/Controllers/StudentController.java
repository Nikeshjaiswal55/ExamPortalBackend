package examportal.portal.Controllers;


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

    // Get All Student By paperId
    @GetMapping("/student/GetAllByPaperId/{paperId}")
    public ResponseEntity<PageResponce> getAllStudentByPaperId(@PathVariable String paperId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "2", required = false) Integer pagesize,
            @RequestParam(value = "sortDirection", defaultValue = "ASC", required = false) String sortDirection,
            @RequestParam(value = "property", defaultValue = "email", required = false) String property) {
        log.info("StudentController , getAllStudent Method Start");

        PageResponce st = this.studentSevices.getAllStudentByPaperId(paperId,
                new PageableDto(pageNumber, pagesize, property, sortDirection));

        log.info("StudentController , getAllStudent Method Ends");
        return new ResponseEntity<PageResponce>(st, HttpStatus.OK);
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
