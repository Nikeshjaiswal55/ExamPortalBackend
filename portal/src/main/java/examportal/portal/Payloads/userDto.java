package examportal.portal.Payloads;

import lombok.Data;

@Data
public class userDto {
    
    private String email;

    private String name;

    private String picture;

    private String sub;

    private String role;

    private String updatedAt;

    private String token;
}