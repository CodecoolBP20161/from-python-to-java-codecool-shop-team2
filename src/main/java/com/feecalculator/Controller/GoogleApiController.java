package com.feecalculator.Controller;

import com.google.gson.internal.LinkedTreeMap;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by balint on 1/3/17.
 */
public class GoogleApiController extends JsonTransformer{

    private final String API_KEY;
    private final String SERVICE_URL;
    private String webshop;
    private String target;
    private String responseJSON;

    public GoogleApiController(String webshop, String target) {
        this.SERVICE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";
        this.API_KEY = "AIzaSyBHcG9di92OUUpCrzLXgq4Er4AlOo1cFV4";
        this.webshop = webshop;
        this.target = target;
        connect();
    }

    private void connect() {
        String params = "?origins=" + this.webshop + "&destinations=" + this.target + "&key=" + API_KEY;
        this.responseJSON = execute(params);
    }

    public double getDistance() {
        double distance = (double)((LinkedTreeMap)((LinkedTreeMap)((ArrayList)((LinkedTreeMap)
                Arrays.asList(((List)parse(responseJSON)
                .get("rows")))
                .get(0)
                .get(0))
                .get("elements"))
                .get(0))
                .get("distance"))
                .get("value");

        return distance;
    }

    public String getStatus() {
        String status = ((LinkedTreeMap)((ArrayList)((LinkedTreeMap)
                Arrays.asList(
                (List)parse(responseJSON).get("rows"))
                .get(0)
                .get(0))
                .get("elements"))
                .get(0))
                .get("status")
                .toString();
        return status;
    }

    public boolean countryCheck() {
        String destination = (String) (Arrays.asList((List)parse(responseJSON).get("destination_addresses")).get(0)).get(0);
        String origin = (String) (Arrays.asList((List)parse(responseJSON).get("origin_addresses")).get(0)).get(0);
        String[] destinationArray = destination.split(" ");
        String[] originArray = origin.split(" ");
        String destinationCountry = destinationArray[destinationArray.length - 1];
        String originCountry = originArray[originArray.length - 1];
        boolean is_same = destinationCountry.equals(originCountry);
        return is_same;
    }

    public boolean continentCheck() {
        String status = getStatus();
        boolean is_same = !status.equals("ZERO_RESULTS");
        return is_same;
    }

    public boolean validCheck() {
        String status = getStatus();
        boolean is_valid = !status.equals("NOT_FOUND");
        return  is_valid;
    }

    private String execute(String url) {
        try {
            URI uri = new URIBuilder(SERVICE_URL + url).build();
            return org.apache.http.client.fluent.Request.Get(uri).execute().returnContent().asString();
        } catch (URISyntaxException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
