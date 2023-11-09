package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Payloads.StudentDto;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.StudentSevices;
// import examportal.portal.Services.UserService;
import jakarta.el.ELException;
import net.bytebuddy.utility.RandomString;

@Service
public class StudentServiceImpl implements StudentSevices {

    Logger log = LoggerFactory.getLogger("StudentServiceImpl.class");

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserRepo userRepo;

    // @Autowired
    // private UserService userService;

    @Deprecated
    @Autowired
    private Auth0Service auth0Service;

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

        return this.studentRepo.findById(id).orElse(null);
    }

    @Deprecated
    @Override
    public Student addStudent(StudentDto student) {

        log.info("StudentServiceImpl , addStudent Method Start");

        Student s = new Student();

        for (String email : student.getEmail()) {

            String password = RandomString.make(8);

            User user= this.userRepo.findByEmail(email);
            System.out.println(user);

            try {
                this.auth0Service.createUser(email,password, student.getToken());
            } catch (Exception e) {
                e.printStackTrace();
            }

           s.setEmail(email);
           this.studentRepo.save(s);
        }

            log.info("StudentServiceImpl , addStudent Method Ends");

            return s;
    

    }

    @Override
    public Student updateStudent(Student student) {

        log.info("StudentServiceImpl , getSingleStudent Method Start");
        Student s = studentRepo.findById(student.getStudentid()).orElseThrow(() -> new ELException("User Not found"));
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

}
