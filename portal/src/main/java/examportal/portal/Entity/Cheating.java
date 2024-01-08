package examportal.portal.Entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Cheating {

    @Id
    private String cheatingId = UUID.randomUUID().toString();
    private String studentId;
    private String resultId;
    private String paperId;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private List<String> images;
     @Lob
    @Column(columnDefinition = "LONGBLOB")
    private List<String> audios;

}
