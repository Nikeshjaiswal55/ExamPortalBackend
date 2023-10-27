package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orgnization {

    @Id
    private String orgnizationid=UUID.randomUUID().toString();

    private String orgnizationName;

    private String orgnizationtype;

    private String userid;

}
