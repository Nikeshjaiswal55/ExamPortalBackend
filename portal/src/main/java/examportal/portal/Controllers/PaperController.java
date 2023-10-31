package examportal.portal.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Paper;
import examportal.portal.Services.PaperService;

@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;

    
    @GetMapping("/all")
    public ResponseEntity<List<Paper>> getallpaper()
    {
        List<Paper> papers = this.paperService.getAllPaper();
        return new ResponseEntity<>(papers,HttpStatus.OK);
    }
    @PostMapping("/create/paper")
    public ResponseEntity<Paper> createNewpaper(@RequestBody Paper paper)
    {
        System.out.println("enterr...................");
        Paper pepr = this.paperService.createPaper(paper);
        System.out.println("end..................");
        return new ResponseEntity<Paper>(pepr,HttpStatus.CREATED);
    }
}
