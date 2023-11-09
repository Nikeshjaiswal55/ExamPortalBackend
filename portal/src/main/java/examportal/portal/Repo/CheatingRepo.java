package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Cheating;
@Repository
public interface CheatingRepo extends JpaRepository<Cheating,String> {
    
}
