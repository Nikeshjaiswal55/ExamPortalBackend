package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Assessment;

@Repository
public interface AssessmentRepo extends JpaRepository<Assessment,String> {

    
} 
