package examportal.portal.Controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import examportal.portal.Services.ImageService;

@RestController
public class ImageUploadController {
    @Autowired
    private ImageService imageService;

    Logger log = LoggerFactory.getLogger(ImageUploadController.class);

    @PostMapping("/upload")
    public ResponseEntity<Map>UploadMeadiaFile(@RequestParam("image")MultipartFile file) {

        log.info("ImageUploadController ,UploadMeadiaFile Method Start");

        Map data = this.imageService.Upload(file);

        log.info("ImageUploadController ,UploadMeadiaFile Method End");

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
