package examportal.portal.Services;

import java.util.List;
// 
import examportal.portal.Entity.Paper;

public interface PaperService {
    
    Paper createPaper(Paper paper);
    // Paper updetPaper(Paper paper);
    // String deletePaper(String paperId);
    List<Paper> getAllPaper();
    // Paper getPaperById(String paperID);
    
}
