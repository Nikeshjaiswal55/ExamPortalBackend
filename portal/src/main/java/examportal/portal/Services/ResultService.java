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

    List<Result> getTopFiveAssesmentOfStudentByStudentId(String studentId);
}
