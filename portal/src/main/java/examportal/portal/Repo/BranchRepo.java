package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Branch;
@Repository
public interface BranchRepo extends JpaRepository<Branch,String> {
    
}
