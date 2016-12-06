package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.implementation.CustomerDaoJdbc;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.MailMan;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

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

    public static String checkCustomer(Request req, Response res) {
        CustomerDao customerDataStore = CustomerDaoJdbc.getInstance();
        for(Customer user : customerDataStore.getAll()) {
            if (user.getCustomerName().equals(req.queryParams("username"))) {
                Boolean isVerified = user.verifyPassword(req.queryParams("username"), req.queryParams("password"));
                req.session().attribute("loginStatus", isVerified);
                res.redirect("/");
                return null;
            }
        }
        req.session().attribute("loginStatus", null);
        res.redirect("/");
        return null;
    }
}
