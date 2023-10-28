package examportal.portal.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Services.OrgnizationService;

@RestController
public class OrgnizationController {
    

    @Autowired
    private OrgnizationService orgnizationService;

    @PostMapping("/createorgnization")
    public ResponseEntity<Orgnizations> createOrgnization(@RequestBody Orgnizations orgnizations)
    {
        Orgnizations savedOrgnization = this.orgnizationService.createOrgnizations(orgnizations);
        return new ResponseEntity<Orgnizations>(savedOrgnization,HttpStatus.CREATED);
    }


    @GetMapping("/getAllOrgnizations")
    public ResponseEntity<List<Orgnizations>> getAll()
    {
        List<Orgnizations> orgnization = this.orgnizationService.getAllOrgnizations();
        return new ResponseEntity<List<Orgnizations>>(orgnization,HttpStatus.OK);
    }
}
