package examportal.portal.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Assessment;
import examportal.portal.Repo.AssessmentRepo;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AssessmentController {

    @Autowired
    private AssessmentRepo assessmentRepo;

    Logger log = LoggerFactory.getLogger("AssessmentController.class");

    @GetMapping("/getAllBy/OrgnizationId/{orgnizationId}")
    public ResponseEntity<List<Assessment>> getAllassmentByOrgnizationId(@PathVariable String orgnizationId) {
        log.info("AssessmentController.class, getAllassmentByOrgnizationId Start ");

        List<Assessment> assessments = this.assessmentRepo.getAssessmentsBy_orgnizationId(orgnizationId);

        log.info("AssessmentController.class, getAllassmentByOrgnizationId Ends ");
        return new ResponseEntity<List<Assessment>>(assessments, HttpStatus.OK);

    }

    @GetMapping("/getAllBy/UserId/{userId}")
    public ResponseEntity<List<Assessment>> getAllassmentByUserId(@PathVariable String userId) {
        log.info("AssessmentController.class, getAllassmentByOrgnizationId Start ");
        List<Assessment> assessments = this.assessmentRepo.getAssessmentsBy_userId(userId);

        log.info("AssessmentController.class, getAllassmentByOrgnizationId Ends ");

        return new ResponseEntity<List<Assessment>>(assessments, HttpStatus.OK);

    }
    
}
