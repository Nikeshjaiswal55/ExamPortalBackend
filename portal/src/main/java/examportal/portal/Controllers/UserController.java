package examportal.portal.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.User;
import examportal.portal.Services.UserService;

@RestController
public class UserController {

    Logger log = LoggerFactory.getLogger("UserController");
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createuser(@RequestBody User user)
    {
        log.info("UserController, createuser Method Start");
        User savedUser = this.userService.createUser(user);
        log.info("UserController, createuser Method Ends");
        return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
    }
    
}
