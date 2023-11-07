package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import examportal.portal.Entity.Cheating;

public interface CheatingRepo extends JpaRepository<Cheating,String> {
    
}
