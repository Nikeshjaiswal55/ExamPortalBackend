package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AttemptedPapers {
    
    @Id
    private String attempt_id= UUID.randomUUID().toString();

    private String studentId;
    private String assmentId;
    private String paperId;
    private boolean is_attempted;
}
