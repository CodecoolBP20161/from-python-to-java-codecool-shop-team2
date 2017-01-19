package com.codecool.shop.controller;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class DeliveryCostController {

    private static final String API_URL = "http://localhost:9999/api";
    private static final String WEBSHOPLOCATION = "Budapest";

    static double calcDeliveryCost(Request req, Response res) throws IOException, URISyntaxException {
        String costFee = null;

        try {
            JSONObject costAsString = getFeeJson(WEBSHOPLOCATION, req.queryParams("shippingcity"));
            costFee = costAsString.get("cost").toString().replaceAll("[^\\d.]", "");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        assert costFee != null;
        return Math.ceil(Double.parseDouble(costFee));
    }

    private static JSONObject getFeeJson(String webshop, String target) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL);
        builder.addParameter("webshop", webshop);
        builder.addParameter("target", target);
        return new JSONObject(execute(builder.build()));
    }

    private static String execute(URI uri) throws IOException {
        return org.apache.http.client.fluent.Request.Get(uri).execute().returnContent().asString();
    }
}
