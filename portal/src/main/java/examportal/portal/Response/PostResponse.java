package examportal.portal.Response;

import java.util.List;

import examportal.portal.Entity.Mentor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter@Setter
public class PostResponse {
    private List<Mentor> content;
    private int pageNuber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}
