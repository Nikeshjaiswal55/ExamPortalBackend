package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Payloads.OrgnizationDto;

public interface OrgnizationService {
    
    Orgnizations createOrgnizations(OrgnizationDto orgnizationsDto);

    List<Orgnizations> getAllOrgnizations();

    Orgnizations updteOrgnizations(Orgnizations orgnizations);

    String deleteorgnization(String OrgnizationID);
}
