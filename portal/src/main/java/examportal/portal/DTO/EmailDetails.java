package examportal.portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDetails {
    
     // Class data members
     private String recipient;
     private String msgBody;
     private String subject;
     // private String attachment;
}
