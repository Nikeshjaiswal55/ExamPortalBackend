package examportal.portal.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import examportal.portal.Entity.AttemptedQuestions;

public interface AttemptedQuestionsRepo extends JpaRepository<AttemptedQuestions, String> {

    @Query("SELECT a FROM AttemptedQuestions a WHERE a.studentID = :studentID AND a.paperID = :paperID")
    List<AttemptedQuestions> getAllQuestionsByStudentID(@Param("studentID") String studentID,@Param("paperID") String paperID);

}
