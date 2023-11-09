package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Paper;

@Repository
public interface PaperRepo extends JpaRepository<Paper,String>{

    
} 
