package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.User;
import examportal.portal.Payloads.EmailDetails;
import examportal.portal.Payloads.userDto;
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

    @Deprecated
    @Autowired
    private Auth0Service auth0Service;

    Logger log = LoggerFactory.getLogger("userServiceImpl");

    @Deprecated
    @Override
    public User createUser(userDto user) {

        log.info("userService , createUser Method Start");

        User findUser = this.userRepo.findByEmail(user.getEmail());

        if (findUser != null) {
            throw new ELException("User Already Exist With this Email " + user.getEmail());
        } else {
            User newuser = new User();
            newuser.setEmail(user.getEmail());
            newuser.setName(user.getName());
            newuser.setPicture(user.getPicture());
            newuser.setSub(user.getSub());
            newuser.setUpdatedAt(user.getUpdatedAt());
             User saveduser =this.userRepo.save(newuser);
            try {
                this.auth0Service.createUser(saveduser.getEmail(), user.getName()+"123", user.getToken());
            } catch (Exception e) {
                e.printStackTrace();
            }
            sendmail(newuser);
            log.info("userService , createUser Method Ends");

            return newuser;
        }

    }

  //  @Override
    public String sendmail(User user) {

        log.info("userService , send mail Method Start");

        String message = "This is your password for login ";

        String subject = "signin";

        String to = user.getEmail();

        // String from = "krishnas.bca2022@ssism.org";

        EmailDetails em = new EmailDetails(to, message, subject);


        emailServices.sendSimpleMail(em);

        // String testPasswordEncoded = user.getPassword();

        // user.setPassword(testPasswordEncoded);

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

    // @Deprecated
    // @Override
    // public String createAuth0User(userDto userdDto) {

    //     log.info("userService , getAllUser Method Start");

    //     System.out.println("enter here in autho create method ==========================================");

    //     try {
    //         this.auth0Service.createUser(userdDto.getEmail(), userdDto.getName()+"123", userdDto.getToken());
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return " user created succesfully";
    // }

    

}