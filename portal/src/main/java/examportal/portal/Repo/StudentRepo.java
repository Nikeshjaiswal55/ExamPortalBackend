package examportal.portal.Repo;

import java.util.List;

import examportal.portal.Entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends JpaRepository<Student,String>{
    
    @Query("SELECT s FROM Student s Where s.email=:email")
    Student getsStudentByEmail(@Param("email")String email);

    //find all student by paperId
    Page<Student> findByPaperId(String paperId,Pageable p);

    @Query("SELECT s FROM Student s where s.branch=:branch")
    List<Student>  getAllStudentBYBranch( @Param("branch") String branch);
    
}
