package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Id;
import lombok.Data;
@Data
public class Branch {
    @Id
    private String branchId = UUID.randomUUID().toString();
    private String branchName;
}
