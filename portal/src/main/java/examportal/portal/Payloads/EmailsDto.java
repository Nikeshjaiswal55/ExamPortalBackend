package examportal.portal.Payloads;

import lombok.Data;

@Data
public class EmailsDto {
    
    private String email;
    private String branch;
    private String orgnizationId;
    private String year;
    private String name;
    
}
