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

import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.InvitedStudents;
import examportal.portal.Entity.Paper;

import examportal.portal.Payloads.PaperDto;
import examportal.portal.Repo.InvitationRepo;
import examportal.portal.Services.PaperService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private InvitationRepo invitationRepo;

    Logger log = LoggerFactory.getLogger("MetorController");

    @GetMapping("/getall/paper")
    public ResponseEntity<List<PaperDto>> getallpaper() {
        log.info("paperService getall paper method started");
        List<PaperDto> papers = this.paperService.getAllPaper();
        log.info("paperService getall paper method End's");
        return new ResponseEntity<>(papers, HttpStatus.OK);
    }

    @GetMapping("/getall/Assesment/{userId}")
    public ResponseEntity<List<ExamDetails>> getallAssesmentByUserId(@PathVariable String userId) {
        log.info("paperService getallAssesmentByUserId method started");
        List<ExamDetails> papers = this.paperService.getAllAssessmentsByUserId(userId);
        log.info("paperService getallAssesmentByUserId method Ends");
        return new ResponseEntity<List<ExamDetails>>(papers, HttpStatus.OK);
    }

    @PostMapping("/create/paper")
    public ResponseEntity<Paper> createNewpaper(@RequestBody PaperDto paperDto,HttpServletRequest request) {
        log.info("paperService create new paper method started");
 
        String token = request.getHeader("Authorization");
        paperDto.setToken(token);
        Paper paper = this.paperService.createPaper(paperDto);

        System.out.println("end..................");
        log.info("paperService create new paper method End's");
        return new ResponseEntity<Paper>(paper, HttpStatus.CREATED);
    }
    // Getting paper by paperId
    @GetMapping("/getPaperbyPaperId/{paperID}")
    public ResponseEntity<PaperDto> getpaperByID(@PathVariable String paperID) {
        log.info("paperService get paper by id  method started");
        PaperDto paperDto = this.paperService.getPaperById(paperID);
        log.info("paperService getall paper method End's");
        return new ResponseEntity<PaperDto>(paperDto, HttpStatus.OK);
    }

    @PutMapping("/update/paper")
    public ResponseEntity<PaperDto> updatePaper(@RequestBody PaperDto paperDto) {
        log.info("paperService Update paper  method started");
        PaperDto paperDto2 = this.paperService.updetPaper(paperDto);
        log.info("paperService UpdatePaper method End's");
        return new ResponseEntity<PaperDto>(paperDto2, HttpStatus.OK);
    }

    // Getting All papers by userId
    @GetMapping("/getAllPaperbyUserId/{userId}")
    public ResponseEntity<List<ExamDetails>> getallpaersbyuserId(@PathVariable String userId) {

        log.info("paper repo getall paper by user id method started");
        List<ExamDetails> exmDeaDetails= this.paperService.getAllPaperByUserId(userId); 
        
        log.info("paper repo getall paper by user id method started");

        return new ResponseEntity<List<ExamDetails>>(exmDeaDetails, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/deletePaperByPaperID/{paperId}")
    public ResponseEntity<String> deletePaper(@PathVariable String paperId) {
        log.info("paper service deletePaper by paperid method started"); 
        String msg = this.paperService.deletePaperByPaperId(paperId);

        return new ResponseEntity<>(msg,HttpStatus.OK);

    }

    @PutMapping("/activetPaper/{paperId}")
    public ResponseEntity<String> activetPaper(@PathVariable String paperId){
        log.info("paper service activetPaper method started");
        String activeMsg = paperService.activatePaper(paperId);
        log.info("paper service activetPaper method Ends");
        return new ResponseEntity<String>(activeMsg,HttpStatus.ACCEPTED);
    }

    @GetMapping("/invited/{paperId}")
    public ResponseEntity<List<InvitedStudents>> getallstudentbypaperId(@PathVariable String paperId)
    {
        List<InvitedStudents> students = this.invitationRepo.getAllStudentByPaperId(paperId);


        return new ResponseEntity<List<InvitedStudents>>(students,HttpStatus.ACCEPTED);
    }

    @PostMapping("/attempt/paper")
    public ResponseEntity<AttemptedPapers> attemptedpaper(@RequestBody Assessment assessment)
    {
        AttemptedPapers attemptedPapers2 = this.paperService.AttemptPaper(assessment);
        
        return new ResponseEntity<>(attemptedPapers2,HttpStatus.ACCEPTED);
         
    }
}
