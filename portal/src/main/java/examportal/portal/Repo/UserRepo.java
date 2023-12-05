package examportal.portal.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.User;


@Repository
public interface UserRepo extends JpaRepository<User,String>{
    
    User findByEmail(String email);
    
    List<User> findByName(String name);

    List<User> findByRole(String role);

    List<User> findByUserId(String userId);
    @Query("SELECT s FROM Course s WHERE s.name=:name")
    List<User> getAllUserByName(@Param("name")String name);

    
    

    
    
}
