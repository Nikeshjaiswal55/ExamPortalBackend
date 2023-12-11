package examportal.portal.Repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Paper;

@Repository
public interface PaperRepo extends JpaRepository<Paper, String> {

    @Query("SELECT p FROM Paper p WHERE p.userId=:userId")
    List<Paper> findAllPaperByUserId(@Param("userId")String userId,Pageable p);

    // @Query("SELECT p FROM Paper p WHERE p.userId = :userId AND p.is_Active = true")
    // List<Paper> findAllPaperThatAreActiveByUserId(@Param("userId")String userId);
    
    @Query("SELECT s FROM Paper s WHERE s.name=:name")
    List<Paper> getAllpaperByName(@Param("name")String name);

    //it give count of paper by orgnizationIDs
    @Query("SELECT COUNT(p) FROM Paper p WHERE p.orgnizationId = :orgnizationId")
    Long countByOrganizationId(@Param("orgnizationId")String orgnizationId);


    @Query("SELECT s FROM Paper s WHERE s.userId = :userId AND " +
    "(:#{#filters['is_Active']} IS NULL OR s.is_Active = :#{#filters['is_Active']}) AND " +
    "(:#{#filters['created_date']} IS NULL OR s.created_date = :#{#filters['created_date']}) AND " +
    "(:#{#filters['published_date']} IS NULL OR s.published_date = :#{#filters['published_date']})")

    List<Paper> findByFiter(@Param("userId")String userId, Pageable p,@Param("filters") Map<String,String> filters);
}
