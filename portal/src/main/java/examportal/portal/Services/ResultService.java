package examportal.portal.Services;

import examportal.portal.Payloads.ResultDto;

public interface ResultService {
    ResultDto createResult(ResultDto result);

    ResultDto getResultByResultId(String StudentId);
}
