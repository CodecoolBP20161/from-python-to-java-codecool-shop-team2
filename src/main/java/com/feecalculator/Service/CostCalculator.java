package com.feecalculator.Service;

import com.feecalculator.Controller.GoogleApiController;

/**
 * Created by balint on 1/3/17.
 */
public class CostCalculator {

    private String target;
    private String webshop;
    private double baseFee;
    private double distanceMultiplier;
    private double foreignMultiplier;
    private double differentContinentFee;
    private GoogleApiController googleApiController;

    public CostCalculator (String target, String webshop) {
        this.target = target;
        this.webshop = webshop;
        this.baseFee = 3;
        this.distanceMultiplier = 0.0000035;
        this.foreignMultiplier = 1.5;
        this.differentContinentFee = 30;
        this.googleApiController = new GoogleApiController(this.target, this.webshop);
    }

    public double getFeeFromDistance() {
        double distance = googleApiController.getDistance();
        double distanceFee = distance * distanceMultiplier;

        return distanceFee;
    }

    public double getFeeForForeignTransfer() {
        boolean is_same = googleApiController.countryCheck();
        if (is_same) {
            return 1;
        }
        return foreignMultiplier;
    }

    public boolean isValid() {
        boolean isValid = googleApiController.validCheck();

        return isValid;
    }

    public boolean isSameContinent() {
        boolean isValid = googleApiController.continentCheck();

        return isValid;
    }

    public String getFee() {
        if (!isValid()) {
            return "Could not calculate";
        }

        if (!isSameContinent()) {
            return Double.toString(differentContinentFee) + " $";
        }

        double additionalFee = 0;

        additionalFee += getFeeFromDistance();
        additionalFee *= getFeeForForeignTransfer();

        String fee = Double.toString(baseFee + additionalFee) + " $";
        return fee;
    }
}
