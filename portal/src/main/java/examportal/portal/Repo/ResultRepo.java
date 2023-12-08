package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import examportal.portal.Entity.Result;
import java.util.List;

public interface ResultRepo extends JpaRepository<Result,String> {
    
    @Query("select s from  Result s where s.paperID=:paperID")
    List<Result> getAllResultsBypaperId(@Param("paperID") String paperID);

    @Query("SELECT r FROM Result r WHERE r.paperID = :paperID ORDER BY r.percentage DESC")
    List<Result> findAllByPaperIdOrderByPercentageDesc(@Param("paperID") String paperID);


}
