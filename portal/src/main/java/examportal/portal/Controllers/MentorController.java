package examportal.portal.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import examportal.portal.Entity.Mentor;
import examportal.portal.Services.MentorService;
import jakarta.validation.Valid;

@RestController
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

   @GetMapping("/Mentor/getAll")
   public ResponseEntity<List<Mentor>> getAllMentors() {

      List<Mentor> mentorList = this.mentorService.getAllMentors();

      return new ResponseEntity<List<Mentor>>(mentorList, HttpStatus.OK);
   }

   @GetMapping("/Mentor/getBy/{mentorId}")
   public ResponseEntity<Mentor> getMentorById(@PathVariable String mentorId) {

      Mentor mentor1 = this.mentorService.getAllMentorById(mentorId);

      return new ResponseEntity<Mentor>(mentor1, HttpStatus.ACCEPTED);

   }

   @PutMapping("/Mentor/update")
   public ResponseEntity<Mentor> update(@RequestBody Mentor mentor) {

      Mentor mentor1 = this.mentorService.updateMentor(mentor);

      return new ResponseEntity<Mentor>(mentor1, HttpStatus.OK);
   }

   @DeleteMapping("/Mentor/delete/{mentorId}")
   public ResponseEntity<String> deleteMentor(@PathVariable String mentorId) {
      this.mentorService.deleteMentor(mentorId);

      return new ResponseEntity<String>("Record Deleted Successfully", HttpStatus.OK);
   }

   // @DeleteMapping("/Mentor/delete/all")
   // public ResponseEntity<String> deleteAllMentor(){
   // this.mentorService.deleteAllMentor();

   // return new ResponseEntity<String>("Record Deleted Successfully",
   // HttpStatus.OK);
   // }

}
