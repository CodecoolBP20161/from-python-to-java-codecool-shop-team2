package com.deliveryTimeCalculator;
/*
Mapping all route for the API and link with the APIController.
 */

import com.deliveryTimeCalculator.service.APIService;
import com.deliveryTimeCalculator.controller.APIController;

import static spark.Spark.get;
import static spark.Spark.port;

public class TimeGeneratorService {
    private APIController controller;

    public static void main(String[] args) {
        port(60003);

        TimeGeneratorService application = new TimeGeneratorService();
        application.controller = new APIController(APIService.getInstance());

        // --- MAPPING ---
        //for e.g.: http://0.0.0.0:60003/api/timecalculator/Budapest/Érd
        get("/api/timecalculator/:origin/:destination", application.controller::location);
    }

}
