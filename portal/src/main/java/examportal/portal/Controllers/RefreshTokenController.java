package examportal.portal.Controllers;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.ServicesImpl.RefreshTokenService;

@RestController
public class RefreshTokenController {


    @Autowired
    private RefreshTokenService refreshTokenSerivce;

    @PostMapping("/getaccessToken")
    public ResponseEntity<String> handleRequest(String token) throws IOException {
        String response = this.refreshTokenSerivce.refreshToken(token);
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
}
