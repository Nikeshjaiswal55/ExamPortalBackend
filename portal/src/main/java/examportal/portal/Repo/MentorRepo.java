package examportal.portal.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import examportal.portal.Entity.Mentor;

public interface MentorRepo extends JpaRepository<Mentor,String> {

}
