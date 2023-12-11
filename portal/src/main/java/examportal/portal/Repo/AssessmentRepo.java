package examportal.portal.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import examportal.portal.Entity.Assessment;

import java.util.List;
@Repository
public interface AssessmentRepo extends JpaRepository<Assessment,String> {

    @Query("SELECT a FROM Assessment a WHERE a.orgnizationId=:orgnizationId")
    Page<Assessment> getAssessmentsBy_orgnizationId(@Param("orgnizationId") String orgnizationId , Pageable p);


    @Query("SELECT a FROM Assessment a WHERE a.userId=:userId")
    List<Assessment> getAssessmentsBy_userId(@Param("userId") String userId);

    @Query("SELECT a FROM Assessment a WHERE a.name=:name")
    List<Assessment> getAllAssesmenByName(@Param("name")String name);

    @Query("Select a From Assessment a WHERE a.userId=:userId And a.paperId=:paperId")
    Assessment getAssessmentByStudentAndpaperId(@Param("userId")String userId,@Param("paperId") String paperId);
}

