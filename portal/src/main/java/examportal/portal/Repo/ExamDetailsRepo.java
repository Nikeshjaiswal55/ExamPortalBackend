package examportal.portal.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.ExamDetails;

@Repository
public interface ExamDetailsRepo extends JpaRepository<ExamDetails,String>{
    
    @Query("SELECT s FROM ExamDetails s where s.paperId=:paperId")
    ExamDetails getExamDetailsByPaperID(@Param("paperId") String paperId);

    // @Query("SELECT ex FROM ExamDetails ex where s.assessmentName LIKE %:assessmentName%")
    // Page<ExamDetails> findByAssessmentName(@Param("assessmentName")String assessmentName ,Pageable p);
}
