package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class InvitedStudents {
    
    @Id
    private String invitationId = UUID.randomUUID().toString();

    private String paperId;

    private String studentId;
}
