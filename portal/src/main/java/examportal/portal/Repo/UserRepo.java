package examportal.portal.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.User;
import examportal.portal.Response.PageResponce;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
    // @Query("SELECT s FROM User s Where s.email=:email")
    User findByEmail(String email);
    
    List<User> findByName(String name);

    List<User> findByRole(String role);

    List<User> findByUserId(String userId);

    
    

    
    
}
