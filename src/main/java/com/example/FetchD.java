package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FetchD {

    public static void main(String[] args) throws Exception {
        // Define the base URL and API key
        String baseURL = "https://api.tomtom.com/traffic/services/4/flowSegmentData/absolute/10/json";
        String apiKey = "5jwydh7cjhv8Db5BkKqc3m9bi3laGnRF";

        // Define the point for the location of the area you want to fetch data for
        String point1 = "18.481108,73.872244"; // replace lat and lon with actual coordinates
        String point2 = "18.480846,73.872694";
        String point3 = "18.480463,73.872370";
        String point4 = "18.480726,73.871991";

        // Define additional query parameters and encode their values
        String unit = "KMPH";
        String thickness = "10";
        String openLr = "false";
        String jsonp = "jsoncallback";

        // Construct the URL for the API call
        String url1 = baseURL + "?key=" + apiKey + "&point=" + point1 + "&unit=" + unit + "&thickness=" + thickness + "&openLr=" + openLr + "&jsonp=" + jsonp;
        String url2 = baseURL + "?key=" + apiKey + "&point=" + point2 + "&unit=" + unit + "&thickness=" + thickness + "&openLr=" + openLr + "&jsonp=" + jsonp;
        String url3 = baseURL + "?key=" + apiKey + "&point=" + point3 + "&unit=" + unit + "&thickness=" + thickness + "&openLr=" + openLr + "&jsonp=" + jsonp;
        String url4 = baseURL + "?key=" + apiKey + "&point=" + point4 + "&unit=" + unit + "&thickness=" + thickness + "&openLr=" + openLr + "&jsonp=" + jsonp;
        // Send the HTTP request and retrieve the response
        String flowSegmentData1 = sendHttpRequest(url1);
        String flowSegmentData2 = sendHttpRequest(url2);
        String flowSegmentData3 = sendHttpRequest(url3);
        String flowSegmentData4 = sendHttpRequest(url4); 

        // Parse the JSON data using Gson

        //For road 1
        
        JsonObject jsonObject1 = new Gson().fromJson(flowSegmentData1, JsonObject.class);

        int currSpeed1 = jsonObject1.get("flowSegmentData").getAsJsonObject().get("currentSpeed").getAsInt();
        int FreeFlowSpeed1 = jsonObject1.get("flowSegmentData").getAsJsonObject().get("freeFlowSpeed").getAsInt();
        int CurrentTravelTime1 = jsonObject1.get("flowSegmentData").getAsJsonObject().get("currentTravelTime").getAsInt();
        int FreeFlowtTravelTime1 = jsonObject1.get("flowSegmentData").getAsJsonObject().get("freeFlowTravelTime").getAsInt();
        int Confidence1 = jsonObject1.get("flowSegmentData").getAsJsonObject().get("confidence").getAsInt();

        //for road 2
        JsonObject jsonObject2 = new Gson().fromJson(flowSegmentData2, JsonObject.class);

        int currSpeed2 = jsonObject2.get("flowSegmentData").getAsJsonObject().get("currentSpeed").getAsInt();
        int FreeFlowSpeed2 = jsonObject2.get("flowSegmentData").getAsJsonObject().get("freeFlowSpeed").getAsInt();
        int CurrentTravelTime2 = jsonObject2.get("flowSegmentData").getAsJsonObject().get("currentTravelTime").getAsInt();
        int FreeFlowtTravelTime2 = jsonObject2.get("flowSegmentData").getAsJsonObject().get("freeFlowTravelTime").getAsInt();
        int Confidence2 = jsonObject2.get("flowSegmentData").getAsJsonObject().get("confidence").getAsInt();

        //For road 3
        JsonObject jsonObject3 = new Gson().fromJson(flowSegmentData3, JsonObject.class);

        int currSpeed3 = jsonObject3.get("flowSegmentData").getAsJsonObject().get("currentSpeed").getAsInt();
        int FreeFlowSpeed3 = jsonObject3.get("flowSegmentData").getAsJsonObject().get("freeFlowSpeed").getAsInt();
        int CurrentTravelTime3 = jsonObject3.get("flowSegmentData").getAsJsonObject().get("currentTravelTime").getAsInt();
        int FreeFlowtTravelTime3 = jsonObject3.get("flowSegmentData").getAsJsonObject().get("freeFlowTravelTime").getAsInt();
        int Confidence3 = jsonObject3.get("flowSegmentData").getAsJsonObject().get("confidence").getAsInt();

        //For Road 4
        JsonObject jsonObject4 = new Gson().fromJson(flowSegmentData4, JsonObject.class);

        int currSpeed4 = jsonObject4.get("flowSegmentData").getAsJsonObject().get("currentSpeed").getAsInt();
        int FreeFlowSpeed4 = jsonObject4.get("flowSegmentData").getAsJsonObject().get("freeFlowSpeed").getAsInt();
        int CurrentTravelTime4 = jsonObject4.get("flowSegmentData").getAsJsonObject().get("currentTravelTime").getAsInt();
        int FreeFlowtTravelTime4 = jsonObject4.get("flowSegmentData").getAsJsonObject().get("freeFlowTravelTime").getAsInt();
        int Confidence4 = jsonObject4.get("flowSegmentData").getAsJsonObject().get("confidence").getAsInt();
        
        
        /*System.out.println(flowSegmentData1);
        System.out.println("and for second road is");
        System.out.println(flowSegmentData2);*/

        //For Road 1
        System.out.println("Details for Road 1 are :");
    
        System.out.println("Current Flow Speed is : "+currSpeed1);
        System.out.println("Free Flow Speed is : "+FreeFlowSpeed1);
        System.out.println("Current Travel time is : "+CurrentTravelTime1);
        System.out.println("Free Flow travel time is : "+FreeFlowtTravelTime1);
        System.out.println("Confidence level of data is : "+Confidence1);
        System.out.println();
        //For Road 2
        System.out.println("Details for Road 2 are :");
        System.out.println("Current Flow Speed is : "+currSpeed2);
        System.out.println("Free Flow Speed is : "+FreeFlowSpeed2);
        System.out.println("Current Travel time is : "+CurrentTravelTime2);
        System.out.println("Free Flow travel time is : "+FreeFlowtTravelTime2);
        System.out.println("Confidence level of data is : "+Confidence2);
        System.out.println();

        //For Road 3
        System.out.println("Details for Road 3 are :");
        System.out.println("Current Flow Speed is : "+currSpeed3);
        System.out.println("Free Flow Speed is : "+FreeFlowSpeed3);
        System.out.println("Current Travel time is : "+CurrentTravelTime3);
        System.out.println("Free Flow travel time is : "+FreeFlowtTravelTime3);
        System.out.println("Confidence level of data is : "+Confidence3);
        System.out.println();

        //For Road 4
        System.out.println("Details for Road 4 are :");
        System.out.println("Current Flow Speed is : "+currSpeed4);
        System.out.println("Free Flow Speed is : "+FreeFlowSpeed4);
        System.out.println("Current Travel time is : "+CurrentTravelTime4);
        System.out.println("Free Flow travel time is : "+FreeFlowtTravelTime4);
        System.out.println("Confidence level of data is : "+Confidence4);
          
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
