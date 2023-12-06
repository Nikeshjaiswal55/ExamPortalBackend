package examportal.portal.Payloads;

import java.util.List;

import examportal.portal.Entity.Cheating;
import examportal.portal.Entity.Questions;
import lombok.Data;

@Data
public class ResultDto {

    private String resultID;

    private String studentID;

    private String paperID;

    private List<Questions> questions;

    private Cheating cheating;

    private int marks;

    private String  result;

    private String date;

    private float percentage;

}
