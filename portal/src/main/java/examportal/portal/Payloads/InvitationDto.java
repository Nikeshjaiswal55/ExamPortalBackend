package examportal.portal.Payloads;

import lombok.Data;
import java.util.List;

@Data
public class InvitationDto {
    private String paperId;
    private String orgnizationId;
    private List<String> emails;
    private String courseId;
    private String token;
}
