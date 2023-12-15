package examportal.portal.Payloads;

import java.util.List;

import examportal.portal.Entity.Course;
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

    private String duration;

    private String orgnizationId;

    private List<EmailsDto> emailsDto;

    public List<Course> getMails() {
        return null;
    }

}
