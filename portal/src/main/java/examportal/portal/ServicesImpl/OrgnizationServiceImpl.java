package examportal.portal.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Repo.OrgnizationRepo;
import examportal.portal.Services.OrgnizationService;

@Service
public class OrgnizationServiceImpl implements OrgnizationService{

    @Autowired
    private OrgnizationRepo orgnizationRepo;

    @Override
    public Orgnizations createOrgnizations(Orgnizations orgnizations) {

       Orgnizations newOrgnizations = this.orgnizationRepo.save(orgnizations);
       return newOrgnizations;
    }

    @Override
    public List<Orgnizations> getAllOrgnizations() {
      List<Orgnizations> orgnizations = this.orgnizationRepo.findAll();
      return orgnizations;
    }
    
    
}
