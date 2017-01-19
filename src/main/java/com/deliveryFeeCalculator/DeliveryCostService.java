package com.deliveryFeeCalculator;

import com.deliveryFeeCalculator.Controller.CostCalculatorController;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class DeliveryCostService {
    public static void main(String[] args) {

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFiles.location("/public");
        port(9999);

        get("/api", new Route(){
            @Override
            public String handle(Request request, Response response) throws Exception {
                CostCalculatorController controller = new CostCalculatorController(request);
                String responseJSON = controller.calculatePostalCost();
                return responseJSON;
            }
        });

    }
}
