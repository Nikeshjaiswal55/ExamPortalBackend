package examportal.portal.Repo;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// import com.auth0.json.mgmt.Page;

import examportal.portal.Entity.Paper;

@Repository
public interface PaperRepo extends JpaRepository<Paper, String> {

    @Query("SELECT p FROM Paper p WHERE p.userId=:userId")
    Page<Paper> findAllPaperByUserId(@Param("userId")Pageable pageable,String userId);

    @Query("SELECT p FROM Paper p WHERE p.userId = :userId AND p.is_Active = true")
    List<Paper> findAllPaperThatAreActiveByUserId(@Param("userId")String userId);
    
    @Query("SELECT s FROM Paper s WHERE s.name=:name")
    List<Paper> getAllpaperByName(@Param("name")String name);

    //it give count of paper by orgnizationIDs
    @Query("SELECT COUNT(p) FROM Paper p WHERE p.orgnizationId = :orgnizationId")
    Long countByOrganizationId(@Param("orgnizationId")String orgnizationId);
}
