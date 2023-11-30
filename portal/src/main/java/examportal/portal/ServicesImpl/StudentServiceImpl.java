package examportal.portal.ServicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.PageableDto;
import examportal.portal.Payloads.StudentDto;
import examportal.portal.Payloads.userDto;
import examportal.portal.Repo.AssessmentRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Response.PageResponce;
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

        return this.studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student ", "id", id));
    }

    @Deprecated
    @Override
    public Student addStudent(StudentDto student) {

        log.info("StudentServiceImpl , addStudent Method Start");

        Student s = new Student();

        String response = "";

        for (String email : student.getEmail()) {

            String password = RandomString.make(8);

            User user = this.userRepo.findByEmail(email);

            if (user != null) {
                Assessment assessment = new Assessment();
                assessment.setPaperId(student.getPaperID());
                assessment.setUserId(user.getUserId());
                assessment.setOrgnizationId(student.getOrgnizationId());
                Assessment newaAssessment = this.assessmentRepo.save(assessment);
                System.out.println("my assment ============================" + newaAssessment);

                

            } else {

                try {
                    response = this.auth0Service.createUser(email, password, student.getToken());
                    System.out.println("My response============================" + response);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                User newUser = new User();
                newUser.setUserId(response) ;
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setRole("Student");
                userDto dto = this.mapper.map(newUser, userDto.class);
                User user2 = this.userService.createUser(dto);

                Assessment assessment = new Assessment();
                assessment.setPaperId(student.getPaperID());
                assessment.setUserId(user2.getUserId());
                assessment.setOrgnizationId(student.getOrgnizationId());
                Assessment newAssessment = this.assessmentRepo.save(assessment);

                System.out.println("my assment ============================" + newAssessment);


                s.setEmail(email);
                s.setStudentid(response);
                s.setOrgnizationId(student.getOrgnizationId());
                this.studentRepo.save(s);
            }

        }

        log.info("StudentServiceImpl , addStudent Method Ends");

        return s;

    }

    @Override
    public Student updateStudent(Student student) {

        log.info("StudentServiceImpl , getSingleStudent Method Start");
        Student s = studentRepo.findById(student.getStudentid()).orElseThrow(() -> new ResourceNotFoundException("Student", "id", student.getStudentid()));
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


    public PageResponce getAllStudentByPaperId(String paperId , PageableDto dto){

          Sort sort;
        
        if(dto.getSortDirection().equals("DESC")){
            sort = Sort.by(dto.getProperty()).descending();
         
        }else{
            sort = Sort.by(dto.getProperty()).ascending();    
        }
        Pageable p = PageRequest.of(dto.getPageNo(), dto.getPageSize(), sort);
        
        Page<Student> st = studentRepo.findByPaperId(paperId, p);
        
        
        // List<Student> student=st.getContent();
        PageResponce pr = new PageResponce();
        pr.setContent_Student(st.getContent());
        pr.setPage(st.getNumber()+1);
        pr.setTotalElements(st.getTotalElements());
        pr.setTotalPages(st.getTotalPages());
        pr.setPagesize(st.getSize());
        pr.setIslastPage(st.isLast());
        pr.setSortby(dto.getProperty());
        pr.setSortDirection(dto.getSortDirection());
        return pr ;
        
    }
  

}
