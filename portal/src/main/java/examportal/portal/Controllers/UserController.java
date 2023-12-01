package examportal.portal.Controllers;



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

import examportal.portal.Payloads.PageableDto;
import examportal.portal.Payloads.userDto;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Response.PageResponce;
import examportal.portal.Services.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo; 

    Logger log = LoggerFactory.getLogger("UserController.class");

    @PostMapping("/user")
    public ResponseEntity<User> createuser(@RequestBody userDto user) {
        log.info("UserController, createuser Method Start");

        User savedUser = this.userService.createUser(user);

        log.info("UserController, createUser Method Ends");
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/getAll")
    public  ResponseEntity<PageResponce> getAllUser(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer pagesize,
            @RequestParam(value = "sortDirection", defaultValue = "ASC", required = false) String sortDirection,
            @RequestParam(value = "property", defaultValue = "name", required = false) String property
            ){

        log.info("UserController, createUser Method Ends");

       PageResponce  us = this.userService.getAllUser(new PageableDto(pageNumber,pagesize,property,sortDirection));

        return new ResponseEntity<PageResponce>(us, HttpStatus.OK);

    }

    @GetMapping("user/ByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
         log.info("UserController, getUserByEmail Method Start");
        User u = userRepo.findByEmail(email);

        log.info("UserController, getUserByEmail Method Ends");
        return new ResponseEntity<User>(u, HttpStatus.OK);

    }
  
}
