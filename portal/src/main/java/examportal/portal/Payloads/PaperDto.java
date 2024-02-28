package examportal.portal.Payloads;

import java.util.List;

import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Paper;
import examportal.portal.Entity.Questions;
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
    private Paper paper;
    private List<Questions> questions;
    private ExamDetails examDetails;
    // private List<String> emails;
    // private List<Student> students;
    private String token;
    // private String StudentId;
    // private String student_email;
}
