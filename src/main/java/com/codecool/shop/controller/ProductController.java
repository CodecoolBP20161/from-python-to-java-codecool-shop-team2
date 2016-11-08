package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();




        Integer htmlId = 0;


        Map params = new HashMap<>();

        while (true) {

            //String valueFromHtml;
            //Integer htmlId;
            //valueFromHtml = req.queryParams("id");
            //System.out.println(valueFromHtml);

            //int asd = valueFromHtml.intValue();




            if (htmlId == 0) {

                params.put("categories", productCategoryDataStore.getAll());
                params.put("products", productDataStore.getAll());

                return new ModelAndView(params, "product/index");
            } else {
                params.put("selected_category", productCategoryDataStore.find(htmlId));
                params.put("products", productDataStore.getBy(productCategoryDataStore.find(htmlId)));

                return new ModelAndView(params, "product/index");

            }


        }




        //params.put("selected_category", productCategoryDataStore.find(2));
        //params.put("products", productDataStore.getBy(productCategoryDataStore.find(2)));


    }
}
