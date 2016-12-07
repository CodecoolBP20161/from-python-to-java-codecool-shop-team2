package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.implementation.CustomerDaoJdbc;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.MailMan;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerController {

    public static ModelAndView renderRegistration(Request req, Response res){
        return new ModelAndView(new HashMap<>(), "product/registration");
    }

    public static String addCustomer(Request req, Response res) {
        CustomerDao customerDataStore = CustomerDaoJdbc.getInstance();
        // get the customer data from client, then check it exist, after that add and send mail
        Customer customer = Customer.getFromClient(req.body());
        String customerFree = customerDataStore.verifyCustomer(customer);
        if (!customerFree.equals("OK")) {
            System.out.println(customerFree);
            res.redirect("/registration");
            return null;
        }
        req.session().removeAttribute("customerStatus");
        customerDataStore.add(customer);
        res.redirect("/");
        MailMan email = new MailMan();
        email.sendWelcome(customer.getEmail());
        return null;
    }

    public static String collectShipping(Request req, Response res) {
        ArrayList<String> shippingList = new ArrayList<>();
        String doYouSave = req.queryParams("save");

        if(doYouSave != null) {
            shippingList.add(String.valueOf(req.queryParams("email")));
            shippingList.add(String.valueOf(req.queryParams("shippingcountry")));
            shippingList.add(String.valueOf(req.queryParams("shippingcity")));
            shippingList.add(String.valueOf(req.queryParams("shippingzipcode")));
            shippingList.add(String.valueOf(req.queryParams("shippingaddress")));
            Customer.updateShippingCustomer(shippingList);
        }
        res.redirect("/");
        return null;
    }
}
