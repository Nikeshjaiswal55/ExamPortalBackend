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
public class Result {

    @Id
    private String resultID=UUID.randomUUID().toString();

    private String studentID;

    private int marks;

    private float percentage;

    private String date;

    private String resultStatus;
    
    private String Assesment_Name;
    private String paperID;

}
