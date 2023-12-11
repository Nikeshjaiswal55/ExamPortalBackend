package examportal.portal.Services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;

import examportal.portal.Entity.Student;
import examportal.portal.Payloads.InvitationDto;

public interface StudentSevices {

    // 1. getting All Students
    List<Student> getAllStudents(Integer pageNumber, int size, String sortField, String sortOrder);

    // 2. getting A single Student by Id
    Student getSingleStudent(String id);

    Student updateStudent(Student student);

    // 5. Deleting an existing Student by Id
    String deleteStudent(String studentId);

    List<Student> getAllStudentByPaperId(String paperId);

    @Async
    CompletableFuture<String> inviteStudents(InvitationDto dto);

    List<Long> getCountOfStudentAndPaperBy_OGId(String orgnizationId);

    // getAllUserByName
    List<Student> getAllStudentByName(String name);

}
