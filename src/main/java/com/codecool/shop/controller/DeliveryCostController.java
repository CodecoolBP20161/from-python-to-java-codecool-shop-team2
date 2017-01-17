package com.codecool.shop.controller;

import java.io.IOException;
import spark.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import spark.Response;

import java.net.URI;
import java.net.URISyntaxException;

public class DeliveryCostController {

    private static final String API_URL = "http://localhost:9999/api";
    private static final String WEBSHOPLOCATION = "Budapest";

    public static String calcDeliveryCost(Request req, Response res) throws IOException, URISyntaxException {
        String costFee = null;

        try {
            JSONObject costAsString = getFeeJson(WEBSHOPLOCATION, req.queryParams("shippingcity"));
            costFee = costAsString.get("cost").toString().replaceAll("[^\\d.]", "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Double costAsDouble = Math.ceil(Double.parseDouble(costFee));
        return costAsDouble.toString() + " $";
    }

    public static JSONObject getFeeJson(String webshop, String target) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL);
        builder.addParameter("webshop", webshop);
        builder.addParameter("target", target);
        JSONObject obj = new JSONObject(execute(builder.build()));
        return obj;
    }

    private static String execute(URI uri) throws IOException {
        return org.apache.http.client.fluent.Request.Get(uri).execute().returnContent().asString();
    }
}
