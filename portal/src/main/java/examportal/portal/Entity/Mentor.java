package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Data
public class Mentor {
    
    @Id
    private String mentorId = UUID.randomUUID().toString();
    @NotEmpty
    private String mentorName ;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String orgnizationId;
    @Email 
    private String email;
    private String name;
}
