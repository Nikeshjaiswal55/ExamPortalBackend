package examportal.portal.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Entity.InvitedStudents;
import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.InvitationDto;
import examportal.portal.Repo.AssessmentRepo;
import examportal.portal.Repo.AttemptepaperRepo;
import examportal.portal.Repo.InvitationRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Services.StudentSevices;
import examportal.portal.Services.UserService;
import net.bytebuddy.utility.RandomString;

@Service
public class StudentServiceImpl implements StudentSevices {

    Logger log = LoggerFactory.getLogger("StudentServiceImpl.class");

    @Autowired
    private StudentRepo studentRepo;

    @Deprecated
    @Autowired
    private Auth0Service auth0Service;

    @Autowired
    private AssessmentRepo assessmentRepo;

    @Autowired
    private InvitationRepo invitationRepo;

    @Autowired
    private AttemptepaperRepo attemptepaperRepo;

    @Autowired 
    private UserService userService;

    @Override
    public List<Student> getAllStudents(Integer page, int size, String sortField, String sortOrder) {
        log.info("StudentServiceImpl , getAllStudent Method Start");
Sort sort =(sortOrder.equalsIgnoreCase("asc"))?Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable p=PageRequest.of(page,size,sort);
        Page<Student> pa =this.studentRepo.findAll(p);
        List<Student> list =pa.getContent();
        log.info("StudentServiceImpl , getAllStudent Method Ends");
        return list;
    }

    @Override
    public Student getSingleStudent(String id) {
        log.info("StudentServiceImpl , getSingleStudent Method Start");

        log.info("StudentServiceImpl , getSingleStudent Method Ends");

        return this.studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student ", "id", id));
    }

    @Override
    public Student updateStudent(Student student) {

        log.info("StudentServiceImpl , getSingleStudent Method Start");
        Student s = studentRepo.findById(student.getStudentid())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", student.getStudentid()));
        s.setEmail(student.getEmail());
        s.setName(student.getName());
        Student updateStudtnt = this.studentRepo.save(s);
        log.info("StudentServiceImpl , getSingleStudent Method Ends");
        return updateStudtnt;
    }

    @Override
    public String deleteStudent(String Id) {
        log.info("StudentServiceImpl , getSingleStudent Method Start");

        studentRepo.deleteById(Id);

        log.info("StudentServiceImpl , getSingleStudent Method Ends");
        return "Record Deleted";
    }

    @Override
    public List<Student> getAllStudentByPaperId(String paperId) {

        log.info("StudentServiceImpl , getAllStudentByPaperId Method Start");
        List<InvitedStudents> stude = this.invitationRepo.getAllStudentByPaperId(paperId);
        List<Student> students = new ArrayList<>();

        for (InvitedStudents invitedStudents : stude) {
            AttemptedPapers attemptedPapers = this.attemptepaperRepo
                    .getAllAttemptedPaperbyStudentID(invitedStudents.getStudentId(), paperId);
            Student s = this.studentRepo.findById(invitedStudents.getStudentId()).orElseThrow(
                    () -> new ResourceNotFoundException("Student", "StudentId", invitedStudents.getStudentId()));
            if (attemptedPapers != null) {
                s.set_attempted(true);
            }
            students.add(s);
        }

        return students;

    }

    @Deprecated
    @Override
    public String inviteStudents(InvitationDto dto) {

        for (String email : dto.getEmails()) {

            Student st = this.studentRepo.getszStudentByEmail(email);

            if (st != null) {
                handleExistingStudent(dto, st.getStudentid());
            } else {
                handleNewStudent(dto, email);
            }
        }
        return "Student added successfully";
    }

    public String handleExistingStudent(InvitationDto dto, String studentId) {

        InvitedStudents invitedStudents = new InvitedStudents();
        invitedStudents.setPaperId(dto.getPaperID());
        invitedStudents.setStudentId(studentId);
        invitationRepo.save(invitedStudents);

        Assessment assessment = createAssessment(dto, studentId);

        return "invited successfully";
    }

    public Assessment createAssessment(InvitationDto dto, String studentId) {
        Assessment assessment = new Assessment();
        assessment.setPaperId(dto.getPaperID());
        assessment.setUserId(studentId);
        assessment.setOrgnizationId(dto.getOrgnizationId());
        return assessmentRepo.save(assessment);
    }

    @Deprecated
    public void handleNewStudent(InvitationDto dto, String email) {
        try {

            String password = RandomString.make(12) +"K80";

            String response = this.auth0Service.createUser(email, password, dto.getToken());

            User newUser = new User();
            newUser.setUserId(response);
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setRole("Student");
            User user = this.userService.createUser(newUser);

            Student student = new Student();
            student.setStudentid(response);
            student.setEmail(email);
            student.setOrgnizationId(dto.getOrgnizationId());
            student.setPaperId(dto.getPaperID());
            Student newsStudent = this.studentRepo.save(student);
            
            handleExistingStudent(dto, newsStudent.getStudentid());
        } catch (Exception e) {
            log.error("Error inviting student: {}", e.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudentByName(String name) {
        log.info("StudentserviceIml, getAllUserByName method is start");
        List<Student> list=getAllStudentByName( name);
        if(list.isEmpty()){
            throw new NoSuchElementException(" thare are no student avalable in this name :"+name);
        }
        return list;
    }

}
