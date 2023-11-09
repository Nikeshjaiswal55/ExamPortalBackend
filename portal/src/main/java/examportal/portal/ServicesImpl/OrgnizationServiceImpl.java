package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Entity.User;
import examportal.portal.Payloads.OrgnizationDto;
import examportal.portal.Repo.OrgnizationRepo;
import examportal.portal.Services.OrgnizationService;
import examportal.portal.Services.UserService;
import jakarta.el.ELException;

@Service
public class OrgnizationServiceImpl implements OrgnizationService {

  Logger log = LoggerFactory.getLogger("OrgnizationServiceImpl.class");

  @Autowired
  private OrgnizationRepo orgnizationRepo;

  @Autowired
  private UserService userService;
  
  @Override
  public Orgnizations createOrgnizations(OrgnizationDto orgnizationsDto) {
    log.info("OrgnizationServiceImp , createOrgnization Method Start");

    User user = this.userService.createUser(orgnizationsDto.getUser());
    
    Orgnizations newOrgnizations = new Orgnizations();
    newOrgnizations.setOrgnizationName(orgnizationsDto.getOrgnizationName());
    newOrgnizations.setOrgnizationType(orgnizationsDto.getOrgnizationType());
    newOrgnizations.setUserId(user.getUserId());

    Orgnizations savedOrgnizations = this.orgnizationRepo.save(newOrgnizations);

    log.info("OrgnizationServiceImp , createOrgnization Method Ends");
    return savedOrgnizations;
  }

  @Override
  public List<Orgnizations> getAllOrgnizations() {
    log.info("OrgnizationServiceImp , getAllOrgnization Method Start");
    List<Orgnizations> orgnizations = this.orgnizationRepo.findAll();
    log.info("OrgnizationServiceImp , getAllOrgnization Method Ends");
    return orgnizations;
  }


  @Override
  public String deleteorgnization(String OrgnizationID) {

    log.info("OrgnizationServiceImp , UpdateOrgnization Method Start");
    Orgnizations deleteOrgnizations = this.orgnizationRepo.findById(OrgnizationID).orElseThrow(()-> new ELException("Orgnization not found"));
    this.orgnizationRepo.delete(deleteOrgnizations);
    log.info("OrgnizationServiceImp , UpdateOrgnization Method Ends");
    return "deleted succesfully";
  }

 
  @Override
  public Orgnizations updateOrgnizations(Orgnizations orgnization) {
      log.info("OrgnizationServiceImp , UpdateOrgnization Method Start");
    Orgnizations Update = this.orgnizationRepo.findById(orgnization.getOrgnizationId())
        .orElseThrow(() -> new ELException("Orgnization not found"));
    Update.setOrgnizationName(orgnization.getOrgnizationName());
    Update.setOrgnizationType(orgnization.getOrgnizationType());
    Orgnizations savedOrgnizations = this.orgnizationRepo.save(Update);
    log.info("OrgnizationServiceImp , UpdateOrgnization Method Start");

    return savedOrgnizations;
  }

}
