package com.codecool.shop.controller;

import com.codecool.shop.Main;
import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.implementation.CustomerDaoJdbc;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.EmailType;
import com.codecool.shop.model.MailMan;
import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the methods for the routes (what is about the customer).
 * @see Main
 */
public class CustomerController {

    private static CustomerDao customerDataStore = CustomerDaoJdbc.getInstance();

    /**
     * This method responsible for rendering the index page.
     *
     * @param req Request from client
     * @param res Response to client
     * @return the index registration template
     */
    public static ModelAndView renderRegistration(Request req, Response res){
        return new ModelAndView(new HashMap<>(), "product/registration");
    }

    /**
     * Create a new customer for the shop, based on registration form data. Verifying the new customer.
     * If not OK, then reload the registration page, and return null. Anyway navigating the index page
     * and send a welcome mail to the new user.
     *
     * @param req Request from client
     * @param res Response to client
     * @return null
     */
    public static String addCustomer(Request req, Response res) {
        Customer customer = new Customer(
                req.queryParams("username"), req.queryParams("email"), null, req.queryParams("password"));
        MailMan email = new MailMan(EmailType.WELCOME_MESSAGE, req.queryParams("username"));
        String customerFree = customerDataStore.verifyCustomer(customer);

        if (!customerFree.equals("OK")) {
            res.redirect("/registration");
            return null;
        }
        customerDataStore.add(customer);
        res.redirect("/");
        email.sendWelcome(customer.getEmail());
        return null;
    }

    /**
     * Manage user operations:
     * - Logging out the users, if they want it, remove they session and
     *   overwrite the order (in the session) with a new one.
     * - If users want to log in, then get the data from client, then
     *   verifying based on database. After that send result to the client.
     *
     * @param req Request from client
     * @param res Response to client
     * @return null
     */
    public static String checkCustomer(Request req, Response res) {
        Boolean logout = Boolean.parseBoolean(req.queryParams("logout"));
        if (logout) {
            req.session().removeAttribute("loginStatus");
            req.session().attribute("loginStatus", null);
            Order orderDataStore = new Order();
            req.session().attribute("order", orderDataStore);
            res.redirect("/");
            return null;
        }
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

    /**
     * Create a status message from user status.
     *
     * @param status from session
     * @return status message as String
     */
    static String loginStatus(Boolean status) {
        if (status == null) {
            return null;
        } else if (status) {
            return "Login Success";
        } else {
            return "Invalid username or password";
        }
    }

    /**
     * Collecting data from the shipping form. If the user checked the box (on form),
     * then update the customer data in the database. Finally, navigating the user to the summary page.
     *
     * @see Customer#updateShippingBilling(ArrayList) method, if you need more info about customer table update.
     * @param req Request from client
     * @param res Response to client
     * @return null
     */
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

            Customer.updateShippingBilling(shippingList);
        }

        req.session().attribute("name", req.queryParams("name"));
        req.session().attribute("email", req.queryParams("email"));
        return APIController.renderDeliverySummary(req, res);
    }

    /**
     * Collect customers data from session, what is necessary for the email.
     * Then send it with a information about the order and shipping.
     * Finally, navigating the index page.
     *
     * @param req Request from client
     * @param res Response to client
     * @return status message
     */
    public static String sendCheckoutEmail(Request req, Response res) {
        Order order = req.session().attribute("order");
        String name = req.session().attribute("name");
        Double cost = req.session().attribute("cost");
        String time = req.session().attribute("time");
        String email = req.session().attribute("email");
        Float allPrice = order.getAllPrice();
        MailMan mail = new MailMan(EmailType.SUMMERY_MESSAGE, name, cost, time, allPrice);
        mail.sendSummary(email);
        res.redirect("/");
        return "Checkout email was sent";
    }
}
