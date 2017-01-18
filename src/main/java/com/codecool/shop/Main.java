import com.codecool.shop.controller.APIController;
import com.codecool.shop.controller.CustomerController;
import com.codecool.shop.controller.ProductController;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

public class Main {

    /**
     * Default server settings:
     * - (Resources in public folder)
     * - WORKING ON: http://0.0.0.0:8888
     *
     * Routes Mapping:
     * - GET "/": Rendering all products in webshop
     * - GET "/:name/:id" Get the product name and id for rendering
     * - GET "/payment": Rendering the payment site
     * - GET "/registration": Rendering the registration interface
     * - GET "/review": Rendering the review page, where the user can see her/his cart
     * - GET "/summary": Rendering the summary page, where the user can confirm her/his order
     * - POST "/": Check the users status
     * - POST "/add/:id": Responsible for adding an item to cart
     * - POST "/edit/:lineItem": Responsible for editing an item quantity in cart
     * - POST "/registration": Send the registration data to the server
     * - POST "/registration/address": Send the shipping, billing data to the serve
     */
    public static void main(String[] args) {

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/:name/:id", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/payment", ProductController::renderPayment, new ThymeleafTemplateEngine());
        get("/registration", CustomerController::renderRegistration, new ThymeleafTemplateEngine());
        get("/review", ProductController::renderReview, new ThymeleafTemplateEngine());
        get("/summary", APIController::renderDeliverySummary, new ThymeleafTemplateEngine());
        post("/", CustomerController::checkCustomer);
        post("/add/:id", ProductController::addProducts);
        post("/edit/:lineItem", ProductController::editProducts);
        post("/registration", CustomerController::addCustomer);
        post("/registration/address", CustomerController::collectShippingBilling);
    }
}
