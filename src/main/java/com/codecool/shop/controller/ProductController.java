package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();

        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());

        //params.put("selected_category", productCategoryDataStore.find(2));
        //params.put("products", productDataStore.getBy(productCategoryDataStore.find(2)));

        return new ModelAndView(params, "product/index");
    }
}
