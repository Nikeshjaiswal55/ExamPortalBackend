package examportal.portal.Controllers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.InvitedStudents;
import examportal.portal.Entity.Paper;
import examportal.portal.Payloads.PaginationDto;
import examportal.portal.Payloads.PaperDto;
import examportal.portal.Payloads.PaperStringDto;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.InvitationRepo;
import examportal.portal.Repo.PaperRepo;
import examportal.portal.Response.PaperResponce;
import examportal.portal.Services.PaperService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private InvitationRepo invitationRepo;

    @Autowired
    private ExamDetailsRepo examDetailsRepo;

    @Autowired
    private PaperRepo paperRepo;

    Logger log = LoggerFactory.getLogger("PaperController");

    @GetMapping("/getall/paper")
    public ResponseEntity<List<PaperDto>> getallpaper(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(name = "sortField", defaultValue = "name", required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "asc", required = false) String sortOrder) {

        log.info("PaperController getallpaper method start");
        List<PaperDto> papers = this.paperService.getAllPaper(page, size, sortField, sortOrder);

        log.info("PaperController getallpaper method Ends");
        return new ResponseEntity<>(papers, HttpStatus.OK);
    }

    @GetMapping("/getall/Assesment/{userId}")
    public ResponseEntity<List<ExamDetails>> getallAssesmentByUserId(@PathVariable String userId) {

        log.info("PaperController getallpaper method Start");
        List<ExamDetails> papers = this.paperService.getAllAssessmentsByUserId(userId);
        log.info("paperService getallAssesmentByUserId method Ends");
        return new ResponseEntity<List<ExamDetails>>(papers, HttpStatus.OK);
    }

    @PostMapping("/create/paper")
    public ResponseEntity<Paper> createNewpaper(@RequestBody PaperDto paperDto, HttpServletRequest request) {
        log.info("PaperController createNewpaper method Start");

        String token = request.getHeader("Authorization");
        paperDto.setToken(token);
        Paper paper = this.paperService.createPaper(paperDto);

        System.out.println("end..................");
        log.info("PaperController createNewpaper method Ends");
        return new ResponseEntity<Paper>(paper, HttpStatus.CREATED);
    }

    // Getting paper by paperId
    @GetMapping("/getPaperbyPaperId/{paperID}")
    public ResponseEntity<PaperStringDto> getpaperByID(@PathVariable String paperID) {
        log.info("PaperController getpaperByID method started");
        PaperStringDto paperDto = this.paperService.getPaperById(paperID);
        log.info("PaperController getall paper method End's");
        return new ResponseEntity<PaperStringDto>(paperDto, HttpStatus.OK);
    }

    @PutMapping("/update/paper")
    public ResponseEntity<PaperDto> updatePaper(@RequestBody PaperDto paperDto) {
        log.info("PaperController Update paper  method started");
        PaperDto paperDto2 = this.paperService.updetPaper(paperDto);
        log.info("PaperController UpdatePaper method End's");
        return new ResponseEntity<PaperDto>(paperDto2, HttpStatus.OK);
    }

    // Getting All papers by userId

    @GetMapping("/getAllPaperbyUserId/{userId}")
    public ResponseEntity<PaperResponce> getallpaersbyuserId(@PathVariable String userId,
            @RequestParam(name = "pageno", defaultValue = "0", required = false) Integer pageno,
            @RequestParam(name = "pagesize", defaultValue = "10", required = false) Integer pagesize,
            @RequestParam(name = "sortField", defaultValue = "paper_name", required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "ASC", required = false) String sortOrder,

            @RequestParam(required = false) MultiValueMap<String, String> params) {
        log.info("PaperController, getallPaperbyuserId method Start");

        PaperResponce paperResponce;

        if (params.containsKey("filter")) {
            String filter = params.getFirst("filter");
            Map<String, String> filters = Stream.of(filter.split(","))
                    .map(entry -> entry.split(":"))
                    .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));

            paperResponce = this.paperService.getAllPaperByUserId(userId,
                    new PaginationDto(pageno, pagesize, sortField, sortOrder), filters);

        } else {
            paperResponce = this.paperService.getAllPaperByUserIdWithOutFilter(userId,
                    new PaginationDto(pageno, pagesize, sortField, sortOrder));

        }

        log.info("PaperController, getallPaperbyuserId method Start");
        return new ResponseEntity<PaperResponce>(paperResponce, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/deletePaperByPaperID/{paperId}")
    public ResponseEntity<PaperStringDto> deletePaper(@PathVariable String paperId) {
        log.info("PaperController, deletePaper by paperid method started");
        PaperStringDto deletemsg = new PaperStringDto();
         deletemsg.setData(this.paperService.deletePaperByPaperId(paperId)); 

        log.info("PaperController deletePaper by paperid Method Ends");
        return new ResponseEntity<>(deletemsg, HttpStatus.OK);

    }

    @PutMapping("/activetPaper/{paperId}")
    public ResponseEntity<PaperStringDto> activetPaper(@PathVariable String paperId) {
        log.info("PaperController activetPaper method started");
        PaperStringDto activeMsg = paperService.activatePaper(paperId);
        log.info("PaperController activetPaper method Ends");
        return new ResponseEntity<PaperStringDto>(activeMsg, HttpStatus.ACCEPTED);
    }

    @GetMapping("/invited/{paperId}")
    public ResponseEntity<List<InvitedStudents>> getallstudentbypaperId(@PathVariable String paperId) {
        log.info("PaperController getallstudentbypaperId method started");
        List<InvitedStudents> students = this.invitationRepo.getAllStudentByPaperId(paperId);
        log.info("PaperController getallstudentbypaperId method Ends");
        return new ResponseEntity<List<InvitedStudents>>(students, HttpStatus.ACCEPTED);
    }

    @PostMapping("/attempt/paper")
    public ResponseEntity<AttemptedPapers> attemptedpaper(@RequestBody Assessment assessment) {
        log.info("PaperController attemptedpaper method started");
        AttemptedPapers attemptedPapers2 = this.paperService.AttemptPaper(assessment);
        log.info("PaperController attemptedpaper method Ends");
        return new ResponseEntity<>(attemptedPapers2, HttpStatus.ACCEPTED);

    }

    @GetMapping("/getexamdetaisbypaperId/{paperId}")
    public ResponseEntity<ExamDetails> getexamdetailsbypeprId(@PathVariable String paperId) {
        log.info("PaperController getexamdetailsbypeprId method started");
        ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperId);
        log.info("PaperController getexamdetailsbypeprId method Ends");
        return new ResponseEntity<ExamDetails>(examDetails, HttpStatus.OK);
    }

    @PostMapping("/sendmailInBackground/{paperId}")
    public String processInBackground(@PathVariable String paperId) throws ExecutionException, InterruptedException {
        log.info("PaperController processInBackground method started");
        Future<String> future = paperService.processInvitationsInBackground(paperId);
        log.info("PaperController processInBackground method Ends");
        return "Background processing started for paperId: " + paperId;
    }

    @GetMapping("/getInstructionBy/PaperId/{paperId}")
    public ResponseEntity<PaperStringDto> getInstructionn(@PathVariable String paperId)
    {
        String instruction = this.paperRepo.getInstructionBypaperId(paperId);

        PaperStringDto dto = new PaperStringDto();
        dto.setData(instruction);


        return new ResponseEntity<PaperStringDto>(dto,HttpStatus.OK);
    }

}
