package examportal.portal.Services;

import examportal.portal.Payloads.ResultDto;
import examportal.portal.Payloads.checkpaperDto;

public interface ResultService {
    ResultDto createResult(ResultDto result);

    ResultDto getResultByResultId(String StudentId);

    ResultDto checkPaper(checkpaperDto dto);
}
