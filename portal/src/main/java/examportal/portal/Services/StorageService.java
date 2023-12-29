package examportal.portal.Services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class StorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${application.bucket.name}")
    private String bucketName ;

    public List<String> store(List<String> images, String url) {

        List<String> urls = new ArrayList<>();
        for (String image : images) {
            byte[] bI = org.apache.commons.codec.binary.Base64
                    .decodeBase64((image.substring(image.indexOf(",") + 1)).getBytes());

            InputStream fis = new ByteArrayInputStream(bI);
            String filename = url + UUID.randomUUID().toString();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(bI.length);
            metadata.setContentType(image.split(",")[0].replaceAll("data:", "").replaceAll(";base64", ""));
            metadata.setCacheControl("public, max-age=31536000");
            amazonS3.putObject(bucketName, filename, fis, metadata);
            amazonS3.setObjectAcl(bucketName, filename, CannedAccessControlList.PublicRead);
            urls.add(filename);

            
        }

        return urls;
    }

    public String store(String image, String url) {

        byte[] bI = org.apache.commons.codec.binary.Base64
                .decodeBase64((image.substring(image.indexOf(",") + 1)).getBytes());

        InputStream fis = new ByteArrayInputStream(bI);
        String filename = url + UUID.randomUUID().toString();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bI.length);
        metadata.setContentType(image.split(",")[0].replaceAll("data:", "").replaceAll(";base64", ""));
        metadata.setCacheControl("public, max-age=31536000");
        amazonS3.putObject(bucketName, filename, fis, metadata);
        amazonS3.setObjectAcl(bucketName, filename, CannedAccessControlList.PublicRead);
        return filename;
    }

}
