package examportal.portal.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Orgnization;
import examportal.portal.Services.OrgnizationService;

@RestController
@RequestMapping("/examportal")
public class OrgnizationController {
    
    @Autowired
    private OrgnizationService orgnizationService;

    @PostMapping("/createorgnization")
    public ResponseEntity<Orgnization> createOrgnization(@RequestBody Orgnization orgnization)
    {
        Orgnization savedorgnization = this.orgnizationService.createOrgnization(orgnization);
        return new ResponseEntity<>(savedorgnization,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Orgnization>> getallorgnizatio()
    {
        List<Orgnization> list = this.orgnizationService.getall();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
