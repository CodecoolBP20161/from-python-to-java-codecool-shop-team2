package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.implementation.CustomerDaoJdbc;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.MailMan;
import com.codecool.shop.model.Order;
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
        Boolean logout = Boolean.parseBoolean(req.queryParams("logout"));
        if (logout) {
            // really deleted session attribute
            req.session().removeAttribute("loginStatus");
            req.session().attribute("loginStatus", null);
            Order orderDataStore = new Order();
            req.session().attribute("order", orderDataStore);
            res.redirect("/");
            return null;
        }

        CustomerDao customerDataStore = CustomerDaoJdbc.getInstance();
        for(Customer user : customerDataStore.getAll()) {
            if (user.getCustomerName().equals(req.queryParams("username"))) {
                Boolean isVerified = user.verifyPassword(req.queryParams("username"), req.queryParams("password"));
                req.session().attribute("loginStatus", isVerified);
                res.redirect("/");
                return null;
            }
        }
        req.session().attribute("loginStatus", false);
        res.redirect("/");
        return null;
    }

    protected static String loginStatus(Boolean status){
        if(status == null){
            return null;
        }
        else if(status){
            return "Login Success";
        }else{
            return"Invalid username or password";
        }
    }
}
