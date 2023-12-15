package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Cheating;

@Repository
public interface CheatingRepo extends JpaRepository<Cheating, String> {

    @Query("SELECT c FROM Cheating c WHERE c.studentId = :studentId AND c.paperId = :paperId")
    Cheating getCheatingByStudentAndPaperId(@Param("studentId") String studentId, @Param("paperId") String paperId);

}
