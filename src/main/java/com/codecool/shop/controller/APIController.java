package com.codecool.shop.controller;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class APIController {

    public static ModelAndView renderDeliverySummary(Request req, Response res) throws IOException, URISyntaxException {
        Map params = new HashMap<>();
        params.put("cost", DeliveryCostController.calcDeliveryCost(req, res));
        params.put("time", DeliveryTimeController.calcDeliveryTime(req, res));
        return new ModelAndView(params, "product/summary");
    }
}
