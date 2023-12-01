package examportal.portal.Repo;


import examportal.portal.Entity.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends JpaRepository<Student,String>{
    
    @Query("SELECT s FROM Student s Where s.email=:email")
    Student getszStudentByEmail(@Param("email")String email);

    @Query("SELECT s FROM Student s Where s.paperId=:paperId")
    List<Student> findAllStudentByPaperId(@Param("paperId")String paperId);

    
}
