package examportal.portal.Services;

import java.util.List;
<<<<<<< HEAD

import org.springframework.stereotype.Service;

import examportal.portal.Entity.User;
import examportal.portal.Payloads.userDto;
@Service
=======
import examportal.portal.Entity.User;
>>>>>>> krishna

public  interface UserService {
    
    User createUser(User user);
    
    List<User>getAllUser(Integer page,Integer size, String sortField, String sortOrder);

    User getUserById(String userId);
 List<User>getAllUserByName(String name);
    
}