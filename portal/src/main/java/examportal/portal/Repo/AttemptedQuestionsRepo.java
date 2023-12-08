package examportal.portal.Repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import examportal.portal.Entity.AttemptedQuestions;

public interface AttemptedQuestionsRepo extends JpaRepository<AttemptedQuestions,String>{
    
    @Query("SELECT s FROM AttemptedQuestions s WHERE s.studentID=:studentID AND s.paperID=:paperID")
    List<AttemptedQuestions> getAllQuestionsByStudentID(@Param("studentID")String studentID, @Param("paperID") String paperID);
}
