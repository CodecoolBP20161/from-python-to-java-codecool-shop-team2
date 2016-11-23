package com.codecool.shop.model;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;

/**
 * Created by bt on 2016.11.22..
 */
public class ExampleData {

    // Define the example data
    public static void  main(String[] args) {

        ProductDao productDataStore = ProductDaoJdbc.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();


        //setting up the suppliers
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier("Samsung", "High-tech electronics manufacturing and digital media.");
        supplierDataStore.add(samsung);

        //setting up the product categories
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardwer", "Is a small, portable personal computer with a \"clamshell\" form factor.");
        productCategoryDataStore.add(laptop);

        //setting up products and add their own store
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", productCategoryDataStore.find(1), supplierDataStore.find(1)));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479f, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", productCategoryDataStore.find(1), supplierDataStore.find(2)));
        productDataStore.add(new Product("Amazon Fire HD 8", 89f, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", productCategoryDataStore.find(1), supplierDataStore.find(1)));
        productDataStore.add(new Product("Samsung Galaxy Tab A", 269.99f, "USD", "10-inch screen for easy viewing, and the octa-core processor keeps up with multitasking or streaming.", productCategoryDataStore.find(1), supplierDataStore.find(3)));
        productDataStore.add(new Product("Lenovo - 15.6 Laptop", 199.99f, "USD", "Take your work on the road with this small-format Lenovo IdeaPad netbook.", productCategoryDataStore.find(2), supplierDataStore.find(2)));
        productDataStore.add(new Product("Samsung Chromebook 3 ", 179f, "USD", "Unleash the power of modern computing with this Samsung Chromebook laptop.", productCategoryDataStore.find(2), supplierDataStore.find(3)));
        productDataStore.add(new Product("Samsung Notebook 9 pro", 1299.99f, "USD", "You can watch movies and TV shows in rich detail on the 15.6 touch screen, which displays a 4K UHD picture.", productCategoryDataStore.find(2), supplierDataStore.find(3)));
    }

}
