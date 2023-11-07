package examportal.portal.Entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    private String id = UUID.randomUUID().toString();
       @JsonProperty("cname")
    private String cname;

}
