package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.*;

// this class contains the methods for the routes in the Main class
public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        // initialize data for renderProducts
        ProductDao productDataStore = ProductDaoJdbc.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();

        Map params = new HashMap<>();

        int valueFromHtml;
        // get values from the client
        String stringValueFromHtml = req.params(":id");
        String stringValueName = req.params(":name");

        // create int from the string one way or another:
       if (stringValueFromHtml!= null){
           valueFromHtml = Integer.parseInt(stringValueFromHtml);
       }
       else {
           valueFromHtml = 0;
       }
       // search based on the given values, then put the products to the param HashMap
        if (valueFromHtml == 0) {
            params.put("categories", productCategoryDataStore.getAll());
            params.put("products", productDataStore.getAll());
        }
        else {
            if (stringValueName.equals("category") ) {
                params.put("selected_category", productCategoryDataStore.find(valueFromHtml));
                params.put("products", productDataStore.getBy(productCategoryDataStore.find(valueFromHtml)));
            }
            else {
                params.put("selected_category", supplierDataStore.find(valueFromHtml));
                params.put("products", productDataStore.getBy(supplierDataStore.find(valueFromHtml)));
            }
        }

        // create a new session if the the user is new on the site
        // create and put the user's Order object into the new session
        if (req.session().isNew()) {
            Order orderDataStore = new Order();
            req.session().attribute("order", orderDataStore);
            // put all quantity into params HashMap
            params.put("allQuantity", orderDataStore.getAllQuantity());
        }
        else {
            // if the user have a session, then get own Order object
            Order orderDataStore = req.session().attribute("order");
            params.put("allQuantity", orderDataStore.getAllQuantity());
        }
        // send params HashMap to the client
        return new ModelAndView(params, "product/index");
    }

    // add products to the user's Order object
    public static String addProducts(Request req, Response res) {
        // find the selected product
        Integer id = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = ProductDaoJdbc.getInstance();
        Product result = productDataStore.find(id);
        // give to the Order's object into the session
        Order orderDataStore = req.session().attribute("order");
        orderDataStore.addItem(new LineItem(result));
        res.redirect("/");
        return null;
    }

    // render the review page
    public static ModelAndView renderReview(Request req, Response res) {
        // if the user is new and visit this page first, then create her/his own session, then send the home page
        if (req.session().isNew()){
            Order orderDataStore = new Order();
            req.session().attribute("order", orderDataStore);
            res.redirect("/");
        }
        // get user's session
        Order orderDataStore = req.session().attribute("order");
        float price = orderDataStore.getAllPrice();
        // create the params HashMap, then fill it the necessary data, after that, send the HashMap to tha client
        Map params = new HashMap<>();
        params.put("order", orderDataStore.getList());
        params.put("price", price);
        return new ModelAndView(params, "product/review");
    }

    // if the user edit the item quantity on the review site, then this method process that
    public static String editProducts(Request req, Response res) {
        // get user's session
        Order orderDataStore = req.session().attribute("order");
        // create an ArrayList, then get data from the client and add to it
        // (the received data is a string, what split three parts, before add them to the List)
        List<String> editAttr = new ArrayList<>(Arrays.asList(req.params(":lineItem").split("_")));
        try {
            // run the editItem method and put the new Order's object in the session
            req.session().attribute("order", orderDataStore.editItem(editAttr));
        }
        // (if the client send an invalid value in editAttr List, then catch the exception)
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        res.redirect("/review");
        return null;
    }

    // render the payment page
    public static ModelAndView renderPayment(Request req, Response res) {
        // if the user is new and visit this page first, then create her/his own session, then send the home page
        if (req.session().isNew()){
            Order orderDataStore = new Order();
            req.session().attribute("order", orderDataStore);
            res.redirect("/");
        }
        // get user's session
        Order orderDataStore = req.session().attribute("order");
        float price = orderDataStore.getAllPrice();
        // create the params HashMap, then fill it the necessary data, after that, send the HashMap to tha client
        Map params = new HashMap<>();
        params.put("order", orderDataStore.getList());
        params.put("price", price);
        return new ModelAndView(params, "product/payment");
    }
}
