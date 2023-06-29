package Proj;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AIModelSentiment {
    private static final String API_KEY = "dce506b8-9b53-41fc-a97f-d77c9f17e1fd";
    public static String sendRequest(String prompt) {

        HttpClient client = HttpClient.newHttpClient();


        String jsonRequest = String.format("{\"input\": \"%s\",\"steps\": [{\"skill\": \"sentiments\"}]}", prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://staging.oneai.com/api/v0/pipeline"))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("api-key", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            System.out.println(jsonResponse);
            JSONArray output = jsonResponse.getJSONArray("output");
            JSONArray labels = output.getJSONObject(0).getJSONArray("labels");
            String sentimentValue = labels.getJSONObject(0).getString("value");
            return sentimentValue;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "AN ERROR OCCURRED";
    }

}
