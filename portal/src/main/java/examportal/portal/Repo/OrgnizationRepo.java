package examportal.portal.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Orgnizations;
@Repository
public interface OrgnizationRepo extends JpaRepository<Orgnizations,String>{

    @Query("SELECT s FROM Orgnizations s WHERE s.userId=:userId")
    Orgnizations getAllOrgnizationByUserID(@Param("userId")String userId);
    
}
