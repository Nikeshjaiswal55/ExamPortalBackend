package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Payloads.EmailDetails;
import examportal.portal.Entity.Mentor;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Repo.MentorRepo;
import examportal.portal.Services.EmailService;
import examportal.portal.Services.MentorService;

@Service
public class MentorSerivceImpl implements MentorService {

    @Autowired
    private MentorRepo mentorRepo;
    @Autowired
    private EmailService emailService;

    Logger log = LoggerFactory.getLogger("MentorSerivceImpl.java");

    @Override
    public Mentor addMentor(Mentor mentor) {
        log.info("MentorSerivceImpl , createMentor Method Start");
        // Email
        String email = mentor.getEmail();
         String sub = "Login Details";
         String msg = "Orgnization id = "+mentor.getOrgnizationId()+"\nUse id = "+mentor.getUserId()+"\nThankYou";
        EmailDetails ed = new EmailDetails(email,msg,sub);

        emailService.sendSimpleMail(ed);
        System.out.println("Email Successfully-------------------------------------");

        Mentor m1 = this.mentorRepo.save(mentor);

        log.info("MentorSerivceImpl , createMentor Method Ends");

        return m1;

    }

    @Override
    public List<Mentor> getAllMentors() {
        log.info("MentorSerivceImpl , getAllMentors Method Start");
        List<Mentor> allM = this.mentorRepo.findAll();
        log.info("MentorSerivceImpl , getAllMentors Method Ends");
        return allM;
    }

    @Override
    public Mentor getMentorById(String mentorid) {
        log.info("MentorSerivceImpl , getMentorById Method Start");
        Mentor m1 = this.mentorRepo.findById(mentorid).orElseThrow(() -> new ResourceNotFoundException("Mentor", "mentorId", mentorid));
        log.info("MentorSerivceImpl , getMentorById Method Ends");
        return m1;
    }

    @Override
    public Mentor updateMentor(Mentor mentor) {
        log.info("MentorSerivceImpl , updateMentor Method Start");
        Mentor m1 = this.mentorRepo.findById(mentor.getMentorId()).orElseThrow(()-> new ResourceNotFoundException("Mentor", "MentorId", mentor.getMentorId()));
        m1.setEmail(mentor.getEmail());
        m1.setMentorName(mentor.getMentorName());
        log.info("MentorSerivceImpl , updateMentor Method Ends");

        return m1;
        
    }

    @Override
    public String deleteMentor(String id) {
       log.info("MentorSerivceImpl , deleteMentor Method Start");

        this.mentorRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Mentor", "mentorId", id));
       
        log.info("MentorSerivceImpl , deleteMentor Method Ends");
       return "Record Deleted";
    }

    
}
