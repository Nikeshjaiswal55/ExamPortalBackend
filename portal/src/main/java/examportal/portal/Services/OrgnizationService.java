package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Orgnizations;

public interface OrgnizationService {
    
    Orgnizations createOrgnizations(Orgnizations orgnizations);

    List<Orgnizations> getAllOrgnizations();

    Orgnizations updteOrgnizations(Orgnizations orgnizations);

    String deleteorgnization(String OrgnizationID);
}
