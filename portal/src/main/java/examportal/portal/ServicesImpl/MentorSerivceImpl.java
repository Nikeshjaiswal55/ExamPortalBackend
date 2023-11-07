package examportal.portal.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Payloads.EmailDetails;
import examportal.portal.Entity.Mentor;
import examportal.portal.Repo.MentorRepo;
import examportal.portal.Services.EmailService;
import examportal.portal.Services.MentorService;
import jakarta.el.ELException;

@Service
public class MentorSerivceImpl implements MentorService {

    @Autowired
    private MentorRepo mentorRepo;
    @Autowired
    private EmailService emailService;

    @Override
    public Mentor addMentor(Mentor mentor) {
        // Email
        String email = mentor.getEmail();
         String sub = "Login Details";
         String msg = "Orgnization id = "+mentor.getOrgnizationId()+"\nUse id = "+mentor.getUserId()+"\nThankYou";
        EmailDetails ed = new EmailDetails(email,msg,sub);

        emailService.sendSimpleMail(ed);
        System.out.println("Email Successfully-------------------------------------");

        Mentor m1 = this.mentorRepo.save(mentor);
       

        return m1;

    }

    @Override
    public List<Mentor> getAllMentors() {
        List<Mentor> allM = this.mentorRepo.findAll();
        return allM;
    }

    @Override
    public Mentor getMentorById(Mentor mentor) {
        Mentor m1 = this.mentorRepo.findById(mentor.getMentorId()).orElseThrow(() -> new ELException("Mentor Not Found"));;

        return m1;
    }

    @Override
    public Mentor updateMentor(Mentor mentor) {
        
        Mentor m1 = this.mentorRepo.findById(mentor.getMentorId()).orElseThrow(()-> new ELException("Mentor Not Found"));
        m1.setEmail(mentor.getEmail());
        m1.setMentorName(mentor.getMentorName());

        return m1;
        
    }

    @Override
    public String deleteMentor(String id) {
       
        this.mentorRepo.findById(id);

       return "Record Deleted";
    }

    @Override
    public Mentor getAllMentorById(String mentorId) {
        
        Mentor m1 = this.mentorRepo.findById(mentorId).orElseThrow(()->new ELException("Mentor Not Foud")); 
         
        return m1;
    }

    @Override
    public void deleteAllMentor() {
        this.mentorRepo.deleteAll();
        
    }
    
}
