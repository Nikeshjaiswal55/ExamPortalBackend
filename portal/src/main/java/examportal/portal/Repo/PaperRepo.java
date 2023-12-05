package examportal.portal.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Paper;

@Repository
public interface PaperRepo extends JpaRepository<Paper, String> {
@Query("SELECT p FROM Paper p WHERE p.userId=:userId")
    List<Paper> getAllPaperByUserId(@Param("userId")String userId);
}
