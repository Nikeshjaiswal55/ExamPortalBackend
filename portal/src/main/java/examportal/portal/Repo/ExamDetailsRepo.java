package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.ExamDetails;
@Repository
public interface ExamDetailsRepo extends JpaRepository<ExamDetails,String>{
    
}
