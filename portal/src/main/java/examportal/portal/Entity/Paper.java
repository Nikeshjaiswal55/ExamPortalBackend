package examportal.portal.Entity;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Paper {

    @Id
    private String paperId=UUID.randomUUID().toString();
    private String userId;
    private boolean is_setup;
    private String is_Active;
    private String orgnizationId;
    private String created_date;
    private String published_date;
    private String name;
    private String created_date;
    private String published_date;
    private String description;
    private boolean is_shorted;
    private boolean is_auto_check;
    private String instruction;
}
