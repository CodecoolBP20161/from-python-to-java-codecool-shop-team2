import com.codecool.shop.controller.CustomerController;
import com.codecool.shop.controller.ProductController;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);


        // here is the website's routes, what call the necessary method form the ProductController
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/registration", CustomerController::renderRegistration, new ThymeleafTemplateEngine());
        get("/:name/:id", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/review", ProductController::renderReview, new ThymeleafTemplateEngine());
        post("/add/:id", ProductController::addProducts);
        post("/edit/:lineItem", ProductController::editProducts);
        post("/registratio/:usrData", CustomerController::addCustomer);
    }

}
