package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orgnizations {
    
    @Id
    private String orgnizationId=UUID.randomUUID().toString();

    private String orgnizationName;
    
    private String orgnizationType;

    private String userId;

   
}
