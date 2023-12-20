package examportal.portal.ServicesImpl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.InvitedStudents;
import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.InvitationDto;
import examportal.portal.Repo.AssessmentRepo;
import examportal.portal.Repo.AttemptepaperRepo;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.InvitationRepo;
import examportal.portal.Repo.PaperRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Services.StudentSevices;
import examportal.portal.Services.UserService;
import net.bytebuddy.utility.RandomString;

@Service
public class StudentServiceImpl implements StudentSevices {

    Logger log = LoggerFactory.getLogger("StudentServiceImpl");

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

    @Autowired
    private PaperRepo paperRepo;

    @Autowired
    private ExamDetailsRepo examDetailsRepo;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Student> getAllStudents(Integer page, int size, String sortField, String sortOrder) {
        log.info("StudentServiceImpl , getAllStudent Method Start");
        Sort sort = (sortOrder.equalsIgnoreCase("asc")) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable p = PageRequest.of(page, size, sort);
        Page<Student> pa = this.studentRepo.findAll(p);
        List<Student> list = pa.getContent();
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

        log.info("StudentServiceImpl , updateStudent Method Start");
        Student s = studentRepo.findById(student.getStudentid())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", student.getStudentid()));
        s.setEmail(student.getEmail());
        s.setName(student.getName());
        Student updateStudtnt = this.studentRepo.save(s);
        log.info("StudentServiceImpl , updateStudent Method Ends");
        return updateStudtnt;
    }

    @Override
    public String deleteStudent(String Id) {
        log.info("StudentServiceImpl , deleteStudent Method Start");

        studentRepo.deleteById(Id);

        log.info("StudentServiceImpl , deleteStudent Method Ends");
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
        log.info("StudentServiceImpl , getAllStudentByPaperId Method End");
        return students;

    }

    @Deprecated
    @Override
    public String inviteStudents(InvitationDto dto) {
        log.info("StudentServiceImpl , inviteStudents Method Start");
        ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(dto.getPaperId());
        System.out.println(examDetails.getBranch()+"=====================================================================================================");
        
        if (examDetails.getBranch() !=null) {

            List<Student> students = this.studentRepo.getAllStudentBYBranchAndYear(examDetails.getBranch(),examDetails.getSession());
    
            for (Student student : students) {
                Student st = this.studentRepo.getszStudentByEmail(student.getEmail());
                if (st != null) {
                    handleExistingStudent(dto, st.getStudentid());
                }
            }
        } 
        else {
            System.out.println("i ma entetr in seconde conditions ==========================================");
            for (String email: dto.getEmails()) {
                Student st = this.studentRepo.getszStudentByEmail(email);
                if (st != null) {
                    handleExistingStudent(dto, st.getStudentid());
                }
                else {
                    handleNewStudent(dto, email);
                }
            }
        }
        log.info("StudentServiceImpl , inviteStudents Method End");
         return  "Student added successfully";
    }
    

    public String handleExistingStudent(InvitationDto dto, String studentId) {
            log.info("StudentServiceImpl , handleExistingStudent Method Start");
        InvitedStudents invitedStudents = new InvitedStudents();
        invitedStudents.setPaperId(dto.getPaperId());
        invitedStudents.setStudentId(studentId);
        invitationRepo.save(invitedStudents);

        Assessment assessment = createAssessment(dto, studentId);

        log.info("StudentServiceImpl , handleExistingStudent Method End");

        return "invited successfully";
    }

    public Assessment createAssessment(InvitationDto dto, String studentId) {
        log.info("StudentServiceImpl , createAssessment Method Start");
        Assessment assessment = new Assessment();
        assessment.setPaperId(dto.getPaperId());
        assessment.setUserId(studentId);
        assessment.setOrgnizationId(dto.getOrgnizationId());
        log.info("StudentServiceImpl , createAssessment Method End");
        return assessmentRepo.save(assessment);
    }

    public String encodeString(String inputString) {
        log.info("StudentServiceImpl , encodeString Method Start");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String encodedString = UriUtils.encode(objectMapper.writeValueAsString(inputString), StandardCharsets.UTF_8);
            log.info("StudentServiceImpl , encodeString Method End");
            return encodedString;
        } catch (Exception e) {
            // Handle the exception, e.g., log or throw a custom exception
            e.printStackTrace();
            log.info("StudentServiceImpl , encodeString Method End");
            return null;
        }


    }

    public String decodeString(String encodedString) {
        log.info("StudentServiceImpl , decodeString Method Start");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String decodedString = UriUtils.decode(encodedString, StandardCharsets.UTF_8);
            log.info("StudentServiceImpl , decodeString Method End");
            return decodedString;
        } catch (Exception e) {
            // Handle the exception, e.g., log or throw a custom exception
            e.printStackTrace();
            log.info("StudentServiceImpl , decodeString Method End");
            return null;
        }
    }

    @Deprecated
    @Override
    public String handleNewStudent(InvitationDto dto, String email) {
        try {
            log.info("StudentServiceImpl , handleNewStudent Method Start");
            String password = RandomString.make(12) + "K80";

            // String encode = encodeString(password);

            // String encode = this.passwordEncoder.encode(password);
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
            student.setPaperId(dto.getPaperId());
            Student newsStudent = this.studentRepo.save(student);
            log.info("StudentServiceImpl , handleNewStudent Method End");
            handleExistingStudent(dto, newsStudent.getStudentid());

        } catch (Exception e) {
            log.error("Error inviting student: {}", e.getMessage());
        }
        return "Inviting student";
    }

   
  

    @Override
    public List<Long> getCountOfStudentAndPaperBy_OGId(String orgnizationId) {
        log.info("StudentServiceImpl , getCountOfStudentAndPaperBy_OGId Method Start");
        List<Long> data = new ArrayList();

        Long total_student = this.studentRepo.countByOrganizationId(orgnizationId);
        data.add(total_student);
        Long total_paper = this.paperRepo.countByOrganizationId(orgnizationId);
        data.add(total_paper);
        log.info("StudentServiceImpl , getCountOfStudentAndPaperBy_OGId Method End");
        return data;
    }

    @Override
    public List<Student> getTopThreeStudentByOrgnization(String orgnizationId) {
        log.info("StudentServiceImpl , getTopThreeStudentByOrgnization Method Start");
        List<Student> allStudent = studentRepo.getTopThreeStudentByOrgnizationIdByMarks(orgnizationId);
        log.info("StudentServiceImpl , getTopThreeStudentByOrgnization Method End");
       
        return allStudent;
        
    }


    
    
}
