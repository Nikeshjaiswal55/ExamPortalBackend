package examportal.portal.Repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Paper;

@Repository
public interface PaperRepo extends JpaRepository<Paper, String> {

    @Query("SELECT p FROM Paper p WHERE p.userId=:userId")
    Page<Paper> findAllPaperByUserId(@Param("userId") String userId, Pageable p);

    // it give count of paper by orgnizationIDs
    @Query("SELECT COUNT(p) FROM Paper p WHERE p.orgnizationId = :orgnizationId")
    Long countByOrganizationId(@Param("orgnizationId") String orgnizationId);

    @Query("SELECT p FROM Paper p WHERE p.orgnizationId=:orgnizationId")
    List<Paper> getAllPapersByOrgnizationId(@Param("orgnizationId") String orgnizationId);

    // @Query("SELECT s FROM Paper s WHERE s.userId = :userId AND " +
    // "(:#{#filters['is_Active']} IS NULL OR s.is_Active =
    // :#{#filters['is_Active']}) AND " +
    // "(:#{#filters['created_date']} IS NULL OR s.created_date =
    // :#{#filters['created_date']}) AND " +
    // "(:#{#filters['published_date']} IS NULL OR s.published_date =
    // :#{#filters['published_date']})")

    @Query("SELECT s FROM Paper s WHERE s.userId = :userId AND (:#{#filters['is_Active']} IS NULL OR s.is_Active = :#{#filters['is_Active']}) AND (:#{#filters['created_date']} IS NULL OR s.created_date LIKE CONCAT(:#{#filters['created_date']}, '%')) AND (:#{#filters['published_date']} IS NULL OR s.published_date LIKE CONCAT(:#{#filters['published_date']}, '%')) AND (:#{#filters['paper_name']} IS NULL OR s.paper_name LIKE CONCAT(:#{#filters['paper_name']}, '%')) AND (:#{#filters['is_deactivated']} IS NULL OR s.is_deactivated = false)")
    Page<Paper> findByFiter(@Param("userId") String userId, Pageable p, @Param("filters") Map<String, String> filters);

    @Query("SELECT p.instruction FROM Paper p WHERE p.paperId = :paperId")
    String getInstructionBypaperId(@Param("paperId") String paperId);

    // @Query("SELECT p FROM Paper p WHERE p.is_deactivated = false")
    // List<Paper> getallpaper();
    // @Query("SELECT p FROM Paper p WHERE p.is_deactivated = false")
    // Page<Paper> getallpaper(Pageable pageable);

    // @Query("SELECT p FROM Paper p WHERE p.is_deactivated = false ORDER BY p.test
    // ASC")
    // Page<Paper> getallpaper(Pageable pageable);

    @Query("SELECT p FROM Paper p WHERE p.is_deactivated = false ORDER BY p.paper_name ASC")
    Page<Paper> getallpaper(Pageable pageable);

}
