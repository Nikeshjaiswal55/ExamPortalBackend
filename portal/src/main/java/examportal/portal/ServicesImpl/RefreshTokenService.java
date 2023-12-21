package examportal.portal.ServicesImpl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    private final String auth0ManagementAPIBaseUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com";
    private final OkHttpClient client = new OkHttpClient();

    private String clientId = "HFjnwkNDl3VtcyC83VfiGWtmLXBT6Pvz";
    private String grant_type = "client_credentials";
    private String client_secret = "gxmU3TaCMa5UBEl0R6cVspXn31yxMMV8OleGBYLU16cp6BpewmtJIQh0izcA4oNb";
    private String audience = "https://exam-easy";

    public String refreshToken(String refresh_token) throws IOException {
        String res = "";

        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = "{"
                + "\"grant_type\": \"" + grant_type + "\","
                + "\"client_id\": \"" + clientId + "\","
                + "\"client_secret\": \"" + client_secret + "\","
                + "\"audience\": \"" + audience + "\","
                + "\"refresh_token\": \"" + refresh_token + "\""
                + "}";

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url(auth0ManagementAPIBaseUrl + "/dbconnections/signup")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JsonObject jsonObject = JsonParser.parseString(response.body().string()).getAsJsonObject();
                System.out.println(jsonObject+"my object ================================================");
                // String id = jsonObject.get("_id").getAsString();
                // res = id;
            } else {
                System.out.println("Failed to create user. Response: " + response.body().string());
            }
        }

        System.out.println("Auth0Service, refreshToken Method Ends");
        return res;
    }
}
























 // public String getAccess_token() {
    //     // Define Auth0 API endpoint
    //     String auth0TokenUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com/oauth/token/";

    //     // Set up request headers
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    //     // Set up request body parameters
    //     MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    //     requestBody.add("grant_type", "client_credentials");
    //     requestBody.add("client_id", "HFjnwkNDl3VtcyC83VfiGWtmLXBT6Pvz");
    //     requestBody.add("client_secret", "gxmU3TaCMa5UBEl0R6cVspXn31yxMMV8OleGBYLU16cp6BpewmtJIQh0izcA4oNb");
    //     requestBody.add("audience", "https://exam-easy");

    //     // Create the HTTP entity with headers and body
    //     HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

    //     // Create RestTemplate
    //     RestTemplate restTemplate = new RestTemplate();

    //     // Make the POST request and get the response
    //     ResponseEntity<String> responseEntity = restTemplate.postForEntity(auth0TokenUrl, requestEntity, String.class);

    //     // Handle the response
    //     // HttpStatus statusCode = responseEntity.getStatusCode();
    //     String responseBody = responseEntity.getBody();

    //     // System.out.println("Status Code: " + statusCode);
    //     System.out.println("Response Body: " + responseBody);
    // }
// private final OkHttpClient client = new OkHttpClient();
// private final String auth0TokenEndpoint = "https://dev-uil1ecwkoehr31jg.us.auth0.com/oauth/token/";

    // public String getAccessToken(TokenDto tokenDto
    //     // String clientId, String clientSecret, String audience, String grantType , String username, String password
    // ) throws Exception {
    //     // log.info("Auth0Service, getAccessToken Method Started");

    //     MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
    //     String requestBody = "grant_type=" + tokenDto.getGrantType() +
    //             "&client_id=" + tokenDto.getClientId() +
    //             "&client_secret=" + tokenDto.getClientSecret() +
    //             "&audience=" + tokenDto.getAudience(); 
    //             // "&username=" + username +
    //             // "&password=" + password;
    //             ;

    //     RequestBody body = RequestBody.create(mediaType, requestBody);

    //     Request request = new Request.Builder()
    //             .url(auth0TokenEndpoint)
    //             .post(body)
    //             .build();

    //     Response response = client.newCall(request).execute();
    //     if (response.isSuccessful()) {
    //         try {
    //             String responseBody = response.body().string();
    //             // Parse the JSON response to get the access token
    //             String accessToken = JsonParser.parseString(responseBody).getAsJsonObject().get("access_token").getAsString();
    //             log.info("Auth0Service, getAccessToken Method Ends");
    //             return accessToken;
    //         } catch (Exception e) {
    //             log.error("Error parsing JSON: " + e.getMessage());
    //         }
    //     } else {
    //         log.error("Failed to get access token. Response: " + response.body().string());
    //     }

    //     log.info("Auth0Service, getAccessToken Method Ends");
    //     return null;
    // }