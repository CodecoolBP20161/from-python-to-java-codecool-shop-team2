package com.codecool.shop.controller;

import com.codecool.shop.model.LineItem;
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

class APIController {


    static ModelAndView renderDeliverySummary(Request req, Response res) throws IOException, URISyntaxException {

        Order orderDataStore = req.session().attribute("order");
        float PRICE = orderDataStore.getAllPrice();
        List<LineItem> ORDER = orderDataStore.getList();
        Map<String, Object> params = new HashMap<>();
        Double cost = DeliveryCostController.calcDeliveryCost(req, res);
        String time = DeliveryTimeController.getTimeInMinute(calcDeliveryTime(req, res));

        params.put("price", PRICE);
        params.put("order", ORDER);
        params.put("cost", cost);
        params.put("time", time);

        req.session().attribute("cost", cost);
        req.session().attribute("time", time);

        return new ModelAndView(params, "product/summary");
    }
}
