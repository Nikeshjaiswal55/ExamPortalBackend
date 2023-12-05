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

   @GetMapping("/Mentor/getAll")
   public ResponseEntity<List<Mentor>> getAllMentors( @RequestParam(name = "page", defaultValue = "0",required = false) Integer page,
  @RequestParam(name = "size", defaultValue = "10",required = false) Integer size,
  @RequestParam(name = "sortField", defaultValue = "name",required = false) String sortField,
  @RequestParam(name = "sortOrder", defaultValue = "asc",required = false) String sortOrder) {
      log.info("MentorController , getAllMentors Method Start");
      List<Mentor> mentorList = this.mentorService.getAllMentors(page,size,sortField,sortOrder);

      log.info("MentorController , getAllMentors Method Ends");
      return new ResponseEntity<List<Mentor>>(mentorList, HttpStatus.OK);
   }
   @GetMapping("/mentor/getAll/{name}")
   public ResponseEntity<List<Mentor>> getAllMentorsByName(@PathVariable String name){
      log.info("Mentor controller ,getAllmentorByName maathod is start");
      List<Mentor> mntr=mentorService.getAllMentorsByName(name);
      log.info("Mentor controller get All mentorby name mathod is and");
      return new ResponseEntity<>(mntr,HttpStatus.OK);

   }

   @GetMapping("/Mentor/getByid/{mentorID}")
   public ResponseEntity<Mentor> getMentorById(@PathVariable String mentorID ) {
      log.info("MentorController , getMentorById Method Start");

      Mentor mentor1 = this.mentorService.getMentorById(mentorID);

      log.info("MentorController , getMentorById Method Ends");
      return new ResponseEntity<Mentor>(mentor1, HttpStatus.ACCEPTED);

   }

   @PutMapping("/Mentor/update")
   public ResponseEntity<Mentor> updateMentor(@RequestBody Mentor mentor) {
      log.info("MentorController , updateMentor updateMentor Start");
      Mentor mentor1 = this.mentorService.updateMentor(mentor);
      log.info("MentorController , updateMentor Ends");

      return new ResponseEntity<Mentor>(mentor1, HttpStatus.OK);
   }

   @DeleteMapping("/Mentor/delete/{mentorId}")
   public ResponseEntity<String> deleteMentor(@PathVariable String mentorId) {
      log.info("MentorController , deleteMentor Start");
      this.mentorService.deleteMentor(mentorId);
      log.info("MentorController , deleteMentor Ends");
      return new ResponseEntity<String>("Record Deleted Successfully", HttpStatus.OK);
   }

   

}
