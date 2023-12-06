package examportal.portal.Payloads;

import examportal.portal.Entity.Cheating;
import examportal.portal.Entity.Questions;
import lombok.Data;
import java.util.List;

@Data
public class checkpaperDto {
   private  List<Questions> questions;
   private  String paperId;
   private  String StudentId;
    private String orgnizationId;
    private Cheating cheating;
    private String resultstatus;
}
