package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.User;
import examportal.portal.Payloads.userDto;

public  interface UserService {
    
    User createUser(userDto user);
    
    // String sendmail(User user);
    
    List<User>getAllUser(int page, int size, String sortField, String sortOrder);

    User getUserById(String userId);

    // String createAuth0User(userDto users);
}