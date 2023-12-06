package examportal.portal.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.User;
import examportal.portal.Services.UserService;

@RestController
@CrossOrigin(origins ="*")
public class UserController {

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger("UserController.class");

    @PostMapping("/user")
    public ResponseEntity<User> createuser(@RequestBody User user){
        log.info("UserController, createuser Method Start");

        User savedUser = this.userService.createUser(user); 
        
        log.info("UserController, createUser Method Ends");
        return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
    }

    @GetMapping("/user/getAll")
    public ResponseEntity<List<User>> getAllUser( @RequestParam(name = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10",required = false) Integer size,
            @RequestParam(name = "sortField", defaultValue = "name",required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "asc",required = false) String sortOrder){
        log.info("UserController, getAllUser Method Ends");

        List<User> us = this.userService.getAllUser(page,size,sortField,sortOrder); 

        return new ResponseEntity<List<User>>(us,HttpStatus.OK);
        
    }
    //  get all user by name
      @GetMapping("/user/getByName/{name}")
    public ResponseEntity<List<User>> getAllUserByName(@PathVariable String name){
        
        List<User> us = this.userService.getAllUserByName(name); 
        log.info("UserController, getAllUserByName Method Ends");

        return new ResponseEntity<List<User>>(us,HttpStatus.OK);
        
    }

      @GetMapping("/user/byid/{userId}")
    public ResponseEntity<User> getuserbyid(@PathVariable String userId){
        log.info("UserController, createUser Method Ends");

        User us = this.userService.getUserById(userId); 

        return new ResponseEntity<User>(us,HttpStatus.OK);
        
    }
}
