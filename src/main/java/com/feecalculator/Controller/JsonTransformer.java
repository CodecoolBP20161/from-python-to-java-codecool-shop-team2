package com.feecalculator.Controller;

import com.google.gson.Gson;
import spark.Request;

import java.util.HashMap;

/**
 * Created by balint on 1/3/17.
 */
public abstract class JsonTransformer {

    public String stringify (Object model) {
        Gson gson = new Gson();
        return gson.toJson(model);
    }

    public HashMap parse (Request request) {
        Gson gson = new Gson();
        return gson.fromJson(request.body(), HashMap.class);
    }

    public HashMap parse (String jsonStrig) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStrig, HashMap.class);
    }
}
