package examportal.portal.Services;

import java.util.List;
import examportal.portal.Entity.User;

public  interface UserService {
    
    User createUser(User user);
    
    List<User>getAllUser(Integer page,Integer size, String sortField, String sortOrder);

    User getUserById(String userId);
 List<User>getAllUserByName(String name);
    
}