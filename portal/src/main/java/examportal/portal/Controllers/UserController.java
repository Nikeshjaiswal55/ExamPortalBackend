package examportal.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.User;
import examportal.portal.Services.UserService;

@RestController
public class Usercontroller {
    
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createNewUser(@RequestBody User user)
    {
        User SavedUser = this.userService.createUser(user);
        return new ResponseEntity<>(SavedUser,HttpStatus.CREATED);
    }
}
