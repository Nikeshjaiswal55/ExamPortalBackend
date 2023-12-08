package examportal.portal.Payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class userDto {

    private String userId;
    
    private String email;

    private String name;

    private String picture;


    private String updatedAt;

    private String token;

    private String role;
}
