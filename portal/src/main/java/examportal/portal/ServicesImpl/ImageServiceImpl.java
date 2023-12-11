package examportal.portal.ServicesImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import examportal.portal.Services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map Upload(MultipartFile file) {
        try {

            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());

            return data;
        } catch (IOException e) {
            throw new RuntimeException("Image uploding fail");
        }
    }

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    public List<String> uploadbase64incloudnaru(List<String> images) {
        List<String> imageUrl= new ArrayList();
        for (String string : images) {
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", cloudName,
                    "api_key", apiKey,
                    "api_secret", apiSecret));

            try {
                // Upload base64 image to Cloudinary
                Map<?, ?> result = cloudinary.uploader().upload(string, ObjectUtils.emptyMap());

                // Extract public URL of the uploaded image
                 String Url = (String) result.get("url");
                 imageUrl.add(Url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imageUrl;
    }

}
