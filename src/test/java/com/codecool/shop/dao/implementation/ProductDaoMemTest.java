package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoMemTest {


    ProductDao productDataStoreTest = ProductDaoMem.getInstance();
    ProductCategory testCategory = new ProductCategory("testCategory", "test department", "for testing");
    Supplier testSupplier = new Supplier("testSupplier", "for testing");
    Product testProduct = new Product("Testproduct", 50f, "USD", "for testing", testCategory, testSupplier);



    @Before
    public void setUp() {
        productDataStoreTest.remove(1);

    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(productDataStoreTest instanceof ProductDaoMem);
    }

    @Test
    public void add() throws Exception {
        productDataStoreTest.add(testProduct);
        Product expectedProduct = productDataStoreTest.find(1);
        assertEquals(expectedProduct, testProduct);
    }

    @Test
    public void find() throws Exception {
        productDataStoreTest.add(testProduct);
        Product expectedProduct = productDataStoreTest.find(1);
        assertEquals(expectedProduct, testProduct);
    }

    @Test
    public void remove() throws Exception {
        productDataStoreTest.add(testProduct);
        productDataStoreTest.remove(1);
        assertNull(productDataStoreTest.find(1));
    }

    @Test
    public void getAll() throws Exception {
        productDataStoreTest.add(testProduct);
        int expectedInt = 1;
        int getAllInt = productDataStoreTest.getAll().size();

        assertEquals(expectedInt, getAllInt);
    }

    @Test
    public void getBy() throws Exception {
        productDataStoreTest.add(testProduct);

        List<Product> expected = new ArrayList<>();
        expected.add(testProduct);
        assertEquals(expected, productDataStoreTest.getBy(testCategory));

    }

    @Test
    public void getBy1() throws Exception {
        productDataStoreTest.add(testProduct);

        List<Product> expected = new ArrayList<>();
        expected.add(testProduct);
        assertEquals(expected, productDataStoreTest.getBy(testSupplier));

    }

}