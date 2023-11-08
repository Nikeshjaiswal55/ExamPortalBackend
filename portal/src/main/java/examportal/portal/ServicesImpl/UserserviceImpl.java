package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.User;
import examportal.portal.Payloads.EmailDetails;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.EmailService;
import examportal.portal.Services.UserService;
import jakarta.el.ELException;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailServices;

    Logger log = LoggerFactory.getLogger("UserServiceImpl");

    @Override
    public User createUser(User user) {

        log.info("userService , createUser Method Start");

        User findUser = this.userRepo.findByEmail(user.getEmail());

        if (findUser != null) {
            throw new ELException("User Already Exist With this Email " + user.getEmail());
        } else {
            User newuser = this.userRepo.save(user);
            // sendmail(newuser);
            
            EmailDetails ed = new EmailDetails(newuser.getEmail(),"Message Body", "Mail For Htmp Formated");
            
            emailServices.SendStyledMail(ed);
            log.info("userService , createUser Method Ends");

            return newuser;
        }

    }

  //  @Override
    // public String sendmail(User user) {

    //     log.info("userService , send mail Method Start");

    //     String message = "";

    //     String subject = "signin";

    //     String to = user.getEmail();

    //     // String from = "krishnas.bca2022@ssism.org";

    //     EmailDetails em = new EmailDetails(to, message, subject);

    //     emailServices.sendSimpleMail(em);

        // String testPasswordEncoded = user.getPassword();

        // user.setPassword(testPasswordEncoded);

       


    //     log.info("userService , sene mail Method End's");

    //     return "Email send sucess fully";

    // }

    @Override
    public List<User> getAllUser() {
        log.info("userService , getAllUser Method Start");
        List <User> u1 =  this.userRepo.findAll();
        log.info("userService , getAllUser Method Start");
       return u1;
    }

}