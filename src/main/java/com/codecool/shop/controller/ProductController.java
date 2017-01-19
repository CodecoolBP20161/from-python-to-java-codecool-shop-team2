package com.codecool.shop.controller;

import com.codecool.shop.Main;
import com.codecool.shop.dao.ProductCategoryDao;
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

/**
 * This class contains the methods for the routes (what is about the products).
 * @see Main
 */
public class ProductController {

    /**
     * This store the product data access object instance for database.
     */
    private static ProductDaoJdbc productDataStore = ProductDaoJdbc.getInstance();

    /**
     * This method responsible for rendering the index page.
     * Initialize the data access objects and create the params HashMap.
     * Get the products id and name, what the users want to see on the page,
     * then put them into the params. Handle the cart based on users Order by store in sessions.
     * (The log in don't overwrite the items in the cart.)
     *
     * @param req Request from client
     * @param res Response to client
     * @return the index page template
     */
    public static ModelAndView renderProducts(Request req, Response res) {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();

        Map<String, Object> params = new HashMap<>();

        Boolean status = req.session().attribute("loginStatus");
        params.put("loginStatus", CustomerController.loginStatus(status));

        int valueFromHtml;
        String stringValueFromHtml = req.params(":id");
        String stringValueName = req.params(":name");

        if (stringValueFromHtml != null) {
            valueFromHtml = Integer.parseInt(stringValueFromHtml);
        } else {
            valueFromHtml = 0;
        }

        if (valueFromHtml == 0) {
            params.put("categories", productCategoryDataStore.getAll());
            params.put("products", productDataStore.getAll());
        } else {
            if (stringValueName.equals("category")) {
                params.put("selected_category", productCategoryDataStore.find(valueFromHtml));
                params.put("products", productDataStore.getBy(productCategoryDataStore.find(valueFromHtml)));
            } else {
                params.put("selected_category", supplierDataStore.find(valueFromHtml));
                params.put("products", productDataStore.getBy(supplierDataStore.find(valueFromHtml)));
            }
        }

        params.put("allQuantity", getOrderBySession(req).getAllQuantity());
        return new ModelAndView(params, "product/index");
    }


    /**
     * Get the product id from client. Add products to the user's Order object (cart).
     * Add the Order object to the session and add new LineItem to the Order
     *
     * @param req Request from client
     * @param res Response to client
     * @return null
     */
    public static String addProducts(Request req, Response res) {
        Integer id = Integer.parseInt(req.params(":id"));
        Product result = productDataStore.find(id);
        getOrderBySession(req).addItem(new LineItem(result));
        res.redirect("/");
        return null;
    }

    /**
     * This method responsible for rendering the review page.
     * Get the Order from session, then put the into params HashMap.
     * (If the cart is empty, the review page will be unavailable.)
     * After that send it to the client.
     *
     * @param req Request from client
     * @param res Response to client
     * @return the review page template
     */
    public static ModelAndView renderReview(Request req, Response res) {
        Order orderDataStore = getOrderBySession(req);
        if (orderDataStore.getList().size() == 0) {
            res.redirect("/");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("order", orderDataStore.getList());
        params.put("price", orderDataStore.getAllPrice());
        return new ModelAndView(params, "product/review");
    }

    /**
     * When the user editing an item in cart (on review page), then this method responsible for modifying.
     * Create an ArrayList, then get and process the data from the client and add to it. Save the edited Order
     * data and save in session. Catch the IllegalArgumentException, what thrown, when the client send not valid data.
     *
     * @param req Request from client
     * @param res Response to client
     * @return null
     * @see Order#editItem(List) method, if you need more info about not valid data.
     */
    public static String editProducts(Request req, Response res) {
        List<String> editAttr = new ArrayList<>(Arrays.asList(req.params(":lineItem").split("_")));
        try {
            req.session().attribute("order", getOrderBySession(req).editItem(editAttr));
        }
        //TODO: create a unique exception for illegal editAttr arguments (?)
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        res.redirect("/review");
        return null;
    }

    /**
     * Check the users session. If new, then create a new Order for them.
     * Anyway get the object, what store in session.
     *
     * @param req Request from client
     * @return user Order form session
     */
    private static Order getOrderBySession(Request req) {
        if (req.session().isNew()) {
            Order orderDataStore = new Order();
            req.session().attribute("order", orderDataStore);
        }
        return req.session().attribute("order");
    }
}
