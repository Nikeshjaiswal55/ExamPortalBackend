package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Result;
import examportal.portal.Entity.Student;
import examportal.portal.Payloads.ResultDto;
import examportal.portal.Payloads.checkpaperDto;

public interface ResultService {
    ResultDto createResult(ResultDto result);

    ResultDto getResultByStudentAndPaperId(String resultId);

    ResultDto checkPaper(checkpaperDto dto);

    List<Student> getTopThreeStudentByPaper(String paperId);

    ResultDto getResultByStudentIdAndPaperId(String papeId,String studentId);

    List<Result> getTopFiveResultOfStudentByStudentId(String studentId);

    ResultDto getAvidenceByStudentIdAndPaperId(String papeId, String studentId);
    
    String publishStudentResult(String studentId,String paperId);
}
