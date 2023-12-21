package examportal.portal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usser")
public class User {
    
    @Id
    private String userId;

    private String email;

    private String name;

    private String picture;
    
    private String role;

    private String password;

    
}
