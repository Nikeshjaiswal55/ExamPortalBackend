package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Cheating;
import examportal.portal.Payloads.CheatingDto;

public interface CheatingService {
 
    Cheating addCheater (CheatingDto chetingDto);

    // List<CheatingDto> getAllCheatingByStudentid (String studentId);
    
}
