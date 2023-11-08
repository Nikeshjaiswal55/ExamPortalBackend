package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usser")
public class User {
    
    @Id
    private String userId=UUID.randomUUID().toString();

    private String email;

    private String name;

    private String picture;

    private String sub;

    private String updatedAt;
    
    private String role;

}
