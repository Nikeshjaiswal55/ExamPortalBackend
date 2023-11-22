package examportal.portal.Entity;

import java.util.List;
import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cheating {
    
    @Id
    private String cheatingId = UUID.randomUUID().toString();
    private String studentId;
    private String resultId;
    private String paperId;
    private List<String> images;
    private List<String> audios;
    

}
