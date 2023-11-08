package examportal.portal.ServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import examportal.portal.Entity.Cheating;
import examportal.portal.Payloads.CheatingDto;


import examportal.portal.Repo.CheatingRepo;
import examportal.portal.Services.CheatingService;

@Service
public class CheatingServiceImp  implements CheatingService{

    @Autowired
    private CheatingRepo cheatingRepo;
    

    @Override
    public Cheating addCheater(CheatingDto chetingDto) {

        Cheating ch = new Cheating();
        ch.setStudentId(chetingDto.getStudentId());
        Cheating newCheating = this.cheatingRepo.save(ch);
        
        // List of images
        // List<Images> img = chetingDto.getIm();
        // //List of audios
        // List<Audios> aud = chetingDto.getAud();

        // for (Images images : img) {
        //     images.setStudentId(chetingDto.getStudentId());
        //     this.imageRepo.save(images);
            
        // }
        // for (Audios audios : aud) {
        //     audios.setStudentId(chetingDto.getStudentId());
        //     this.audioRepo.save(audios);
        // }

        // return newCheating;
        return newCheating;

    }



    // @Override
    // public List<CheatingDto> getAllCheatingByStudentid(String studentId) {

    //     List<
     
    //     return null;
    // }

    
    
}
