package examportal.portal.Controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.Cloudinary;

import examportal.portal.Payloads.PaperStringDto;
import examportal.portal.Services.ImageService;
import examportal.portal.Services.StorageService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ImageUploadController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private StorageService service;

    Logger log = LoggerFactory.getLogger("ImageUploadController.class");

    // uploading file image in this method

    @PostMapping("/upload")
    public ResponseEntity<Map> UploadMeadiaFile(@RequestParam("image") MultipartFile file) {

        log.info("ImageUploadController ,UploadMeadiaFile Method Start");

        Map data = this.imageService.Upload(file);

        log.info("ImageUploadController ,UploadMeadiaFile Method End");

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    // uploading string image base64 in this method
    @PostMapping("/uploadBase64Image")
    public ResponseEntity<String> uploadImage(@RequestBody String base64Image) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));

        try {
            // Upload base64 image to Cloudinary
            Map<?, ?> result = cloudinary.uploader().upload(base64Image, ObjectUtils.emptyMap());

            // Extract public URL of the uploaded image
            String imageUrl = (String) result.get("url");

            return ResponseEntity.ok("Image uploaded successfully. URL: " + imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading image");
        }
    }

    @PostMapping("/uploadSingleImageAtaTmie")
    public PaperStringDto uploadtatclounary(@RequestBody String image) {
        PaperStringDto imageUrl = this.imageService.uploadImageSingle(image);
        log.info("ImageServiceImpl ,uploadbase64incloudnaru Method End");
        return imageUrl;
    }

    @PostMapping("/uploadAtS3")
    public PaperStringDto uploadtatamazoneS3(@RequestBody String image) {
        String url ="faskfa";
        String imageUrl = this.service.store(image, url);
        PaperStringDto dto = new PaperStringDto();
        dto.setData(imageUrl);
        log.info("ImageServiceImpl ,uploadbase64incloudnaru Method End");
        return dto;
    }

}
