package com.codecool.shop.controller;


import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codecool.shop.controller.DeliveryTimeController.calcDeliveryTime;

public class APIController {


    public static ModelAndView renderDeliverySummary(Request req, Response res) throws IOException, URISyntaxException {


        Order orderDataStore = req.session().attribute("order");
        float PRICE = orderDataStore.getAllPrice();
        List ORDER = orderDataStore.getList();
        Map params = new HashMap<>();
        params.put("price", PRICE);
        params.put("order", ORDER);
        params.put("cost", DeliveryCostController.calcDeliveryCost(req, res));
        params.put("time", DeliveryTimeController.getTimeInMinute(calcDeliveryTime(req, res)));
        return new ModelAndView(params, "product/summary");
    }
}
