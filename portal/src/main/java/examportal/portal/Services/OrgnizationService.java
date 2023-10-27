package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Orgnization;

public interface OrgnizationService {
    
    Orgnization createOrgnization(Orgnization orgnization);
    List<Orgnization> getall();
}
