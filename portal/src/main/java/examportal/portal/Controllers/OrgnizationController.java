package examportal.portal.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Payloads.OrgnizationDto;
import examportal.portal.Services.OrgnizationService;

@RestController
public class OrgnizationController {

    Logger log = LoggerFactory.getLogger("OrgnizationController.class");

    @Autowired
    private OrgnizationService orgnizationService;

    // @PostMapping("/createorgnization")
    @PostMapping("/createorgnization")
    public ResponseEntity<Orgnizations> createOrgnization(@RequestBody OrgnizationDto orgnizationsDto)
    {   log.info("OrgnizationController, createOrgnization Method Start");
        
    Orgnizations savedOrgnization = this.orgnizationService.createOrgnizations(orgnizationsDto);
        
        log.info("Orgnization Controller, createOrgnization Method Ends");
        return new ResponseEntity<Orgnizations>(savedOrgnization,HttpStatus.CREATED);
    }
    

    @GetMapping("/getAllOrgnizations")
    public ResponseEntity<List<Orgnizations>> getAll()
    {
        log.info("OrgnizationController , getAll Method Start");
        List<Orgnizations> orgnization = this.orgnizationService.getAllOrgnizations();
        log.info("OrgnizationController , getAll Method Ends");
        return new ResponseEntity<List<Orgnizations>>(orgnization,HttpStatus.OK);
    }

    @PutMapping("/updteorgnizations")
    public ResponseEntity<Orgnizations> Updateorgnizations(@RequestBody Orgnizations orgnizations)
    {
        log.info("OrgnizationController , Updateorgnizations Method Start");
        Orgnizations update = this.orgnizationService.updteOrgnizations(orgnizations);
         log.info("OrgnizationController , Updateorgnizations Method End's");
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("/deleteorgnizations")
    public ResponseEntity<String> deleteOrgnization(@RequestBody Orgnizations orgnizations)
    {
         log.info("OrgnizationController , deleteorgnizations Method Start");
         this.orgnizationService.deleteorgnization(orgnizations.getOrgnizationId());
          log.info("OrgnizationController , Updateorgnizations Method Ends");
        return new ResponseEntity<String>("deleted succesfully",HttpStatus.OK);
    }
}
