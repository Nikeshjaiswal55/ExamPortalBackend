package examportal.portal.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Paper;
import examportal.portal.Repo.PaperRepo;
import examportal.portal.Services.PaperService;

@Service
public class PaperServiceImpl  implements PaperService{

    @Autowired
    private PaperRepo paperRepo;

    @Override
    public Paper createPaper(Paper paper) {
       Paper newpPaper = this.paperRepo.save(paper);
       return newpPaper;
    }

    // @Override
    // public Paper updetPaper(Paper paper) {
    //     Paper updatPaper = this.paperRepo.findById(paper.getPaperId()).orElseThrow();
    //     Paper update = this.paperRepo.save(updatPaper);
    //     return update;
    // }

    // @Override
    // public String deletePaper(String paperId) {
    //   Paper paper= this.paperRepo.findById(paperId).orElseThrow();
    //    this.paperRepo.delete(paper);
    //    return "deleted Succesfully";
    // }

    @Override
    public List<Paper> getAllPaper() {
      List<Paper> papers = this.paperRepo.findAll();
      return papers;
    }

    // @Override
    // public Paper getPaperById(String paperID) {
    //    return null;
    // }
    
}
