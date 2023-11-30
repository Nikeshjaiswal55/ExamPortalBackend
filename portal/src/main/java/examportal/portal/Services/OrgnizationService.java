package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Payloads.OrgnizationDto;

public interface OrgnizationService {
    
    Orgnizations createOrgnizations(OrgnizationDto orgnizationsDto);

    List<Orgnizations> getAllOrgnizations();

    String deleteorgnization(String OrgnizationID);

    Orgnizations updateOrgnizations(Orgnizations orgnization);


}
