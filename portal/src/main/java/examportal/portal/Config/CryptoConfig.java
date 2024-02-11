package examportal.portal.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

  

@Configuration
public class CryptoConfig {

    @Value("${app.crypto.secret-key}")
    private String secretKey;

    @Value("${app.crypto.iv}")
    private String initializationVector;

    public String getSecretKey() {
        return secretKey;
    }

    public String getInitializationVector() {
        return initializationVector;
    }
}


