package examportal.portal.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import examportal.portal.Entity.User;
import examportal.portal.Payloads.userDto;
@Service

public  interface UserService {
    
    User createUser(userDto user);
    
    // String sendmail(User user);
    
    List<User>getAllUser();

    User getUserById(String userId);

    // String createAuth0User(userDto users);
}