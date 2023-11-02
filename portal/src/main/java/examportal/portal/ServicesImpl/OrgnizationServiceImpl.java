package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Repo.OrgnizationRepo;
import examportal.portal.Services.OrgnizationService;
import jakarta.el.ELException;

@Service
public class OrgnizationServiceImpl implements OrgnizationService {

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

  @Override
  public Orgnizations updteOrgnizations(Orgnizations orgnizations) {
    log.info("OrgnizationServiceImp , UpdateOrgnization Method Start");
    Orgnizations Update = this.orgnizationRepo.findById(orgnizations.getOrgnizationId())
        .orElseThrow(() -> new ELException("Orgnization not found"));
    Update.setOrgnizationName(orgnizations.getOrgnizationName());
    Update.setOrgnizationType(orgnizations.getOrgnizationType());
    Orgnizations savedOrgnizations = this.orgnizationRepo.save(Update);
    log.info("OrgnizationServiceImp , UpdateOrgnization Method Start");

    return savedOrgnizations;
  }

  @Override
  public String deleteorgnization(String OrgnizationID) {

    log.info("OrgnizationServiceImp , UpdateOrgnization Method Start");
    Orgnizations deleteOrgnizations = this.orgnizationRepo.findById(OrgnizationID).orElseThrow(()-> new ELException("Orgnization not found"));
    this.orgnizationRepo.delete(deleteOrgnizations);
    log.info("OrgnizationServiceImp , UpdateOrgnization Method Ends");
    return "deleted succesfully";
  }

}
