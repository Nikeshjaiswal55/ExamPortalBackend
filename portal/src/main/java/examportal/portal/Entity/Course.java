package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Course {

    @Id
    private String course_id = UUID.randomUUID().toString();
    // @JsonProperty("cname")
    private String course_name;
    private String name;
    private String userId;
    private String userName;

}