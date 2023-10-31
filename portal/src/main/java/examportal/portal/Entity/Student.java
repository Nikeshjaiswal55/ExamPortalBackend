package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    private String studentid = UUID.randomUUID().toString();
   @Email
    private String email;
    @NotEmpty
    private String name;


}
