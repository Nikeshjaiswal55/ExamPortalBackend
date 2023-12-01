package examportal.portal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.InvitedStudents;

@Repository
public interface InvitationRepo extends JpaRepository<InvitedStudents,String>{
    
}
