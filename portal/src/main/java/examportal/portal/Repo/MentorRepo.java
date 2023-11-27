package examportal.portal.Repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import examportal.portal.Entity.Mentor;

public interface MentorRepo extends JpaRepository<Mentor,String> {

  @Query("SELECT s FROM Mentor s WHERE LOWER(s.name) LIKE LOWER(:name)")
List<Mentor> findByNameContaining(@Param("name") String name);

}
