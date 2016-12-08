package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.implementation.CustomerDaoJdbc;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.MailMan;
import com.codecool.shop.model.Order;
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

    protected static String loginStatus(Boolean status) {
        if (status == null) {
            return null;
        } else if (status) {
            return "Login Success";
        } else {
            return "Invalid username or password";
        }
    }

    public static String collectShippingBilling(Request req, Response res) {
        ArrayList<String> shippingList = new ArrayList<>();
        String doYouSave = req.queryParams("save");

        if(doYouSave != null) {
            shippingList.add(String.valueOf(req.queryParams("email")));
            shippingList.add(String.valueOf(req.queryParams("shippingcountry")));
            shippingList.add(String.valueOf(req.queryParams("shippingcity")));
            shippingList.add(String.valueOf(req.queryParams("shippingzipcode")));
            shippingList.add(String.valueOf(req.queryParams("shippingaddress")));
            shippingList.add(String.valueOf(req.queryParams("billingcountry")));
            shippingList.add(String.valueOf(req.queryParams("billingcity")));
            shippingList.add(String.valueOf(req.queryParams("billingzipcode")));
            shippingList.add(String.valueOf(req.queryParams("billingaddress")));


            Customer.updateShippingBillingCustomer(shippingList);
        }
        res.redirect("/payment");
        return null;

    }
}
