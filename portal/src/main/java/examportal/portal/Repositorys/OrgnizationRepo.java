package examportal.portal.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Orgnization;
@Repository
public interface OrgnizationRepo extends JpaRepository<Orgnization,String> {
    
}
