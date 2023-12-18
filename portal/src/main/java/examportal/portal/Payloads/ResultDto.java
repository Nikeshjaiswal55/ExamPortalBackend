package examportal.portal.Payloads;

import java.util.List;

import examportal.portal.Entity.Cheating;
import examportal.portal.Entity.Questions;
import examportal.portal.Entity.Result;
import lombok.Data;

@Data
public class ResultDto {

    private String resultID;

    private Result result;

    private List<Questions> questions;

    private Cheating cheating;

    private boolean is_attempted;

    private String is_published;
    

}
