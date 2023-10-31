package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Paper {

    @Id
    private String paperId=UUID.randomUUID().toString();
    
    private String userId;
}
