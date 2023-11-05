package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StudentResult {
    @Id
    private String resultId=UUID.randomUUID().toString();

    private String StudentId;

}
