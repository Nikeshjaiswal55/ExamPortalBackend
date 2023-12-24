package examportal.portal.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceAlreadyExistException extends RuntimeException {
    
    String resourceName;
    String fieldName;
    String fieldvalue;

    public ResourceAlreadyExistException(String resourceName, String fieldName, String fieldvalue) {
        super(String.format("%s Already exits with %s : %s ", resourceName, fieldName, fieldvalue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldvalue = fieldvalue;
    }

}
