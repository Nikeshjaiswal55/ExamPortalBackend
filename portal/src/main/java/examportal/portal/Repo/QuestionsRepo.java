package examportal.portal.Repo;

// import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import examportal.portal.Entity.Questions;
@Repository
public interface QuestionsRepo extends JpaRepository<Questions,String>{
    
    @Query("SELECT s From Questions s where s.paperID=:paperID")
    List<Questions> getAllQuestionsByPaperId(@Param("paperID") String paperID);
    @Query("SELECT s FROM Questions s WHERE s.name=:name")
    List<Questions> getAllQuestionsByName(@Param("name")String name);
}

