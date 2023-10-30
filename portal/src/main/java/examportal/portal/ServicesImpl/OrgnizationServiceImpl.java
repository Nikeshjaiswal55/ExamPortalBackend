package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Repo.OrgnizationRepo;
import examportal.portal.Services.OrgnizationService;

@Service
public class OrgnizationServiceImpl implements OrgnizationService{

  Logger log = LoggerFactory.getLogger("OrgnizationServiceImpl.class");

    @Autowired
    private OrgnizationRepo orgnizationRepo;

    @Override
    public Orgnizations createOrgnizations(Orgnizations orgnizations) {
        log.info("OrgnizationServiceImp , createOrgnization Method Start");

       Orgnizations newOrgnizations = this.orgnizationRepo.save(orgnizations);

       log.info("OrgnizationServiceImp , createOrgnization Method Ends");
       return newOrgnizations;
    }

    @Override
    public List<Orgnizations> getAllOrgnizations() {
      log.info("OrgnizationServiceImp , getAllOrgnization Method Start");
      List<Orgnizations> orgnizations = this.orgnizationRepo.findAll();
      log.info("OrgnizationServiceImp , getAllOrgnization Method Ends");
      return orgnizations;
    }
    
    
}
