package examportal.portal.Entity;

import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cheating {
    
    @Id
    private String cId = UUID.randomUUID().toString();
    private String studentId;

    // private List<Images> cImages;
    // private List<Audios> cAudios;
}
