package examportal.portal.Services;

import java.util.List;
import java.util.concurrent.Future;

import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Entity.ExamDetails;
// 
import examportal.portal.Entity.Paper;
// import examportal.portal.Entity.Questions;
import examportal.portal.Payloads.PaperDto;

public interface PaperService {
    
    Paper createPaper(PaperDto paperDto);
    
    PaperDto updetPaper(PaperDto paperDto);
    
    List<PaperDto> getAllPaper(Integer pageNumber, Integer size, String sortField, String sortOrder);
//Get All Paper By UserID
    List<ExamDetails> getAllPaperByUserId(String userId);

    // get pepar by name
    public List<Paper> getAllpaperByName(String name);

    String activatePaper(String paperID,boolean active);

    String getPaperById(String paperID);

    List<ExamDetails> getAllAssessmentsByUserId(String userId);

    String deletePaperByPaperId(String paperId);

    AttemptedPapers AttemptPaper(Assessment assessment);

    ExamDetails GetattemptedStudents(String paperId,String studentId);

    Future<String> processInvitationsInBackground(String paperId);
}
