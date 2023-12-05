package examportal.portal.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import examportal.portal.Entity.Student;
// import java.util.List;

import examportal.portal.Payloads.PageableDto;
import examportal.portal.Payloads.StudentDto;
import examportal.portal.Response.PageResponce;


public interface StudentSevices{
    
    //1. getting All Students
    Page<Student> getAllStudents(Pageable pageable);
    //2. getting A single Student by Id
    Student getSingleStudent(String id);
    //3. Adding a new student to the database
    Student addStudent(StudentDto student);
    //4. Updating Existing Student by Id
    Student updateStudent( Student student);
    //5. Deleting an existing Student by Id
    String deleteStudent(String studentId);

    PageResponce getAllStudentByPaperId(String paperId, PageableDto dto);

    
}
