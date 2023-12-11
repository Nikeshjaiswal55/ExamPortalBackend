package examportal.portal.Payloads;

import lombok.Data;

@Data
public class EmailsDto {
    
    private String email;
    private String branch;
    private String year;
    private String name;
    private String orgnizationId;
    
}
