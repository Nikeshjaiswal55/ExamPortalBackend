package examportal.portal.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Orgnizations;
import java.util.List;

@Repository
public interface OrgnizationRepo extends JpaRepository<Orgnizations,String>{

    @Query("SELECT s FROM Orgnizations s WHERE s.userId=:userId")
    
    Orgnizations getAllOrgnizationByUserID(@Param("userId")String userId);

    List<Orgnizations> findByOrgnizationId(String orgnizationId);
    
    @Query("SELECT s FROM Course s WHERE s.name=:name")
    List<Orgnizations> getAllOrgnizationsByName(@Param("name")String name);
    
}
