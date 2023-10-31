package examportal.portal.Services;

import examportal.portal.Entity.User;

public  interface UserService {
    User createUser(User user);
    String sendmail(User user);
}