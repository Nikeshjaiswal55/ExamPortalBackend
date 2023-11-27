package examportal.portal.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import examportal.portal.Entity.Mentor;
// import examportal.portal.Response.PostResponse;
import examportal.portal.Services.MentorService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class MentorController {

   @Autowired
   private MentorService mentorService;

   Logger log = LoggerFactory.getLogger("MetorController");

   @PostMapping("/Mentor/create")
   public ResponseEntity<Mentor> addMentor(@RequestBody @Valid Mentor mentor) {
      log.info("MentorController ,addMentor Method Start");

      Mentor mentor1 = this.mentorService.addMentor(mentor);

      log.info("MentorController ,addMentor Method End");
      return new ResponseEntity<Mentor>(mentor1, HttpStatus.CREATED);
   }
   // get all method is  pagenation mathod

   @GetMapping("/Mentor/getAll")
   public ResponseEntity<List<Mentor>> getAllMentors(
      @RequestParam(value = "pageNuber",defaultValue = "0",required = false)Integer pageNumber,
      @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
      @RequestParam(value = "sortBy",defaultValue = "mentorId",required = false)String sortBy,
      @RequestParam(value = "sortDir",defaultValue = "dec",required = false)String sortDir
   ) {
    List<Mentor> mentorList = this.mentorService.getAllMentors( pageNumber,pageSize,sortBy,sortDir);
      return new ResponseEntity<List<Mentor>>(mentorList, HttpStatus.OK);
   }

   @GetMapping("/Mentor/getByid/{mentorID}")
   public ResponseEntity<Mentor> getMentorById(@PathVariable String mentorID ) {

      Mentor mentor1 = this.mentorService.getMentorById(mentorID);

      return new ResponseEntity<Mentor>(mentor1, HttpStatus.ACCEPTED);

   }

   @PutMapping("/Mentor/update")
   public ResponseEntity<Mentor> updateMentor(@RequestBody Mentor mentor) {

      Mentor mentor1 = this.mentorService.updateMentor(mentor);

      return new ResponseEntity<Mentor>(mentor1, HttpStatus.OK);
   }

   @DeleteMapping("/Mentor/delete/{mentorId}")
   public ResponseEntity<String> deleteMentor(@PathVariable String mentorId) {
      this.mentorService.deleteMentor(mentorId);

      return new ResponseEntity<String>("Record Deleted Successfully", HttpStatus.OK);
   }@GetMapping("/mentor/sarchName/{mentorName}")
   public ResponseEntity<List<Mentor>>sarchMentorByName(@PathVariable String mentorName) {
      List<Mentor> result = this.mentorService.serchMentors(mentorName);
      return new ResponseEntity<>(result,HttpStatus.OK);
   }
}
