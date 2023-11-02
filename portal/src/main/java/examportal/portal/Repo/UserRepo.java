package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
    @Query("SELECT s FROM User s Where s.email=:email")
    User findByEmail(@Param("email")String email);
    
}
