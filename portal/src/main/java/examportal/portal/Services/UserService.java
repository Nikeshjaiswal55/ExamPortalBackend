package examportal.portal.Services;

import java.util.List;
import examportal.portal.Entity.User;

public  interface UserService {
    
    User createUser(User user);
    
    List<User>getAllUser();

    User getUserById(String userId);

    
}