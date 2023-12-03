package examportal.portal.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.InvitedStudents;

@Repository
public interface InvitationRepo extends JpaRepository<InvitedStudents,String>{
    
    @Query("SELECT s from InvitedStudents s where s.paperId=:paperId")
    List<InvitedStudents> getAllStudentByPaperId(@Param("paperId") String paperId); 
}
