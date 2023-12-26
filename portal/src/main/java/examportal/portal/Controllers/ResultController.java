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
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Cheating;
import examportal.portal.Entity.Result;
import examportal.portal.Entity.Student;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.ResultDto;
import examportal.portal.Payloads.checkpaperDto;
import examportal.portal.Repo.CheatingRepo;
import examportal.portal.Repo.ResultRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Services.ResultService;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin(origins = "*")
public class ResultController {

    Logger log = LoggerFactory.getLogger("ResultController");

    
    @Autowired
    private ResultService resultService;

    @Autowired
    private CheatingRepo cheatingRepo;

    @Autowired
    private ResultRepo resultRepo;

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/saveresult")
    public ResponseEntity<ResultDto> createResults(@RequestBody ResultDto resultDto)
    {
        log.info("ResultController, createResults  Method Start");
        ResultDto result = this.resultService.createResult(resultDto);

        log.info("ResultController, createResults  Method End");
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @GetMapping("/Get/result/{resultId}")
    public ResponseEntity<ResultDto> getresultByID(@PathVariable String resultId)
    {
        log.info("ResultController, getresultByID  Method Start");
        ResultDto resultDto = this.resultService.getResultByStudentAndPaperId(resultId);
        log.info("ResultController, getresultByID  Method End");
        return new ResponseEntity<>(resultDto,HttpStatus.OK);
    }

     @GetMapping("/getTop5ResultOfStudent/{studentId}")
    public ResponseEntity<List<Result>>getTop5_ResultOfStudentId(@PathVariable String studentId)
    {
        log.info("ResultController, getTop5_ResultOfStudentId  Method Start");
     List<Result> results = this.resultService.getTopFiveResultOfStudentByStudentId(studentId);
        
     log.info("ResultController, getTop5_ResultOfStudentId  Method End");
        return new ResponseEntity<>(results,HttpStatus.OK);
    }

    @PostMapping("/checkPaper")
    public ResponseEntity<ResultDto> checkpaper( @RequestBody checkpaperDto dto)
    {
        log.info("ResultController, checkpaper  Method Start");
        ResultDto resultDto = this.resultService.checkPaper(dto);
        log.info("ResultController, checkpaper  Method End");
        return new ResponseEntity<>(resultDto,HttpStatus.CREATED);
    }

    @GetMapping("/Get/CheatingOfStudentOfPaper/{studentId}/{paperId}")
    public ResponseEntity<Cheating> getCheatingOfStudentInSpecificPaper(@PathVariable String studentId,@PathVariable String paperId)
    {
        log.info("ResultController, getCheatingOfStudentInSpecificPaper  Method Start");
        Cheating cheat = this.cheatingRepo.getCheatingByStudentAndPaperId(studentId, paperId);
        
        log.info("ResultController, getCheatingOfStudentInSpecificPaper  Method End");
        return new ResponseEntity<>(cheat,HttpStatus.OK);
    }

    @GetMapping("/getTopperByPaperId/{paperId}")
    public ResponseEntity <java.util.List<Student>>getTopThreeStudentsByPaperId(@PathVariable String paperId)
    {
       log.info("ResultController, getTopThreeStudentsByPaperId  Method Start");
        java.util.List<Student> toppers = this.resultService.getTopThreeStudentByPaper(paperId);
        log.info("ResultController, getTopThreeStudentsByPaperId  Method End");

        return new ResponseEntity<>(toppers,HttpStatus.OK);
    }

   
    @GetMapping("/getresultby/student/{studentId}/paperId/{paperId}")
    public ResponseEntity<ResultDto> getbystudentandpaperId(@PathVariable String studentId , @PathVariable String paperId) {

        log.info("ResultController, getbystudentandpaperId  Method Start");
        ResultDto dto = this.resultService.getResultByStudentIdAndPaperId(paperId, studentId);
        log.info("ResultController, getbystudentandpaperId  Method End");
        return new ResponseEntity<ResultDto>(dto,HttpStatus.OK);
    }

    @GetMapping("/getAvidanceofAStudent/{studentId}/paperId/{paperId}")
    public ResponseEntity<ResultDto> getAvidanceBystudentandpaperId(@PathVariable String studentId , @PathVariable String paperId) {
        log.info("ResultController, getAvidanceBystudentandpaperId  Method Start");
        ResultDto dto = this.resultService.getAvidenceByStudentIdAndPaperId(paperId, studentId);
        log.info("ResultController, getAvidanceBystudentandpaperId  Method End");
        return new ResponseEntity<ResultDto>(dto,HttpStatus.OK);
    }

    
    @GetMapping("/results/bypaperId/{paperID}")
    public ResponseEntity<List<Result>> getresultbypaperId(@PathVariable String paperID)
    {   
        log.info("ResultController, getresultbypaperId  Method Start");
        List<Result> results = this.resultRepo.getAllResultsByPaperID(paperID);
        log.info("ResultController, getresultbypaperId  Method End");
        return new ResponseEntity<>(results,HttpStatus.OK);
    }
    
    @GetMapping("/getTopAssesmentofStudent/{studentId}")
    public Result topassesmentofStudent (@PathVariable String studentId) {
        log.info("ResultController, topassesmentofStudent  Method Start");
        List<Result> results = this.resultRepo.findAllResutlByStudentID(studentId);
        Student s = this.studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("student", "studentId", studentId));
        s.setTopMarks(results.get(0).getMarks());
        s.setTop_paperId(results.get(0).getPaperID());
        this.studentRepo.save(s);
        log.info("ResultController, topassesmentofStudent  Method End");
        
        return results.get(0);
    }    
    
    
    @PostMapping("/publisStudentResult/studentId/{studentId}/paperId/{paperId}")
    public ResponseEntity<String> publishResult(@PathVariable String studentId,@PathVariable String paperId)
    {
     
        log.info("ResultController, publishResult  Method Start");
        String reponse = this.resultService.publishStudentResult(studentId, paperId);

        log.info("ResultController, publishResult  Method End");
        return new ResponseEntity<String>(reponse,HttpStatus.OK);
    }


    @PostMapping("/RejectStudentResult/studentId/{studentId}/paperId/{paperId}")
    public ResponseEntity<String> deactiveStudentResult(@PathVariable String studentId,@PathVariable String paperId)
    {
        log.info("ResultController, deactiveStudentResult  Method Start");
        String reponse = this.resultService.DeactiveStudentResult(studentId, paperId);
        log.info("ResultController, deactiveStudentResult  Method Ends");
        return new ResponseEntity<String>(reponse,HttpStatus.OK);
    }

    @GetMapping("/getTopAssesmentByOrgnizationId/{orgnizationId}")
    public ResponseEntity<List<Result>> getTopAssesmentByOrgnizationId(@PathVariable String orgnizationId)
    {
        List<Result> results = this.resultService.gettopAssesmentsByOrgnizationId(orgnizationId);
        return new ResponseEntity<>(results,HttpStatus.OK);
    }

    @GetMapping("/getAllPassedResultByStudentId/{studentId}")
    public ResponseEntity<List<Result>> getALLPassedResultByStudentId(@RequestParam String studentId) {

        List<Result> passedResult = this.resultRepo.getAllPassedResultByStudentId(studentId);
        return new ResponseEntity<>(passedResult,HttpStatus.OK);
    }
    

}
