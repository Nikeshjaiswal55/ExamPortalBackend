package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.User;

public  interface UserService {
    User createUser(User user);
    String sendmail(User user);
    List<User> getAllUser();
}