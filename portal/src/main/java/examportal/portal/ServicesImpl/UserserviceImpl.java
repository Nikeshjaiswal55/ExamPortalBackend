package examportal.portal.ServicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.User;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.UserService;

@Service
public class UserserviceImpl implements UserService {

 @Autowired
 private UserRepo userRepo;

 Logger log = LoggerFactory.getLogger("userServiceImpl");

    @Override
    public User createUser(User user) {

        log.info("userService , createUser Method Start");
        User newuser = this.userRepo.save(user);
        log.info("userService , createUser Method Ends");
        return newuser;
    }
    
}
