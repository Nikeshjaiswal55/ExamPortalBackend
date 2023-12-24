package examportal.portal.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import examportal.portal.Entity.Result;

public interface ResultRepo extends JpaRepository<Result, String> {

    @Query("SELECT r FROM Result r WHERE r.paperID = :paperID ORDER BY r.percentage DESC")
    List<Result> findAllByPaperIdOrderByPercentageDesc(@Param("paperID") String paperID);

     @Query("SELECT r FROM Result r WHERE r.paperID = :paperID AND r.resultStatus = 'pass' ORDER BY r.percentage DESC")
    List<Result> findAllByPaperIdOrderByPercentageDescAndPass(@Param("paperID") String paperID);

    
    @Query("SELECT r From Result r WHERE r.studentID =:studentID ORDER BY r.percentage DESC")
    List<Result> findAllResutlByStudentID(@Param("studentID") String studentID);

    @Query("SELECT r FROM Result r WHERE r.paperID = :paperID AND r.studentID = :studentID")
    Result getResultByStudentAndPaperId(@Param("paperID") String paperID, @Param("studentID") String studentID);

    //find all result by paperid
    @Query("SELECT r FROM Result r WHERE r.paperID = :paperID")
    List<Result> getAllResultsByPaperID(@Param("paperID") String paperID);

    //find all Passed result by StudentID
    @Query("SELECT r FROM Result r WHERE r.studentID =:studentId AND r.resultStatus='pass' ")
    List<Result> getAllPassedResultByStudentId(@Param("studentId") String studentId);

    

}
