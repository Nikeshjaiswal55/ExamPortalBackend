package examportal.portal.ServicesImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceAlreadyExistException;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.userDto;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.UserService;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

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
            newuser.setPassword(user.getPassword());
            User saveduser = this.userRepo.save(newuser);
            if(saveduser.getRole().equals("OG")){
                 String msg = "Orginzation Created Succesfully by"+newuser.getEmail();    
                 emailServiceImpl.sendFormateMail(newuser.getEmail(), msg, "Orgnizaton Creation", newuser.getRole());
            }
            log.info("userService , createUser Method Ends");

            return saveduser;
        }

    }


    @Override
    public List<User> getAllUser() {
        log.info("userService , getAllUser Method Start");
        List<User> u1 = this.userRepo.findAll();
        log.info("userService , getAllUser Method Start");
        return u1;
    }

    @Override
    public User getUserById(String userId) {
        log.info("userService , getAllUser Method Start");
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        log.info("userService , getAllUser Method Start");
        return user;
    }

}