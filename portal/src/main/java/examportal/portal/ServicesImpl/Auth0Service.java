package examportal.portal.ServicesImpl;

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

    // private final String auth0ManagementAPIBaseUrl = "https://dev-mp3ifwfcpsy5t3ok.us.auth0.com";
      private final String auth0ManagementAPIBaseUrl = "https://dev-uil1ecwkoehr31jg.us.auth0.com";
    private final OkHttpClient client = new OkHttpClient();

    public String createUser(String email, String password, String token) throws Exception {

        System.out
                .println("method statted  ==========================================================================");
        String clientId = "eztAo6qyd8H7WNYOEiQWqIMopdOiXRQ8";
        String connection = "Username-Password-Authentication";
        String res="";
        System.out.println(token
                + "token===========================================================================================");
        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = "{"
                + "\"client_id\": \"" + clientId + "\","
                + "\"email\": \"" + email + "\","
                + "\"password\": \"" + password + "\","
                + "\"connection\": \"" + connection + "\""
                + "}";

        System.out.println("myjson body=================================="+jsonBody);

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()

        .url(auth0ManagementAPIBaseUrl + "/dbconnections/signup")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Authorization", "Bearer " + token)
        .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            System.out.println("User created successfully");
            System.out.println(
                    "Auth0 response ====================================================================" + response);
                     try {
                        JsonObject jsonObject = JsonParser.parseString(response.body().string()).getAsJsonObject();
                        String id = jsonObject.get("_id").getAsString();
                        res=id;
            
                    } catch (Exception e) {
                        System.out.println("Error parsing JSON: " + e.getMessage());
                    }
            
                    System.out.println("my response ========================"+res);
        } else {
            System.out.println("Failed to create user. Response: " + response.body().string());
        }

        return res;
    }

}
