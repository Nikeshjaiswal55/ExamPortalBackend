package examportal.portal.Payloads;

import java.util.List;
import java.util.UUID;

import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Questions;
import examportal.portal.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaperDto {
    
    private String paperId;
    private List<Questions> questions;
    private ExamDetails examDetails;
    private List<String> emails;
    private List<Student> students;
    private String token;
    private String userId;
    private String StudentId;
    private String orgnizationId;
    private boolean is_setup;
    private boolean is_Active;
    private String description;
    private boolean is_shorted;
    private boolean is_auto_check;
    private String instruction;
    private String student_email;

}
