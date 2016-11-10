package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
//        params.put("cart", req.session().attribute("cart"));
        int valueFromHtml;
        String stringValueFromHtml = req.params(":id");
        String stringValueName = req.params(":name");

        //create int from the string one way or another:
       if (stringValueFromHtml!= null){
           valueFromHtml = Integer.parseInt(stringValueFromHtml);
       }else{
           valueFromHtml = 0;
       }


        if (valueFromHtml == 0) {

            params.put("categories", productCategoryDataStore.getAll());
            params.put("products", productDataStore.getAll());

            return new ModelAndView(params, "product/index");

        } else {

            if (stringValueName.equals("category") ) {

                params.put("selected_category", productCategoryDataStore.find(valueFromHtml));
                params.put("products", productDataStore.getBy(productCategoryDataStore.find(valueFromHtml)));
                return new ModelAndView(params, "product/index");

            } else {
                params.put("selected_category", supplierDataStore.find(valueFromHtml));
                params.put("products", productDataStore.getBy(supplierDataStore.find(valueFromHtml)));

                return new ModelAndView(params, "product/index");
            }
        }

    }

    public static String getProducts(Request req, Response res) {
        Integer id = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product result = productDataStore.find(id);
        Order orderDataStore = Order.getInstance();
        orderDataStore.addItem(new LineItem(result));
        System.out.println(orderDataStore.getList());
        System.out.println("All quantity: "+orderDataStore.getAllQuantity());
        System.out.println("All price: "+orderDataStore.getAllPrice());

//        req.session().attribute("cart", 1);
        res.redirect("/");
        return null;

    }

    public static ModelAndView renderReview(Request req, Response res) {
        float price = Order.getAllPrice();
        Order orderDataStore = Order.getInstance();
        Map params = new HashMap<>();
        params.put("order", orderDataStore.getList());
        params.put("price", price);



        return new ModelAndView(params, "product/review");
    }
}
