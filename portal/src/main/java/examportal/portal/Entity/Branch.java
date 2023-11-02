package examportal.portal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//uuid.random.oString
    private int id;
    private String BranchName;

}
