package examportal.portal.ServicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.User;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.EmailServices;
import examportal.portal.Services.UserService;

@Service
public class UserserviceImpl implements UserService {

 @Autowired
 private UserRepo userRepo;

 @Autowired
 private EmailServices emailServices;

 
 Logger log = LoggerFactory.getLogger("userServiceImpl");

    @Override
    public User createUser(User user) {

        log.info("userService , createUser Method Start");
        User newuser = this.userRepo.save(user);
        sendmail(newuser);
        log.info("userService , createUser Method Ends");
        return newuser;
    }

    @Override
    public String sendmail(User user) {

        String message = "This is your password for login "+ user.getPassword();

        String subject = "signin";
    
        String to = user.getEmail();
            
        String from = "mohammadm.bsccs2021@ssism.org";
            
        emailServices.sendEmail(from,subject, message, to);
    
        String testPasswordEncoded =user.getPassword();
    
        user.setPassword(testPasswordEncoded);

        User save = this.userRepo.save(user);

        System.out.println(save);

        return "Email send sucess fully";
        
    }
    
}
