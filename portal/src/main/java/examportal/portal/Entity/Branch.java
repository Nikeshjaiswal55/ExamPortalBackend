package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Data@Entity
public class Branch {
    @Id
    private String branchId = UUID.randomUUID().toString();
    private String branchName;
}
