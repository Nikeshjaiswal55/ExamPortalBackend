package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.AttemptedPapers;

@Repository
public interface AttemptepaperRepo extends JpaRepository<AttemptedPapers ,String>{
    
    @Query("select s from AttemptedPapers s where s.paperId=:paperId")
    AttemptedPapers GetattemptedStudentsByPaperId(@Param("paperId") String paperId);
}
