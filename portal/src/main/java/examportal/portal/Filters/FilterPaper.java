package examportal.portal.Filters;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilterPaper {
    private Boolean is_Active;
    private String start_date;
    private String end_date;
    public boolean getIs_Active() {
        return false;
    }
}
