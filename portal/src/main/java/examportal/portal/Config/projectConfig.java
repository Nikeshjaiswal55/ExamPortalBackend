package examportal.portal.Config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;

@Configuration
public class projectConfig {
    Logger log = LoggerFactory.getLogger("Config  -> projectConfig.java");
     @Bean
    public Cloudinary getColudinary(){

        log.info("Config  -> projectConfig.java, Inside {} getCloudinary Class");
        Map config = new HashMap();
        config.put("cloud_name","dvln9maxh");
        config.put("api_key","724486846564564");
        config.put("api_secret","eFNJRs1S1_Q6CfLe1deXqhS8x6I");
        config.put("secure",true);
        return new Cloudinary(config);

    }
}
