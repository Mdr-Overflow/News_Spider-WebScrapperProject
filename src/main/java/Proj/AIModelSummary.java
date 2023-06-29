package Proj;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AIModelSummary {
    private static final String API_KEY = "";


    public static String sendRequest(String prompt) {
        // You need to add the HttpClient and HttpRequest libraries
        HttpClient client = HttpClient.newHttpClient();

        String jsonRequest = String.format("{\"input\": \"%s\",\"steps\": [{\"skill\": \"summarize\"}]}", prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.oneai.com/api/v0/pipeline"))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("api-key", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            System.out.println(response);
            JSONArray output = jsonResponse.getJSONArray("output");
            String text = output.getJSONObject(0).getString("text");
            return text;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "AN ERROR OCCURRED";
    }
}
