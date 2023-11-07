package examportal.portal.Payloads;
import java.util.UUID;

import lombok.Data;

@Data
public class CheatingDto {

    private String cId = UUID.randomUUID().toString() ;
   
    private String studentId;
}
