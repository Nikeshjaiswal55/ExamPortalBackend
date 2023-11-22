package examportal.portal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    private String studentid;

    private String email; 
    
    private String name;

    private String orgnizationId;

}