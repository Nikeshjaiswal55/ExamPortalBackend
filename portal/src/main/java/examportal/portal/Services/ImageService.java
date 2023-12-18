package examportal.portal.Services;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public Map Upload(MultipartFile file);

     List<String> uploadbase64incloudnaru(List<String> images);
     String uploadbase64incloudnary(String image);
}
