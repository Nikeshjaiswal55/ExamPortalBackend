package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private String studentid = UUID.randomUUID().toString();
    private String email;
    private String name;


}
