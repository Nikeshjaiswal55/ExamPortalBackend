package examportal.portal.Repo;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examportal.portal.Entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,String> {

    
    @Query("SELECT s FROM Course s WHERE s.userId=:userId")
    public List<Course> getCourseByUseId(@Param("userId") String userId,Pageable p);

    @Query(value = "SELECT c FROM Course c where c.userName = :user_name OR c.course_name= :course_name")
    public List<Course> SearchCouse(@Param("user_name")String userName ,@Param("course_name")String course_name );

    List<Course> findByUserName(String userName);

    //  @Query("SELECT s FROM Course s WHERE s.userId=:userId AND " +
    //  "(:#{#filters['course_name']} IS NULL OR s.course_name = :#{#filters['course_name']}) AND " +
    //  "(:#{#filters['userName']} IS NULL OR s.userName = :#{#filters['userName']}) AND " +
    //  "(:#{#filters['duration']} IS NULL OR s.duration = :#{#filters['duration']})")
    // public List<Course> getCourseByUseIdWithFilter(@Param("userId") String userId,Pageable p,Map<String,String> filter);
    // // List<Course> 

}