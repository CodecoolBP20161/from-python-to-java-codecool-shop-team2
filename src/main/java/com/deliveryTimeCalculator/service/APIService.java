package com.deliveryTimeCalculator.service;
/*
Here get the json from Google and get back for API.
 */

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class APIService {

    private static final String API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?";
    private static final String API_KEY = "AIzaSyD9emeRrTuCeAOMtvFKSZOmnal0K1jgt2g";

    public APIService() {}

    private static APIService INSTANCE;

    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    public String calcTime(String origin, String destination) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL);

        builder.addParameter("units", "metric");
        builder.addParameter("origins", origin);
        builder.addParameter("destinations", destination);
        builder.addParameter("key", API_KEY);

        return execute(builder.build());
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri).execute().returnContent().asString();
    }
}
