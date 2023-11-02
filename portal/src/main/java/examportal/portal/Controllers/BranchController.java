package examportal.portal.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Branch;
// import examportal.portal.Services.BranchService5;
import examportal.portal.ServicesImpl.BranchesServiceimpl;

@RestController
public class BranchController {
    Logger log   = LoggerFactory.getLogger("BranchController.class");
    @Autowired
    public BranchesServiceimpl services;

    @GetMapping("/branch")
    public ResponseEntity< List<Branch>> getBranch() {
        log.info("BranchController","getAll mathod is Started");
        List<Branch> list = services.getBranch();
        log.info("BranchController","getAll Method Ends");
        return new ResponseEntity<List<Branch>>(list,HttpStatus.OK);
    }

    @GetMapping("/branch/{getId}")
    public ResponseEntity<Branch> getBranchById(@PathVariable int getId) {
        log.info("BranchController","getBrachById Method Started");
        Branch b = services.geteBranchById(getId);
        log.info("BranchController","getBranchById Method Ends");
         return new ResponseEntity<Branch>(b,HttpStatus.OK);
    }

    // CREATE
    @PostMapping("/branch")
    public  ResponseEntity< Branch> postBranch(@RequestBody Branch user) {
           log.info("BranchController"," postBranch Method Started");
        Branch b = services.postBranch(user);
        log.info("BranchController","postBranch Method Ends");
        return new ResponseEntity<Branch>(b,HttpStatus.OK);
    }

    @PutMapping("/branch")
    public ResponseEntity<Branch>putBranch(@RequestBody Branch user) {
           log.info("BranchController","putBranch Method Started");
        Branch b = services.putBranch(user);
        log.info("BranchController","putBranch Method Ends");
        return new ResponseEntity<Branch>(b,HttpStatus.OK);
    }

    @DeleteMapping("/branch/{getId}")
    public void deleteBranchById(@PathVariable int getId) {
        services.deleteBranchById(getId);
    }
}
