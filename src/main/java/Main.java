import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.ExampleData;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // fill the site with example data
        ExampleData.populateData();

        // here is the website's routes, what call the necessary method form the ProductController
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/:name/:id", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/review", ProductController::renderReview, new ThymeleafTemplateEngine());
        post("/add/:id", ProductController::addProducts);
        post("/edit/:lineItem", ProductController::editProducts);
    }

}
