package examportal.portal.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.User;
import examportal.portal.Payloads.userDto;
import examportal.portal.Services.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger("UserController.class");

    @PostMapping("/user")
    public ResponseEntity<User> createuser(@RequestBody userDto user){
        log.info("UserController, createuser Method Start");

        User savedUser = this.userService.createUser(user);
        
        log.info("UserController, createUser Method Ends");
        return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
    }

    @GetMapping("/user/getAll")
    public ResponseEntity<List<User>> getAllUser(){
        log.info("UserController, createUser Method Ends");

        List<User> us = this.userService.getAllUser(); 

        return new ResponseEntity<List<User>>(us,HttpStatus.OK);
        
    }
}
