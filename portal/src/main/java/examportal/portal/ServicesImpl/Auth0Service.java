package examportal.portal.ServicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@Deprecated
public class Auth0Service {

    Logger log = LoggerFactory.getLogger("Auth0Service.class");

    // private final String auth0ManagementAPIBaseUrl =
    // "https://dev-mp3ifwfcpsy5t3ok.us.auth0.com";
    private final String auth0ManagementAPIBaseUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com";
    private final OkHttpClient client = new OkHttpClient();
        
    public String createUser(String email, String password, String token) throws Exception {
        log.info("Auth0Service, createUser Method Started");
 
        String clientId = "HFjnwkNDl3VtcyC83VfiGWtmLXBT6Pvz";
        String connection = "Username-Password-Authentication";
        String res = "";
        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = "{"
                + "\"client_id\": \"" + clientId + "\","
                + "\"email\": \"" + email + "\","
                + "\"password\": \"" + password + "\","
                + "\"connection\": \"" + connection + "\""
                + "}";

     

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()

                .url(auth0ManagementAPIBaseUrl + "/dbconnections/signup")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            try {
                JsonObject jsonObject = JsonParser.parseString(response.body().string()).getAsJsonObject();
                String id = jsonObject.get("_id").getAsString();
                res = id;

            } catch (Exception e) {
                System.out.println("Error parsing JSON: " + e.getMessage());
            }

        } else {
            System.out.println("Failed to create user. Response: " + response.body().string());
        }
        log.info("Auth0Service, createUser Method Ends");
        return res;
    }

    // public String assignRoleToUser(String userId, String role, String accessToken) {

    //     String assignRoleUrl = "https://https://dev-uil1ecwkoehr31jg.us.auth0.com/api/v2/users/" + userId + "/roles";

    //     HttpHeaders headers = new HttpHeaders();

    //     headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

    //     headers.setBearerAuth(accessToken);

    //     // Assign role request body
    //     String roleRequestBody = "{\"roles\":[\"" + role + "\"]}";

    //     // Assign role
    //     HttpEntity<String> assignRoleEntity = new HttpEntity<>(roleRequestBody, headers);

    //     ResponseEntity<String> assignRoleResponse = new RestTemplate().exchange(assignRoleUrl, HttpMethod.POST,

    //             assignRoleEntity, String.class);

    //     if (!assignRoleResponse.getStatusCode().is2xxSuccessful()) {
    //         return "role assigned successfully";
    //     }
    //     else
    //     {
    //         return "failed to create ";
    //     }
    // }

}
