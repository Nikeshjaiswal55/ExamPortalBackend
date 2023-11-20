package examportal.portal.Controllers;

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

    @GetMapping("/getAllBy/OrgnizationId/{orgnizationId}")
    public ResponseEntity<List<Assessment>> getAllassmentByOrgnizationId(@PathVariable String orgnizationId)
    {
        List<Assessment> assessments = this.assessmentRepo.getAssessmentsBy_orgnizationId(orgnizationId);

        return new ResponseEntity<List<Assessment>>(assessments,HttpStatus.OK);
    }
}
