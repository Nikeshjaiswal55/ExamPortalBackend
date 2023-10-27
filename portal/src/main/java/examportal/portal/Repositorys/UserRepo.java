package examportal.portal.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.User;
@Repository
public interface UserRepo extends JpaRepository<User,String>{
    
}
