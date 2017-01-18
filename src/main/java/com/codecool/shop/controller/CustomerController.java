package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.implementation.CustomerDaoJdbc;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.MailMan;
import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerController {

    public static ModelAndView renderRegistration(Request req, Response res){
        return new ModelAndView(new HashMap<>(), "product/registration");
    }

    public static String addCustomer(Request req, Response res) {
        CustomerDao customerDataStore = CustomerDaoJdbc.getInstance();
        // get the customer data from client, then check it exist, after that add and send mail
        // Customer customer = Customer.getFromClient(req.body());
        Customer customer = new Customer(
                req.queryParams("username"), req.queryParams("email"), null, req.queryParams("password"));
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
        // if the use want to logout, remove the session, set null (for safety)
        // and overwrite the order (in the session) with a new one
        if (logout) {
            req.session().removeAttribute("loginStatus");
            req.session().attribute("loginStatus", null);
            Order orderDataStore = new Order();
            req.session().attribute("order", orderDataStore);
            res.redirect("/");
            return null;
        }

        // if the user send the login data to the server, here process these
        CustomerDao customerDataStore = CustomerDaoJdbc.getInstance();
        for(Customer user : customerDataStore.getAll()) {
            if (user.getCustomerName().equals(req.queryParams("username"))) {
                //verify the user, and "put" the result in the session
                Boolean isVerified = user.verifyPassword(req.queryParams("username"), req.queryParams("password"));
                req.session().attribute("loginStatus", isVerified);
                res.redirect("/");
                return null;
            }
        }
        // if the user given wrong name or password:
        req.session().attribute("loginStatus", false);
        res.redirect("/");
        return null;
    }
    // this is necessary for the html
    protected static String loginStatus(Boolean status) {
        if (status == null) {
            return null;
        } else if (status) {
            return "Login Success";
        } else {
            return "Invalid username or password";
        }
    }

    // collecting data from the shipping form
    // if the user checked the box, update the customer data in the database
    public static ModelAndView collectShippingBilling(Request req, Response res) throws IOException, URISyntaxException {
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

            // at more info look at the Customer model
            Customer.updateShippingBilling(shippingList);
        }

        return APIController.renderDeliverySummary(req, res);
    }
}
