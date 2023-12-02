package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceAlreadyExistException;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.OrgnizationDto;
import examportal.portal.Repo.OrgnizationRepo;
import examportal.portal.Services.OrgnizationService;
import examportal.portal.Services.UserService;

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

    Orgnizations orgnization = this.orgnizationRepo.getAllOrgnizationByUserID(orgnizationsDto.getUserId());

    if (orgnization != null) {
      throw new ResourceAlreadyExistException("Orgnization", "UserID", orgnizationsDto.getUserId());
    } else {

      User user = this.userService.createUser(orgnizationsDto.getUser());
      Orgnizations newOrgnizations = new Orgnizations();

      newOrgnizations.setOrgnizationName(orgnizationsDto.getOrgnizationName());
      newOrgnizations.setOrgnizationType(orgnizationsDto.getOrgnizationType());
      newOrgnizations.setUserId(user.getUserId());

      Orgnizations savedOrgnizations = this.orgnizationRepo.save(newOrgnizations);

      log.info("OrgnizationServiceImp , createOrgnization Method Ends");
      return savedOrgnizations;
    }
  }
//impeliments pagenation and sorting in this mathod
  @Override
  public List<Orgnizations> getAllOrgnizations(Integer pageNumber, int size, String sortField, String sortOrder) {
    log.info("OrgnizationServiceImp , getAllOrgnization Method Start");
    Sort sort =(sortField.equalsIgnoreCase("ASC"))?Sort.by(sortField).ascending():Sort.by(sortField).descending();
    Pageable p= PageRequest.of(pageNumber, size, sort);
    Page<Orgnizations> orgnizations = this.orgnizationRepo.findAll(p);
    List<Orgnizations> o= orgnizations.getContent();
    log.info("OrgnizationServiceImp , getAllOrgnization Method Ends");
    return o;
  }

  @Override
  public String deleteorgnization(String OrgnizationID) {

    log.info("OrgnizationServiceImp , UpdateOrgnization Method Start");
    Orgnizations deleteOrgnizations = this.orgnizationRepo.findById(OrgnizationID)
        .orElseThrow(() -> new ResourceNotFoundException("Orgnization", "OrgnizationID", OrgnizationID));
    this.orgnizationRepo.delete(deleteOrgnizations);
    log.info("OrgnizationServiceImp , UpdateOrgnization Method Ends");
    return "deleted succesfully";
  }

  @Override
  public Orgnizations updateOrgnizations(Orgnizations orgnization) {
    log.info("OrgnizationServiceImp , UpdateOrgnization Method Start");
    Orgnizations Update = this.orgnizationRepo.findById(orgnization.getOrgnizationId())
        .orElseThrow(
            () -> new ResourceNotFoundException("Orgnization", "OrgnizationId", orgnization.getOrgnizationId()));
    Update.setOrgnizationName(orgnization.getOrgnizationName());
    Update.setOrgnizationType(orgnization.getOrgnizationType());
    Orgnizations savedOrgnizations = this.orgnizationRepo.save(Update);
    log.info("OrgnizationServiceImp , UpdateOrgnization Method Start");

    return savedOrgnizations;
  }

}
