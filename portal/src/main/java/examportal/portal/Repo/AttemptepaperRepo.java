package examportal.portal.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.AttemptedPapers;

@Repository
public interface AttemptepaperRepo extends JpaRepository<AttemptedPapers ,String>{
    
    @Query("select s from AttemptedPapers s where s.paperId=:paperId")
    AttemptedPapers GetattemptedStudentsByPaperId(@Param("paperId") String paperId);


    @Query("select s from  AttemptedPapers s where s.studentId=:studentId and s.paperId=:paperId")
    AttemptedPapers getAllAttemptedPaperbyStudentID(@Param("studentId") String studentId, @Param("paperId") String paperId);
    
}
