package examportal.portal.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Course;
@Repository

public interface CourseRepo extends JpaRepository<Course,String> {
    
}
