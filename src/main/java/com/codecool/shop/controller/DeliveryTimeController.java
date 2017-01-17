package com.codecool.shop.controller;


import org.apache.http.client.utils.URIBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class DeliveryTimeController {

    private static final String API_URL = "http://0.0.0.0:60003/api/timecalculator/";
    private static final String ORIGIN = "Amterdam";


    public static String calcDeliveryTime(Request req, Response res){
        String time = null;
        try {
            JSONObject obj = getTimeJson(ORIGIN, String.valueOf(req.queryParams("shippingcity")));
            time = obj.get("time").toString();
        } catch (URISyntaxException | JSONException | IOException e) {
            e.printStackTrace();
        }
        getTimeInMinute(time);
        return time;
    }

    public static String getTimeInMinute(String timeMs){
        Long ms = Long.valueOf(timeMs);
        String timeInHourMinute = String.format("%02d day %02d hours",TimeUnit.MILLISECONDS.toDays(ms), TimeUnit.MILLISECONDS.toHours(ms));
        System.out.println(timeInHourMinute);
        return timeInHourMinute;
    }

    public static JSONObject getTimeJson(String origin, String target) throws URISyntaxException, IOException, JSONException {

        URIBuilder builder = new URIBuilder(API_URL + origin + "/" + target);

        return new JSONObject(execute(builder.build()));
    }

    private static String execute(URI uri) throws IOException {
        return org.apache.http.client.fluent.Request.Get(uri).execute().returnContent().asString();
    }
}
