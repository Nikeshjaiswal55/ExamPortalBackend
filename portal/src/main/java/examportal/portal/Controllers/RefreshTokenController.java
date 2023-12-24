package examportal.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.ServicesImpl.RefreshTokenSerivceImp;


@RestController
@Deprecated
public class RefreshTokenController {


    @Autowired
    private RefreshTokenSerivceImp refreshTokenServiceImp;

    @PostMapping("/getaccessToken/{refreshToken}")
    public ResponseEntity<String> getAccessTokenEndpoint(String refreshToken) {
        try {

            System.out.println("MEthod Start__+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            String accessToken = this.refreshTokenServiceImp.getAccessToken(refreshToken);

            return ResponseEntity.ok(accessToken);
        } catch (Exception e) {
            // Handle exception appropriately
            return ResponseEntity.status(500).body("Error refreshing access token");
        }
}

}