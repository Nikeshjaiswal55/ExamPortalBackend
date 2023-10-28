package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import examportal.portal.Entity.User;

public interface UserRepo extends JpaRepository<User,String>{

    
} 
