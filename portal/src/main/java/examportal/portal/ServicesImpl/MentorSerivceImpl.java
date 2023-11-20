package examportal.portal.ServicesImpl;

import java.util.List;

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
    public Mentor getMentorById(String mentorid) {
        Mentor m1 = this.mentorRepo.findById(mentorid).orElseThrow(() -> new ResourceNotFoundException("Mentor", "mentorId", mentorid));;

        return m1;
    }

    @Override
    public Mentor updateMentor(Mentor mentor) {
        
        Mentor m1 = this.mentorRepo.findById(mentor.getMentorId()).orElseThrow(()-> new ResourceNotFoundException("Mentor", "MentorId", mentor.getMentorId()));
        m1.setEmail(mentor.getEmail());
        m1.setMentorName(mentor.getMentorName());

        return m1;
        
    }

    @Override
    public String deleteMentor(String id) {
       
        this.mentorRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Mentor", "mentorId", id));

       return "Record Deleted";
    }

    
}
