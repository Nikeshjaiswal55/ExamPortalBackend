package examportal.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.User;
import examportal.portal.Services.UserService;

@RestController
@RequestMapping("/exam-portal")
public class UserController {
    
    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseEntity<User>createUser(@RequestBody User user)
    {
        User savedUser = this.userService.createUser(user);
        return new ResponseEntity<User>(savedUser,HttpStatus.OK);
    }

}