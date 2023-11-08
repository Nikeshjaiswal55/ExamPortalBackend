package examportal.portal.Payloads;

import java.util.UUID;

// import examportal.portal.Entity.User;
import lombok.Data;

@Data
public class OrgnizationDto {

    private String orgnizationId=UUID.randomUUID().toString();

    private String orgnizationName;

    private String orgnizationType;

    private String userId;

    private userDto user;
}
