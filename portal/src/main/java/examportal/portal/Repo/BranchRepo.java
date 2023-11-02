package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;


import examportal.portal.Entity.Branch;
public interface BranchRepo extends JpaRepository<Branch,Integer> {
    
}
