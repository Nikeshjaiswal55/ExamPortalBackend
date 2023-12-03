package examportal.portal.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.InvitedStudents;
import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.StudentDto;
import examportal.portal.Payloads.userDto;
import examportal.portal.Repo.AssessmentRepo;
import examportal.portal.Repo.InvitationRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.StudentSevices;
import examportal.portal.Services.UserService;
import net.bytebuddy.utility.RandomString;

@Service
public class StudentServiceImpl implements StudentSevices {

    Logger log = LoggerFactory.getLogger("StudentServiceImpl.class");

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Deprecated
    @Autowired
    private Auth0Service auth0Service;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AssessmentRepo assessmentRepo;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Autowired
    private InvitationRepo invitationRepo;

    @Override
    public List<Student> getAllStudents() {
        log.info("StudentServiceImpl , getAllStudent Method Start");

        log.info("StudentServiceImpl , getAllStudent Method Ends");
        return this.studentRepo.findAll();
    }

    @Override
    public Student getSingleStudent(String id) {
        log.info("StudentServiceImpl , getSingleStudent Method Start");

        log.info("StudentServiceImpl , getSingleStudent Method Ends");

        return this.studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student ", "id", id));
    }

    @Deprecated
    @Override
    public Student addStudent(StudentDto student) {

        log.info("StudentServiceImpl , addStudent Method Start");

        Student s = new Student();

        String response = "";

        String password = RandomString.make(8) + "K8085";
        System.out.println("\n\n\n++++++++++++++++++++++++++++++++++++!!!!!!!!!" + student.getEmail());

        for (String email : student.getEmail()) {

            User user = this.userRepo.findByEmail(email);

            if (user != null) {

                InvitedStudents invitedStudents = new InvitedStudents();
                invitedStudents.setPaperId(student.getPaperID());
                invitedStudents.setStudentId(user.getUserId());
                this.invitationRepo.save(invitedStudents);

                Assessment assessment = new Assessment();
                assessment.setPaperId(student.getPaperID());
                assessment.setUserId(user.getUserId());
                assessment.setOrgnizationId(student.getOrgnizationId());
                Assessment newaAssessment = this.assessmentRepo.save(assessment);
                System.out.println("my assment ============================" + newaAssessment);
                // send mail to the user with his/her credential

            } else {

                try {
                    response = this.auth0Service.createUser(email, password, student.getToken());
                    if (response != null) {
                        System.out.println("My response============================" + response);
                    } else {
                        throw new Exception("Auth0 UserCreation error");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                User newUser = new User();
                newUser.setUserId(response);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setRole("Student");
                userDto dto = this.mapper.map(newUser, userDto.class);
                User user2 = this.userService.createUser(dto);

                System.out.println("User Created by" + newUser.getEmail());

                InvitedStudents invitedStudents = new InvitedStudents();
                invitedStudents.setPaperId(student.getPaperID());
                invitedStudents.setStudentId(user2.getUserId());
                this.invitationRepo.save(invitedStudents);

                Assessment assessment = new Assessment();
                assessment.setPaperId(student.getPaperID());
                assessment.setUserId(response);
                assessment.setOrgnizationId(student.getOrgnizationId());
                Assessment newAssessment = this.assessmentRepo.save(assessment);

                System.out.println("my assment ============================" + newAssessment);

                s.setEmail(email);
                s.setStudentid(response);
                s.setOrgnizationId(student.getOrgnizationId());
                s.setPaperId(student.getPaperID());
                s.setYear(student.getYear());
                this.studentRepo.save(s);
            }

            // }
        }
        log.info("StudentServiceImpl , addStudent Method Ends");

        return s;

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
            Student s = this.studentRepo.findById(invitedStudents.getStudentId()).orElseThrow(
                    () -> new ResourceNotFoundException("Student", "StudentId", invitedStudents.getStudentId()));
             students.add(s);
        }
        return students;

    }

    @Deprecated
    @Override
    public String addStudentPaper(StudentDto studentdDto) {

        for (String email : studentdDto.getEmail()) {
            User user = this.userRepo.findByEmail(email);
            String password = RandomString.make(8);

            String response ="";
            if (user != null) {

                InvitedStudents invitedStudents = new InvitedStudents();
                invitedStudents.setPaperId(studentdDto.getPaperID());
                invitedStudents.setStudentId(user.getUserId());
                this.invitationRepo.save(invitedStudents);

                Assessment assessment = new Assessment();
                assessment.setPaperId(studentdDto.getPaperID());
                assessment.setUserId(user.getUserId());
                assessment.setOrgnizationId(studentdDto.getOrgnizationId());
                Assessment newaAssessment = this.assessmentRepo.save(assessment);
                System.out.println("my assment ============================" + newaAssessment);
            }
            else
            {
                
                try {
                    response = this.auth0Service.createUser(email, password, studentdDto.getToken());
                    if (response != null) {
                        System.out.println("My response============================" + response);
                    } else {
                        throw new Exception("Auth0 UserCreation error");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                User newUser = new User();
                newUser.setUserId(response);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setRole("Student");
                userDto dto = this.mapper.map(newUser, userDto.class);
                User user2 = this.userService.createUser(dto);

                 InvitedStudents invitedStudents = new InvitedStudents();
                invitedStudents.setPaperId(studentdDto.getPaperID());
                invitedStudents.setStudentId(user2.getUserId());
                this.invitationRepo.save(invitedStudents);

                Assessment assessment = new Assessment();
                assessment.setPaperId(studentdDto.getPaperID());
                assessment.setUserId(user2.getUserId());
                assessment.setOrgnizationId(studentdDto.getOrgnizationId());
                Assessment newaAssessment = this.assessmentRepo.save(assessment);
                System.out.println("my assment ============================" + newaAssessment);

                Student student = new Student();
                student.setStudentid(response);
                student.setEmail(email);
                student.setOrgnizationId(studentdDto.getOrgnizationId());
                student.setBranch(studentdDto.getBranch());
                student.setName(studentdDto.getName());
                student.setPaperId(studentdDto.getPaperID());
                student.setYear(studentdDto.getYear());
                Student newsStudent = this.studentRepo.save(student);

            }
        }
        return "Student added successfully";
    }

}
