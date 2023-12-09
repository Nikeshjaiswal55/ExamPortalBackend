package examportal.portal.Controllers;

import java.util.List;

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
import examportal.portal.Payloads.ResultDto;
import examportal.portal.Payloads.checkpaperDto;
import examportal.portal.Repo.CheatingRepo;
import examportal.portal.Repo.ResultRepo;
import examportal.portal.Services.ResultService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private CheatingRepo cheatingRepo;

    @Autowired
    private ResultRepo resultRepo;

    @PostMapping("/saveresult")
    public ResponseEntity<ResultDto> createResults(@RequestBody ResultDto resultDto)
    {
        ResultDto result = this.resultService.createResult(resultDto);

        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @GetMapping("/Get/result/{resultId}")
    public ResponseEntity<ResultDto> getresultByID(@PathVariable String resultId)
    {
        ResultDto resultDto = this.resultService.getResultByStudentAndPaperId(resultId);
        return new ResponseEntity<>(resultDto,HttpStatus.OK);
    }

    @PostMapping("/checkPaper")
    public ResponseEntity<ResultDto> checkpaper( @RequestBody checkpaperDto dto)
    {
        ResultDto resultDto = this.resultService.checkPaper(dto);
        return new ResponseEntity<>(resultDto,HttpStatus.CREATED);
    }

    @GetMapping("/Get/CheatingOfStudentOfPaper/{studentId}/{paperId}")
    public ResponseEntity<Cheating> getCheatingOfStudentInSpecificPaper(@PathVariable String studentId,@PathVariable String paperId)
    {
        Cheating cheat = this.cheatingRepo.getCheatingByStudentAndPaperId(studentId, paperId);
        return new ResponseEntity<>(cheat,HttpStatus.OK);
    }

    @GetMapping("/getTopperByPaperId/{paperId}")
    public ResponseEntity <java.util.List<Student>>getTopThreeStudentsByPaperId(@PathVariable String paperId)
    {
       
        java.util.List<Student> toppers = this.resultService.getTopThreeStudentByPaper(paperId);

        return new ResponseEntity<>(toppers,HttpStatus.OK);
    }

    @GetMapping("/getTop5ResultOfStudent/{studentId}")
    public ResponseEntity<List<Result>>getTop5_ResultOfStudentId(@PathVariable String studentId)
    {
       
     List<Result> results = this.resultService.getTopFiveAssesmentOfStudentByStudentId(studentId);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }

    @GetMapping("/getresultby/student/{studentId}/paperId/{paperId}")
    public ResponseEntity<ResultDto> getbystudentandpaperId(@PathVariable String studentId , @PathVariable String paperId) {
        ResultDto dto = this.resultService.getResultByStudentIdAndPaperId(paperId, studentId);
        return new ResponseEntity<ResultDto>(dto,HttpStatus.OK);
    }
    
}
