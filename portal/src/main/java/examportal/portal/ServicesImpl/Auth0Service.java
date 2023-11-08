package examportal.portal.ServicesImpl;

import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@Deprecated
public class Auth0Service {

    private final String auth0ManagementAPIBaseUrl = "https://dev-mp3ifwfcpsy5t3ok.us.auth0.com";
    private final OkHttpClient client = new OkHttpClient();

    public void createUser(String email, String password, String token) throws Exception {
        String clientId = "Mn7mUDsnQZu3BwMQqixP88nnFcGeshif";
        String connection = "Username-Password-Authentication";
        System.out.println(token
                + "token===========================================================================================");
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
            System.out.println("User created successfully");
            System.out.println("Auth0 response ====================================================================");
        } else {
            System.out.println("Failed to create user. Response: " + response.body().string());
        }
    }
}
