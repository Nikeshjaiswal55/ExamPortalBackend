package examportal.portal.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
public class EmailResponce {
    private String massage;
    private boolean success;
}
