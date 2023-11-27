package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import examportal.portal.Entity.Orgnizations;

public interface OrgnizationRepo extends JpaRepository<Orgnizations,String> {
    
}
