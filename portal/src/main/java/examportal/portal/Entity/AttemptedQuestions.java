package examportal.portal.Entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class AttemptedQuestions {

    @Id
    private String attempteQuestionId=UUID.randomUUID().toString();
    private List<String> options;
    private String questions;
    private List<String> correctAns;
    private List<String> userAns;
    private String paperID;
    private String studentID;
}
