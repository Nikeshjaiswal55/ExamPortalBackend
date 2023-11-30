package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import examportal.portal.Entity.Assessment;

import java.util.List;
@Repository
public interface AssessmentRepo extends JpaRepository<Assessment,String> {

    @Query("SELECT a FROM Assessment a WHERE a.orgnizationId=:orgnizationId")
    List<Assessment> getAssessmentsBy_orgnizationId(@Param("orgnizationId") String orgnizationId);


    @Query("SELECT a FROM Assessment a WHERE a.userId=:userId")
    List<Assessment> getAssessmentsBy_userId(@Param("userId") String userId);
}

