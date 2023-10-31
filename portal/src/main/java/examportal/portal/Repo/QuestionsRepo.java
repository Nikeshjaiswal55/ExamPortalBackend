package examportal.portal.Repo;

// import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import examportal.portal.Entity.Questions;
@Repository
public interface QuestionsRepo extends JpaRepository<Questions,String>{
    

}

