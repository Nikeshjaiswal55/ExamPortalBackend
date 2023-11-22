package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.User;
import examportal.portal.Payloads.userDto;

public  interface UserService {
    
    User createUser(userDto user);
    
    // String sendmail(User user);
    
    List<User>getAllUser();

    // String createAuth0User(userDto users);
}