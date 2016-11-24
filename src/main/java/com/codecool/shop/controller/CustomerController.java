package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class CustomerController {

    public static ModelAndView renderRegistration(Request req, Response res){
        return new ModelAndView(new HashMap<>(), "product/registration");
    }

    public static String addCustomer(Request req, Response res) {
        res.redirect("/");
        return null;
    }
}
