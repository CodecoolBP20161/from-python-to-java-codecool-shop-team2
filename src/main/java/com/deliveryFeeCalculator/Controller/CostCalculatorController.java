package com.deliveryFeeCalculator.Controller;
import com.deliveryFeeCalculator.Service.CostCalculator;
import spark.Request;
import java.util.HashMap;

/**
 * Created by balint on 1/3/17.
 */
public class CostCalculatorController extends JsonTransformer {

    private Request request;

    public CostCalculatorController(Request request) {
        this.request = request;
    }

    public String calculatePostalCost () {
        String target = request.queryParams("target");
        String webshop = request.queryParams("webshop");
        HashMap responseData = new HashMap();

        if (target == null || webshop == null) {
            responseData.put("cost", "Could not calculate");
            responseData.put("status", "Missing parameters");
        } else {
            CostCalculator service = new CostCalculator(target, webshop);
            String fee = service.getFee();
            responseData.put("cost", fee);
            if (responseData.get("cost").equals("Could not calculate")) {
                responseData.put("status", "Invalid parameters");
            } else {
                responseData.put("status", "Completed");
            }
        }

        String responseJson = stringify(responseData);
        return responseJson;
    }
}
