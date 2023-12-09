package examportal.portal.Services;

import java.util.List;
import examportal.portal.Entity.Student;
import examportal.portal.Payloads.InvitationDto;


public interface StudentSevices{
    
    //1. getting All Students
    List<Student> getAllStudents(Integer pageNumber, int size, String sortField, String sortOrder);
    //2. getting A single Student by Id
    Student getSingleStudent(String id);
    //3. Adding a new student to the database
    // Student addStudent(StudentDto student);
    //4. Updating Existing Student by Id
    Student updateStudent( Student student);
    //5. Deleting an existing Student by Id
    String deleteStudent(String studentId);

    List<Student> getAllStudentByPaperId(String paperId);

    // String addStudentPaper(StudentDto studentDto);

     String inviteStudents(InvitationDto dto);
    //getAllUserByName  
    List<Student>getAllStudentByName(String name);
    

    
}
