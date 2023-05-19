package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class random {

    public static void main(String[] args) throws Exception {
        
        String baseURL = "https://api.tomtom.com/traffic/services/4/flowSegmentData/absolute/10/json";
        String apiKey = "5jwydh7cjhv8Db5BkKqc3m9bi3laGnRF";

        
        String point = "18.391809,73.855933"; 

        
        String unit = "KMPH";
        String thickness = "20";
        String openLr = "false";
        String jsonp = "jsoncallback";

        // Construct the URL for the API call
        String url = baseURL + "?key=" + apiKey + "&point=" + point + "&unit=" + unit + "&thickness=" + thickness + "&openLr=" + openLr + "&jsonp=" + jsonp;

        // Send the HTTP request and retrieve the response
        String flowSegmentData = sendHttpRequest(url);

        
        //Gson gson = new Gson();
        JsonObject jsonObject = new Gson().fromJson(flowSegmentData, JsonObject.class);

        
        //int flow = jsonObject.getAsJsonArray("flowSegmentData").get(0).getAsJsonObject().get("currentSpeed").getAsInt();
        //int flow = jsonObject.getAsJsonArray("flowSegmentData").get(0);
        /*int freeFlow = jsonObject.getAsJsonArray("flowSegmentData").get(0).getAsJsonObject().get("freeFlowSpeed").getAsInt();
        int confidence = jsonObject.getAsJsonArray("flowSegmentData").get(0).getAsJsonObject().get("confidence").getAsInt();
        String roadName = jsonObject.getAsJsonArray("flowSegmentData").get(0).getAsJsonObject().get("roadName").getAsString();*/

        
        //System.out.println(jsonObject.get("flowSegmentData").);
        int currSpeed = jsonObject.get("flowSegmentData").getAsJsonObject().get("currentSpeed").getAsInt();
        int FreeFlowSpeed = jsonObject.get("flowSegmentData").getAsJsonObject().get("freeFlowSpeed").getAsInt();
        int CurrentTravelTime = jsonObject.get("flowSegmentData").getAsJsonObject().get("currentTravelTime").getAsInt();
        int FreeFlowtTravelTime = jsonObject.get("flowSegmentData").getAsJsonObject().get("freeFlowTravelTime").getAsInt();
        int Confidence = jsonObject.get("flowSegmentData").getAsJsonObject().get("confidence").getAsInt();
        /*for(String key : obj.keySet()){
            System.out.println(key);
            }
       /*  System.out.println("Free flow: " + freeFlow);
        System.out.println("Confidence: " + confidence);
        System.out.println("Road name: " + roadName);*/
        System.out.println("Current Flow Speed is : "+currSpeed);
        System.out.println("Free Flow Speed is : "+FreeFlowSpeed);
        System.out.println("Current Travel time is : "+CurrentTravelTime);
        System.out.println("Free Flow travel time is : "+FreeFlowtTravelTime);
        System.out.println("Confidence level of data is : "+Confidence);
    }

    private static String sendHttpRequest(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

