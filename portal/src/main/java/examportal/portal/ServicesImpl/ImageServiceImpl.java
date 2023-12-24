package examportal.portal.ServicesImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import examportal.portal.Payloads.PaperStringDto;
import examportal.portal.Services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    private static final Object String = null;
    @Autowired
    private Cloudinary cloudinary;
    Logger log = LoggerFactory.getLogger("ImageServiceImpl");

    @Override
    public Map Upload(MultipartFile file) {
        try {
            log.info("ImageServiceImpl ,Upload Method Start");

            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
            log.info("ImageServiceImpl ,Upload Method End");
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
        log.info("ImageServiceImpl ,uploadbase64incloudnaru Method Start");
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
        log.info("ImageServiceImpl ,uploadbase64incloudnaru Method End");
        return imageUrl;
    }
    
    @Override
    public PaperStringDto uploadbase64incloudnary(String image) {
        log.info("ImageServiceImpl ,uploadbase64incloudnary Method Start");
        
        PaperStringDto dto = new PaperStringDto();
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", cloudName,
                    "api_key", apiKey,
                    "api_secret", apiSecret));

            try {
                // Upload base64 image to Cloudinary
                Map<?, ?> result = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());

                // Extract public URL of the uploaded image
                 String Url = (String) result.get("url");
                 dto.setData(Url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        log.info("ImageServiceImpl ,uploadbase64incloudnaru Method End");
        return dto;
    }

    @Override
    public PaperStringDto uploadImageSingle(String image)
    {
         PaperStringDto url =uploadbase64incloudnary(image);
         return url;
    }
}

