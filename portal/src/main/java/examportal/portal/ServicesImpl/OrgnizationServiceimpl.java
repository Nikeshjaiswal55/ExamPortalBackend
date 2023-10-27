package examportal.portal.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Orgnization;
import examportal.portal.Repositorys.OrgnizationRepo;
import examportal.portal.Services.OrgnizationService;

@Service
public class OrgnizationServiceimpl  implements OrgnizationService{

    @Autowired
    private OrgnizationRepo orgnizationRepo;

    @Override
    public Orgnization createOrgnization(Orgnization orgnization) {
       Orgnization newOrgnization = this.orgnizationRepo.save(orgnization);
       return newOrgnization;
    }

    @Override
    public List<Orgnization> getall() {
       List<Orgnization> orgnizations = this.orgnizationRepo.findAll();
       return orgnizations;
    }
    
}
