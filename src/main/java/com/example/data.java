package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class data {
    private double CurrentSpeed;
    private double FreeFlowSpeed;

    public data(String point, String roadName) throws Exception{
        
        String baseURL = "https://api.tomtom.com/traffic/services/4/flowSegmentData/absolute/10/json";
        String apiKey = "5jwydh7cjhv8Db5BkKqc3m9bi3laGnRF";

        String unit = "KMPH";
        String thickness = "20";
        String openLr = "false";
        String jsonp = "jsoncallback";

        // Construct the URL for the API call
        String url = baseURL + "?key=" + apiKey + "&point=" + point + "&unit=" + unit + "&thickness=" + thickness + "&openLr=" + openLr + "&jsonp=" + jsonp;

        // Send the HTTP request and retrieve the response
        String FlowSegmentData = sendHttpRequest1(url);

        //System.out.println(FlowSegmentData);
        JsonObject jsonObject = new Gson().fromJson(FlowSegmentData, JsonObject.class);

        this.CurrentSpeed = jsonObject.get("flowSegmentData").getAsJsonObject().get("currentSpeed").getAsDouble();
        this.FreeFlowSpeed = jsonObject.get("flowSegmentData").getAsJsonObject().get("freeFlowSpeed").getAsDouble();
        int CurrentTime = jsonObject.get("flowSegmentData").getAsJsonObject().get("currentTravelTime").getAsInt();
        int FreeTime = jsonObject.get("flowSegmentData").getAsJsonObject().get("freeFlowTravelTime").getAsInt();
        double DataLevelConfidence = jsonObject.get("flowSegmentData").getAsJsonObject().get("confidence").getAsDouble();
        
        System.out.println("Traffic data for "+ roadName +" road is as follows - "); 
        System.out.println("Current Speed for the road is : "+ CurrentSpeed +" kmph");
        System.out.println("FreeFlow Speed for the Road is : "+ FreeFlowSpeed +" kmph");
        System.out.println("Current Travel Time for the Road segment is : "+ CurrentTime+" seconds");
        System.out.println("Free Flow travel time for the Road segment is :"+ FreeTime +" seconds");
        System.out.println("Confidence level for the data is : "+ DataLevelConfidence);
        System.out.println(" ");   
    }

    public double getCurrentSpeed(){
        return this.CurrentSpeed;
    }

    public double getFreeFlowSpeed(){
        return this.FreeFlowSpeed;
    }

    public static String sendHttpRequest1(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()  
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public static void main(String[] args) throws Exception {
        /*data D = new data("18.480747,73.871753", "From bibwewadi");
        System.out.println(D.getCurrent());
        System.out.println(D.getFree());

        data t = new data("18.480747,73.871753", "From market yard");
        System.out.println(t.getCurrent());
        System.out.println(t.getFree());*/

        

        /*int[] arr = new int[2];2
        arr[0] = D.getCurrent();
        arr[1] = t.getCurrent(); 
        System.out.println(arr);*/
        /*D.getData("18.480747,73.871753", "From bibwewadi");
        D.getData("18.480747,73.871753", "From market yard");
        D.getData("18.480748,73.872528","From kondwa");
        D.getData("18.480566,73.872281", "From clg");*/

}
}
