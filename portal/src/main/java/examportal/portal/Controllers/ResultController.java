package examportal.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Payloads.ResultDto;
import examportal.portal.Services.ResultService;

@RestController
@CrossOrigin(origins = "*")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/saveresult")
    public ResponseEntity<ResultDto> createResults(@RequestBody ResultDto resultDto)
    {
        System.out.println("My Quesetions =============================="+resultDto.getPaperID());
        ResultDto result = this.resultService.createResult(resultDto);

        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @GetMapping("/Get/result/{resultID}")
    public ResponseEntity<ResultDto> getresultByID(@PathVariable String resultID)
    {
        ResultDto resultDto = this.resultService.getResultByResultId(resultID);
        return new ResponseEntity<>(resultDto,HttpStatus.OK);
    }
    
}
