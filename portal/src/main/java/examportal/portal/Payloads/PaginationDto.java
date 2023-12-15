package examportal.portal.Payloads;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
@AllArgsConstructor
public class PaginationDto {

    private Integer pageNo ;
    private Integer pageSize ;
    private String property ;
    private String sortDirection;

    
}
