package Proj;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class SuperTask {
    private List<Task> tasks;
    private HashMap<String, String> options;
    private Query query;
    private boolean cookiesEnabled;
    private Proxy proxy;



    public SuperTask() throws IOException {
        // Parsing Tasks.json
        String tasksContent = new String(Files.readAllBytes(Paths.get("Tasks.json")));
        JSONObject tasksJson = new JSONObject(tasksContent);
        JSONArray tasksArray = tasksJson.getJSONArray("Tasks");
        this.tasks = new ArrayList<>();
        for (int i = 0; i < tasksArray.length(); i++) {
            JSONObject taskObject = tasksArray.getJSONObject(i);
            Task task = new Task();
            task.number = taskObject.getString("Number");
            task.rating = taskObject.getString("Rating");
            task.page = taskObject.getString("Page");
            task.domain = taskObject.getString("Domain");
            task.urls = Arrays.asList(taskObject.getJSONArray("Urls").toList().toArray(new String[0]));
            task.name = taskObject.getString("Name");
            this.tasks.add(task);
        }

        // Parsing Options.txt
        BufferedReader br = new BufferedReader(new FileReader("Options.txt"));
        String[] optionData = br.readLine().split(",");
        this.options = new HashMap<>();
     //   Object[] op = {};

        // Fill options map
        this.options = new HashMap<>();
        options.put("Headless", (String) optionData[0]);
        options.put("Safe", (String) optionData[1]);
        options.put("Scap", (String) optionData[2]);
        options.put("DelURLFile", (String) optionData[3]);
        options.put("DelLogFile", (String) optionData[4]);
        options.put("DelResultsFile", (String) optionData[5]);
        options.put("Threads", String.valueOf(optionData[6]));
        options.put("Rate", String.valueOf(optionData[7]));
        br.close();

        // Parsing Query.json
        String queryContent = new String(Files.readAllBytes(Paths.get("Query.json")));
        JSONObject queryJson = new JSONObject(queryContent);
        query = new Query();
        query.wait = queryJson.getInt("wait");
       query.search = queryJson.getString("search");
       query.topic = queryJson.getString("topic");
       query.iterations = queryJson.getInt("itterations");
       query.keyword = queryJson.getString("keyword");

        // Parsing cookies.json
        String cookiesContent = new String(Files.readAllBytes(Paths.get("cookies.json")));
        JSONObject cookiesJson = new JSONObject(cookiesContent);
        this.cookiesEnabled = cookiesJson.getBoolean("Enabled");

        // Parsing Proxy.json
        String proxyContent = new String(Files.readAllBytes(Paths.get("Proxy.json")));
        JSONObject proxyJson = new JSONObject(proxyContent);
        this.proxy = new Proxy();
        this.proxy.enabled = proxyJson.getBoolean("Enabled");
        this.proxy.custom = proxyJson.getBoolean("Custom");
        this.proxy.def = proxyJson.getBoolean("Default");
        this.proxy.source = proxyJson.getString("Source");
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public HashMap<String, String> getOptions() {
        return options;
    }

    public Query getQuery() {
        return query;
    }

    public boolean isCookiesEnabled() {
        return cookiesEnabled;
    }

    public Proxy getProxy() {
        return proxy;
    }
}