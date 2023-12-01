package examportal.portal.Services;

import java.util.List;
// 
import examportal.portal.Entity.Paper;
// import examportal.portal.Entity.Questions;
import examportal.portal.Payloads.PaperDto;

public interface PaperService {
    
    Paper createPaper(PaperDto paperDto);
    
    PaperDto updetPaper(PaperDto paperDto);
    // String deletePaper(String paperId);
    List<PaperDto> getAllPaper();
    PaperDto getPaperById(String paperID);

    String deletePaperByPaperId(String paperId);

}
