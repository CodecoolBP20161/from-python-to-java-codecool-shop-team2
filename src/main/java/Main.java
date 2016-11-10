import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
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

        populateData();


        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/:name/:id", ProductController::renderProducts, new ThymeleafTemplateEngine());
        post("/add/:id", ProductController::getProducts);
        get("/review", ProductController::renderReview, new ThymeleafTemplateEngine());
    }
    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();


        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier("Samsung", "High-tech electronics manufacturing and digital media.");
        supplierDataStore.add(samsung);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardwer", "Is a small, portable personal computer with a \"clamshell\" form factor.");
        productCategoryDataStore.add(laptop);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Samsung Galaxy Tab A", 269.99f, "USD", "10-inch screen for easy viewing, and the octa-core processor keeps up with multitasking or streaming.", tablet, samsung));
        productDataStore.add(new Product("Lenovo - 15.6 Laptop", 199.99f, "USD", "Take your work on the road with this small-format Lenovo IdeaPad netbook.", laptop, lenovo));
        productDataStore.add(new Product("Samsung Chromebook 3 ", 179f, "USD", "Unleash the power of modern computing with this Samsung Chromebook laptop.", laptop, samsung));
        productDataStore.add(new Product("Samsung Notebook 9 pro", 1299.99f, "USD", "You can watch movies and TV shows in rich detail on the 15.6 touch screen, which displays a 4K UHD picture.", laptop, samsung));
    }


}
