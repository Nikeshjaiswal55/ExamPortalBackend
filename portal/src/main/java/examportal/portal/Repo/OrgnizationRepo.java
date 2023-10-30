package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Orgnizations;
@Repository
public interface OrgnizationRepo extends JpaRepository<Orgnizations,String>{
    
}
