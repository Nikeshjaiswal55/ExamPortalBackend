package examportal.portal.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import examportal.portal.Entity.Branch;
import examportal.portal.Services.BranchService;

@RestController
@CrossOrigin(origins = "*")
public class BranchController {
    @Autowired
  public BranchService branchService;

  Logger log = LoggerFactory.getLogger("BranchController.class");

  @GetMapping("/Branch/getAll")
  public ResponseEntity<List<Branch>> getBranchs() {
    log.info("BranchController,getBranch Method Start");

    List<Branch> l =branchService.getAllBranch();

    log.info("BranchController,getBranch Method Ends");
    return new ResponseEntity<List<Branch>>(l, HttpStatus.OK);
  }

  @GetMapping("/Branch/{getId}")
  public ResponseEntity<Branch> getBranchById(@PathVariable String getId) {
    log.info("BranchController,getBranchById Method Start");
   Branch list =branchService.getBranchById(getId);
    log.info("BranchController,getBranchById Method Ends");
    return new ResponseEntity<Branch>(list, HttpStatus.OK);
  }

  //create 
  @PostMapping("/Branch/create")
  public ResponseEntity<Branch> addBranchs(@RequestBody Branch  branch) {
    log.info("BranchController,addBranchs Method Start");
 Branch branch2 =branchService.addBranch(branch);
    log.info("BranchController,addBranchs Method Ends");
    return new ResponseEntity<Branch>(branch2, HttpStatus.OK);
  }

  @PutMapping("/Branch/update")
  public ResponseEntity<Branch> updateBranch(@RequestBody Branch branch) {
    log.info("BranchController,updateBranch Method Start");
    Branch branch3 =branchService.updateBranch(branch);
    log.info("BranchController,updateBranch Method Ends");
    return new ResponseEntity<Branch>(branch3, HttpStatus.OK);
  }

  @DeleteMapping("/Branch/{getId}")
  public String deleteBranch(@PathVariable String getId) {
    log.info("BranchController,deleteBranch Method Start");
   branchService.deleteBranchById(getId);
    log.info("BranchController,deleteBranch Method Ends");
    return "Record deleted";
  }
}
