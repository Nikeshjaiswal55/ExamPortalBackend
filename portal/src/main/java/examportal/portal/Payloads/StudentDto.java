package examportal.portal.Payloads;

import java.util.List;

import lombok.Data;

@Data
public class StudentDto {

    private List<String> email;

    private String name;

    private String paperID;

    private String token;

    private String branch;

    private String orgnizationId;

}