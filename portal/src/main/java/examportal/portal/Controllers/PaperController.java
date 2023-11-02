package examportal.portal.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Paper;
import examportal.portal.Payloads.PaperDto;
import examportal.portal.Services.PaperService;

@RestController
@CrossOrigin(origins = "*")
public class PaperController {

    @Autowired
    private PaperService paperService;

    
    @GetMapping("/getall/paper")
    public ResponseEntity<List<PaperDto>> getallpaper()
    {
        List<PaperDto> papers = this.paperService.getAllPaper();
        return new ResponseEntity<>(papers,HttpStatus.OK);
    }
    @PostMapping("/create/paper")
    public ResponseEntity<Paper> createNewpaper(@RequestBody PaperDto paperDto)
    {
        System.out.println("enterr..................."+paperDto.getQuestions());
        Paper paper= this.paperService.createPaper(paperDto);
        
        System.out.println("end..................");
        return new ResponseEntity<Paper>(paper,HttpStatus.CREATED);
    }

    @GetMapping("/getPaper/byId/{paperID}")
    public ResponseEntity<PaperDto> getpaperByID(@PathVariable String paperID)
    {
        PaperDto paperDto = this.paperService.getPaperById(paperID);
        return new ResponseEntity<PaperDto>(paperDto,HttpStatus.OK);
    }

    @PutMapping("/update/paper")
    public ResponseEntity<PaperDto> updatePaper(@RequestBody PaperDto paperDto)
    {
        PaperDto paperDto2 = this.paperService.updetPaper(paperDto);

        return new ResponseEntity<PaperDto>(paperDto2,HttpStatus.OK);
    }
}
