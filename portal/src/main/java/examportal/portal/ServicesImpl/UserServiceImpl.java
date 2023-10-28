package examportal.portal.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.User;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.UserService;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(User user) {
      
        User newUser= this.userRepo.save(user);
        return newUser;
    }
    
}
