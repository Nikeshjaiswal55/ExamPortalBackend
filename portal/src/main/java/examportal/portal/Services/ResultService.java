package examportal.portal.Services;

import examportal.portal.Payloads.ResultDto;
import examportal.portal.Payloads.checkpaperDto;

public interface ResultService {
    ResultDto createResult(ResultDto result);

    ResultDto getResultByStudentAndPaperId(String resultId);

    ResultDto checkPaper(checkpaperDto dto);
}
