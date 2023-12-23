package examportal.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import examportal.portal.ServicesImpl.RefreshTokenService;

@RestController
@Deprecated
public class RefreshTokenController {


    @Autowired
    private RefreshTokenService refreshTokenSerivce;

    @PostMapping("/getaccessToken/{refreshToken}")
    public ResponseEntity<String> getAccessTokenEndpoint(String refreshToken) {
        try {

            System.out.println("MEthod Start__+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            String accessToken = this.refreshTokenSerivce.getAccessToken(refreshToken);

            return ResponseEntity.ok(accessToken);
        } catch (Exception e) {
            // Handle exception appropriately
            return ResponseEntity.status(500).body("Error refreshing access token");
        }
}

}
