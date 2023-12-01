package examportal.portal.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    private String studentid;

    private String email; 
    
    private String name;

    private String orgnizationId;

    private String paperId;

    // public String setPaperId(String paperid){
    //     paperId.add(paperid);
    //     return "paperId add"+paperId;
    // }

}