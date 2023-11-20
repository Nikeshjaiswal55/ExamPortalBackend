package examportal.portal.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    
    private String massage;
    private boolean success;
}
