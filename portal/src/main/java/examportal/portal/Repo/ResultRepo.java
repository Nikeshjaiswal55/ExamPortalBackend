package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import examportal.portal.Entity.Result;

public interface ResultRepo extends JpaRepository<Result,String> {
    
}
