package examportal.portal.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Result;
import examportal.portal.Repo.AssessmentRepo;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.ResultRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AssessmentController {

    @Autowired
    private AssessmentRepo assessmentRepo;

    @Autowired
    private ExamDetailsRepo examDetailsRepo;

    @Autowired
    private ResultRepo resultRepo;

    Logger log = LoggerFactory.getLogger("AssessmentController.class");

    @GetMapping("/getAllBy/OrgnizationId/{orgnizationId}")
    public ResponseEntity<List<Assessment>> getAllassmentByOrgnizationId(@PathVariable String orgnizationId,
            Pageable p) {
        log.info("AssessmentController.class, getAllassmentByOrgnizationId Start ");

        List<Assessment> assessments = (List<Assessment>) this.assessmentRepo
                .getAssessmentsBy_orgnizationId(orgnizationId, p);

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
    public ResponseEntity<List<ExamDetails>> getAllAssessmentByStudentId(@PathVariable String studentId) {
        log.info("AssessmentController.class, getAllAssessmentByStudentId start ");
        List<Assessment> assessments = this.assessmentRepo.getAssessmentsBy_userId(studentId);
        List<ExamDetails> examDetailsAll = new ArrayList<>();
        
        for (Assessment assessment : assessments) {
            ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(assessment.getPaperId());
              Result result = this.resultRepo.getResultByStudentAndPaperId(assessment.getPaperId(), studentId);
            
              if (result!=null) {
                examDetails.set_attempted(true);
                System.out.println(examDetails.is_attempted()+"++++++++++++++++++++++++++++++++++++++++++++++++++++");
                examDetailsAll.add(examDetails);
              }
            else if(examDetails != null && (examDetails.getIs_Active()).equals("true")) {
                examDetailsAll.add(examDetails);
            }
        }
        
        log.info("AssessmentController.class, getAllAssessmentByStudentId Ends ");
        return new ResponseEntity<>(examDetailsAll, HttpStatus.OK);
    }


    
      @GetMapping("/getTotalAssessmentBy/StudentId/{studentId}")
    public ResponseEntity<List<ExamDetails>> getAllAssessmentByStudentIdIsActiveAndProgess(@PathVariable String studentId) {
        log.info("AssessmentController.class, getAllAssessmentByStudentId start ");
        List<Assessment> assessments = this.assessmentRepo.getAssessmentsBy_userId(studentId);
        List<ExamDetails> examDetailsAll = new ArrayList<>();
        
        for (Assessment assessment : assessments) {
            ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(assessment.getPaperId());
            if(examDetails != null && "true".equals(examDetails.getIs_Active())) {
                examDetailsAll.add(examDetails);
            }
        }
        
        log.info("AssessmentController.class, getAllAssessmentByStudentId Ends ");
        return new ResponseEntity<>(examDetailsAll, HttpStatus.OK);
    }
    

}
