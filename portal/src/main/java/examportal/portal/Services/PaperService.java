package examportal.portal.Services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Entity.ExamDetails;
// 
import examportal.portal.Entity.Paper;
import examportal.portal.Payloads.PaginationDto;
import examportal.portal.Payloads.PaperDto;
import examportal.portal.Payloads.PaperStringDto;
import examportal.portal.Response.PaperResponce;

public interface PaperService {
    
    Paper createPaper(PaperDto paperDto);
    
    PaperDto updetPaper(PaperDto paperDto);
    
    List<PaperDto> getAllPaper(Integer pageNumber, Integer size, String sortField, String sortOrder);
//Get All Paper By UserID
    PaperResponce getAllPaperByUserId(String userId,PaginationDto dto,Map<String,String> filter);

    // get pepar by name
    

    String activatePaper(String paperID,boolean active);

    PaperStringDto getPaperById(String paperID);

    List<ExamDetails> getAllAssessmentsByUserId(String userId);

    String deletePaperByPaperId(String paperId);

    AttemptedPapers AttemptPaper(Assessment assessment);

    ExamDetails GetattemptedStudents(String paperId,String studentId);

    Future<String> processInvitationsInBackground(String paperId);

    PaperResponce getAllPaperByUserIdWithOutFilter(String userId, PaginationDto dto);
}
