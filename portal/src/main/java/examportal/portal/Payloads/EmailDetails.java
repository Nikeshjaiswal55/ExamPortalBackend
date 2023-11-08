package examportal.portal.Payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDetails {
    
     // Class data members
     private String to;
     private String msgBody;
     private String subject;
     // private String attachment;
}
