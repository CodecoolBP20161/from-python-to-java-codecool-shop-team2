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
        Supplier magicBeans = new Supplier("magicBeans", "beans supplier");
        supplierDataStore.add(magicBeans);
        Supplier biobeans = new Supplier("biobeans", "beans supplier");
        supplierDataStore.add(biobeans);
        Supplier friendlyST = new Supplier("friendlyST", "beans supplier");
        supplierDataStore.add(friendlyST);

        //setting up the product categories
        ProductCategory magic = new ProductCategory("Magic", "Beans", "description");
        productCategoryDataStore.add(magic);
        ProductCategory real = new ProductCategory("Real", "Beans", "description");
        productCategoryDataStore.add(real);

        //setting up products and add their own store
        productDataStore.add(new Product("Mameshiba", 49.9f, "USD", "Dog-like faces and tell trivia. Their name derives from a series of puns.", productCategoryDataStore.find(1), supplierDataStore.find(1)));
        productDataStore.add(new Product("Sogood Coffee", 479f, "USD", "Born and brewed in Southern California since 1963, the taste and aroma of the world's best coffees.", productCategoryDataStore.find(2), supplierDataStore.find(2)));
        productDataStore.add(new Product("Bertie Botts", 89f, "USD", "They're back! It's the return of the infamous Bertie Bott's Every Flavour Beans! ", productCategoryDataStore.find(1), supplierDataStore.find(1)));
        productDataStore.add(new Product("Darkest Energy", 269.99f, "USD", "This beans grown in Mordor depths. Product of caring Orcs and Sauron himself.", productCategoryDataStore.find(2), supplierDataStore.find(3)));
        productDataStore.add(new Product("Cranberry", 199.99f, "USD", "Cranberries are usually processed into juice, sauce, jam and sweetened dried cranberries.", productCategoryDataStore.find(2), supplierDataStore.find(2)));
        productDataStore.add(new Product("America Nowadays", 179f, "USD", "No racism by the Trump industry. No offance BUT....... Add it to cart right now.", productCategoryDataStore.find(2), supplierDataStore.find(3)));
        productDataStore.add(new Product("Judged based on cover", 1299.99f, "USD", "The exception proves the rule. The exception proves the rule!", productCategoryDataStore.find(2), supplierDataStore.find(3)));
    }

}
