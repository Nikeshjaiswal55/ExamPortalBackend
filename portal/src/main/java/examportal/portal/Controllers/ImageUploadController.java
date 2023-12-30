package examportal.portal.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Payloads.PaperStringDto;

import examportal.portal.Services.StorageService;


@RestController
@CrossOrigin(origins = "*")
public class ImageUploadController {
 
    @Autowired
    private StorageService service;

    Logger log = LoggerFactory.getLogger("ImageUploadController.class");


    @PostMapping("/uploadAtS3")
    public PaperStringDto uploadtatamazoneS3(@RequestBody String image) {
        log.info("ImageServiceImpl ,uploadtatamazoneS3 Method Start");
        String url ="Cheating/";
        String imageUrl = this.service.store(image, url);
        PaperStringDto data = new PaperStringDto();
            data.setData(imageUrl);
        log.info("ImageServiceImpl ,uploadtatamazoneS3 Method End");
        return data;
    }

}
