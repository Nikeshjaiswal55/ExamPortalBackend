package examportal.portal.Payloads;

import lombok.Data;

@Data
public class userDto {

    private String userId;
    
    private String email;

    private String name;

    private String picture;

    private String password;

    private String updatedAt;

    private String token;

    private String role;
}
