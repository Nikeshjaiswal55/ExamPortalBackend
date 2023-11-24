package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceAlreadyExistException;
import examportal.portal.Payloads.EmailDetails;
import examportal.portal.Payloads.userDto;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.EmailService;
import examportal.portal.Services.UserService;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailServices;


    
    Logger log = LoggerFactory.getLogger("userServiceImpl");

    @Deprecated
    @Override
    public User createUser(userDto user) {

        log.info("userService , createUser Method Start");

        User findUser = this.userRepo.findByEmail(user.getEmail());

        if (findUser != null) {
            throw new ResourceAlreadyExistException("user", "email", user.getEmail());
        } else {
            User newuser = new User();
            newuser.setUserId(user.getUserId());
            newuser.setEmail(user.getEmail());
            newuser.setName(user.getName());
            newuser.setPicture(user.getPicture());
            newuser.setUpdatedAt(user.getUpdatedAt());
            newuser.setRole(user.getRole());
             User saveduser =this.userRepo.save(newuser);
            // creating user in auth0 with api only for testing 
            // try {
            //     this.auth0Service.createUser(saveduser.getEmail(), user.getName()+"123", user.getToken());
            // } catch (Exception e) {
            //     e.printStackTrace();
            // }
            //  creating user in auth0 with api only for testing
            sendmail(saveduser);
            log.info("userService , createUser Method Ends");

            return saveduser;
        }
        
    }

  //  @Override
    public String sendmail(User user) {

        log.info("userService , send mail Method Start");
        String message = "This is your password for login in exam easy portal";
        String subject = "signin";
        String to = user.getEmail();
        EmailDetails em = new EmailDetails(to, message, subject);
        emailServices.sendSimpleMail(em);
        User save = this.userRepo.save(user);
        System.out.println(save);
        log.info("userService , sene mail Method End's");
        return "Email send sucess fully";
    }

    @Override
    public List<User> getAllUser() {
        log.info("userService , getAllUser Method Start");
        List <User> u1 =  this.userRepo.findAll();
        log.info("userService , getAllUser Method Start");
       return u1;
    }

}