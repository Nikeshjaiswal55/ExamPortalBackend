package examportal.portal.Repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,String> {

    
    @Query("SELECT s FROM Course s WHERE s.userId=:userId")
    public List<Course> getCourseByUseId(@Param("userId") String userId);
}