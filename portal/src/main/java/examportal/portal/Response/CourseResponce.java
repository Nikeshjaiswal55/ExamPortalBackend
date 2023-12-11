package examportal.portal.Response;

import java.util.List;

import examportal.portal.Entity.Course;
import lombok.Data;

@Data
public class CourseResponce {
    private List<Course> data;
    private Long totalElements;
    private Integer totalPages;
    private Integer currentPage;
    private Integer pagesize;
    private Boolean islastPage;
    // private String sortby;
}
