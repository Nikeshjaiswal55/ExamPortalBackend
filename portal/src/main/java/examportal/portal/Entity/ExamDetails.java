package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ExamDetails {
    @Id
    private String examid = UUID.randomUUID().toString();
    private String examDuration;
    private String examMode;
    private int examRounds;
    private boolean paperChecked;
    private String branch;
    private String session;
    private String assessmentName;
    private String totalMarks;
    private String minimumMarks;
    private String paperId;
}
