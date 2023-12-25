package examportal.portal.ServicesImpl;

import org.springframework.stereotype.Service;

import okhttp3.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Deprecated
@Service
public class RefreshTokenSerivceImp {

    private final String auth0ManagementAPIBaseUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com";
    private final OkHttpClient client = new OkHttpClient();

    // String domain = "https://dev-uil1ecwkoehr31jg.us.auth0.com";
    String clientId = "HFjnwkNDl3VtcyC83VfiGWtmLXBT6Pvz";
    String granttype = "client_credentials";
    String client_secret = "gxmU3TaCMa5UBEl0R6cVspXn31yxMMV8OleGBYLU16cp6BpewmtJIQh0izcA4oNb";
    String audience = "https://exam-easy";

    public String getAccessToken(String refreshToken) throws Exception {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++enter");
        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = "{"
                + "\"grant_type\": \""+granttype+"\","
                  + "\"audience\": \""+audience+"\","
                + "\"refresh_token\": \"" + refreshToken + "\","
                + "\"client_id\": \"" + clientId + "\","
                + "\"client_secret\": \"" + client_secret + "\""
                + "}";

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url(auth0ManagementAPIBaseUrl + "/oauth/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            try {
                JsonObject jsonObject = JsonParser.parseString(response.body().string()).getAsJsonObject();
                System.out.println(jsonObject);
                String accessToken = jsonObject.get("access_token").getAsString();
                return accessToken;
            } catch (Exception e) {
                System.out.println("Error parsing JSON: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to obtain access token. Response: " + response.body().string());
        }

        return null;
    }

    // ... (other methods)

}