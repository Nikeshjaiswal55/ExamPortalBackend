package examportal.portal.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Repo.AssessmentRepo;
import examportal.portal.Repo.ExamDetailsRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
public class AssessmentController {

    @Autowired
    private AssessmentRepo assessmentRepo;

    @Autowired
    private ExamDetailsRepo examDetailsRepo;

    Logger log = LoggerFactory.getLogger("AssessmentController.class");

    @GetMapping("/getAllBy/OrgnizationId/{orgnizationId}")
    public ResponseEntity<List<Assessment>> getAllassmentByOrgnizationId(@PathVariable String orgnizationId, @RequestParam(name = "page", defaultValue = "0",required = false) Integer page,
  @RequestParam(name = "size", defaultValue = "10",required = false) Integer size,
  @RequestParam(name = "sortField", defaultValue = "name",required = false) String sortField,
  @RequestParam(name = "sortOrder", defaultValue = "asc",required = false) String sortOrder) {

        log.info("AssessmentController.class, getAllassmentByOrgnizationId Start ");
         Sort sort =(sortOrder.equalsIgnoreCase("asc"))?Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable p=PageRequest.of(page,size,sort);
        Page<Assessment> pa =this.assessmentRepo.getAssessmentsBy_orgnizationId(orgnizationId,p);
        List<Assessment> assessments = pa.getContent();
        // List<Assessment> assessments = this.assessmentRepo.getAssessmentsBy_orgnizationId(c);

        log.info("AssessmentController.class, getAllassmentByOrgnizationId Ends ");
        return new ResponseEntity<List<Assessment>>(assessments, HttpStatus.OK);

    }

    @GetMapping("/getAllBy/UserId/{userId}")
    public ResponseEntity<List<ExamDetails>> getAllassmentByUserId(@PathVariable String userId) {
        log.info("AssessmentController.class, getAllassmentByUserId Start ");

        List<Assessment> assessments = this.assessmentRepo.getAssessmentsBy_userId(userId);
        List<ExamDetails> examDetailsAll = new ArrayList<>();
        for (Assessment assessment : assessments) {
            ExamDetails examDetails = examDetailsRepo.getExamDetailsByPaperID(assessment.getPaperId());
            examDetailsAll.add(examDetails);
        }
        log.info("AssessmentController.class, getAllassmentByUserId Ends ");

        return new ResponseEntity<List<ExamDetails>>(examDetailsAll, HttpStatus.OK);

    }

    @GetMapping("/getAllAssessmentByStudentId/{studentId}")
   public ResponseEntity<List<ExamDetails>> getAllAssessmentByStudentId(@PathVariable String studentId)
   {
      List<Assessment> assessments = this.assessmentRepo.getAssessmentsBy_userId(studentId);
        List<ExamDetails> examDetailsAll = new ArrayList<>();
        for (Assessment assessment : assessments) {
            ExamDetails examDetails = examDetailsRepo.getExamDetailsByPaperID(assessment.getPaperId());
            if (examDetails.is_Active()) {
                examDetailsAll.add(examDetails);
            }
            
        }
        log.info("AssessmentController.class, getAllassmentByUserId Ends ");

        return new ResponseEntity<List<ExamDetails>>(examDetailsAll, HttpStatus.OK);
   }
    
    @GetMapping("getAllAsementByName/{name}")
    public ResponseEntity<List<Assessment>> getAllAssesmenByName(String name){ 
        log.info("AssessmentCinroler , get all assessment by name method start");
    List<Assessment> list = this.assessmentRepo.getAllAssesmenByName(name);
    if (list.isEmpty()) 
    {
        throw new NoSuchElementException("no assessment on this name");
    }
    log.info("AssessmentController , get All Assessment by name method and ");
    return new ResponseEntity<List<Assessment>>(list,HttpStatus.OK);
    }

}
