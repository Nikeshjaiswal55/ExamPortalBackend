package examportal.portal.Response;

import java.util.List;

import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class PageResponce {
   private List<User> content_User;

   private List<Student> content_Student;
   private Long totalElements;
   private Integer totalPages;
   private Integer page;
   private Integer pagesize;
   private Boolean islastPage;
   private String sortby;
   private String sortDirection;

     
}
