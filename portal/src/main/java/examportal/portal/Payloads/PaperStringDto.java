package examportal.portal.Payloads;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class PaperStringDto {

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String data;
}
