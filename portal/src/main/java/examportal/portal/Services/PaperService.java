package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.ExamDetails;
// 
import examportal.portal.Entity.Paper;
// import examportal.portal.Entity.Questions;
import examportal.portal.Payloads.PaperDto;

public interface PaperService {
    
    Paper createPaper(PaperDto paperDto);
    
    PaperDto updetPaper(PaperDto paperDto);
    
    List<PaperDto> getAllPaper();
//Get All Paper By UserID
    List<ExamDetails> getAllPaperByUserId(String userId);

    //
    String activatePaper(String paperID);

    PaperDto getPaperById(String paperID);

    List<ExamDetails> getAllAssessmentsByUserId(String userId);

    String deletePaperByPaperId(String paperId);

}
