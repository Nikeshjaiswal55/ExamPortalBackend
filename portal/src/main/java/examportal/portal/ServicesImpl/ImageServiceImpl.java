package examportal.portal.ServicesImpl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import examportal.portal.Services.ImageService;
@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map Upload(MultipartFile file) {
        try {
            
             Map data = this.cloudinary.uploader().upload(file.getBytes(),Map.of());
             
             return data;
        } catch (IOException e) {
            throw new RuntimeException("Image uploding fail"); 
        }
    }
    
}
