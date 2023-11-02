package examportal.portal.Repo;


import examportal.portal.Entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepo extends JpaRepository<Student,String>{
    
}
