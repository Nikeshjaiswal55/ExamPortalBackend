package examportal.portal.Entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private String is_Active;
    private boolean is_Setup;
    private boolean is_attempted;
    private int totalMarks;
    private int minimum_marks;
    private String paperId;
    private String created_date;
    private String published_date;
    private String description;
    private boolean is_shorted;
    private String is_auto_check;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String instruction;

}
