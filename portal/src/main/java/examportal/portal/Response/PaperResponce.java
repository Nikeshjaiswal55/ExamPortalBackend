package examportal.portal.Response;

import java.util.List;



import examportal.portal.Entity.ExamDetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class PaperResponce {
  
   List<ExamDetails> data;
   private Long totalElements;
   private Integer totalPages;
   private Integer currentPage;
   private Integer pagesize;
   private Boolean islastPage;
   // private String sortby;
   // private String sortDirection;

     
}
