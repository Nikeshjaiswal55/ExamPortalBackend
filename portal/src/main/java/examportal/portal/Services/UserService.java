package examportal.portal.Services;

import java.util.List;
import examportal.portal.Entity.User;
import examportal.portal.Payloads.userDto;

public  interface UserService {
    
    User createUser(userDto user);
    
    List<User>getAllUser();

    User getUserById(String userId);

    
}