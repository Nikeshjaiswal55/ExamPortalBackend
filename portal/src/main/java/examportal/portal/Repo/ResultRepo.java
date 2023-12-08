package examportal.portal.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import examportal.portal.Entity.Result;

public interface ResultRepo extends JpaRepository<Result,String> {
    
    @Query("SELECT r FROM Result r WHERE r.paperID = :paperID ORDER BY r.percentage DESC")
    List<Result> findAllByPaperIdOrderByPercentageDesc(@Param("paperID") String paperID);

    
    List<Result> findByStudentID(String studentID);
}
