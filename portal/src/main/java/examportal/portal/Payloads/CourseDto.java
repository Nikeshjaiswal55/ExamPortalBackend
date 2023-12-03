package examportal.portal.Payloads;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseDto {

    private String course_name;

    private String userId;

    private String token;

    private List<EmailsDto> emailsDto;

}
