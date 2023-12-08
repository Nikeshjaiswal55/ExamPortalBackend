package examportal.portal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;
import java.util.List;

@Entity
@Data
public class Questions {
    @Id
    private String questionId=UUID.randomUUID().toString();
    private List<String> options;
    private String question; 
    private String correctAns;
    private String userAns;
    private String paperID;
    private String name;
    
}
