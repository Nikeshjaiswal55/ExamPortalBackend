package examportal.portal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Assessment {

    @Id
    private String assessmentID = UUID.randomUUID().toString();

    private String userID;

    private String orgnizationId;

    private String paperID;
}
