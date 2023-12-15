package examportal.portal.Repo;

import java.util.List;
import examportal.portal.Entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {

    @Query("SELECT s FROM Student s Where s.email=:email")
    Student getszStudentByEmail(@Param("email") String email);

    @Query("SELECT s FROM Student s Where s.paperId=:paperId")
    List<Student> findAllStudentByPaperId(@Param("paperId") String paperId);

    @Query("SELECT s FROM Student s where s.branch=:branch AND s.year=:year")
    List<Student> getAllStudentBYBranchAndYear(@Param("branch") String branch, @Param("year") String year);


    // it give the total count of student by orginization ID
    @Query("SELECT COUNT(s) FROM Student s WHERE s.orgnizationId = :orgnizationId")
    Long countByOrganizationId(@Param("orgnizationId") String orgnizationId);

    @Query("SELECT s FROM Student s WHERE s.orgnizationId =:orgnizationId ORDER BY s.topMarks DESC LIMIT 5")
    List<Student> getTopThreeStudentByOrgnizationIdByMarks(@Param("orgnizationId") String orgnizationId);


    // fitler top Ranker By Branch
    @Query("SELECT s FROM Student s WHERE s.orgnizationId = :orgnizationId AND s.branch=:branch " +
    "ORDER BY s.topMarks DESC " +
    "LIMIT 5")    
    List<Student> filterTopRankerByBranch(@Param("orgnizationId") String orgnizationId,@Param("branch")String branch );
    
    
    //top15StudentOFOrgnization
    @Query("SELECT s FROM Student s WHERE s.orgnizationId =:orgnizationId ORDER BY s.topMarks DESC LIMIT 15")
    List<Student> getTop15StudentsofOrgnizations(@Param("orgnizationId") String orgnizationId);
    
    // top15Student By branch
    @Query("SELECT s FROM Student s WHERE s.orgnizationId = :orgnizationId AND s.branch=:branch " +
    "ORDER BY s.topMarks DESC " +
    "LIMIT 15")
    List<Student> Top15StudentByBranch(@Param("orgnizationId") String orgnizationId,@Param("branch")String branch );
}
