package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by adambodnar on 2016. 11. 23..
 */
public class ProductDaoJdbcTest {

    private static ProductDao productDataStoreTest = ProductDaoJdbc.getInstance();
    private static SupplierDao supplierDataStoreTest = SupplierDaoJdbc.getInstance();
    private static ProductCategoryDao productCategoryDataStoreTest = ProductCategoryDaoJdbc.getInstance();

    static int lastSupplierIndex = supplierDataStoreTest.getAll().size();
    static int lastProductCategoryIndex = productCategoryDataStoreTest.getAll().size();


    Product testProduct = new Product("Testproduct", 50f, "USD", "for testing", productCategoryDataStoreTest.find(lastProductCategoryIndex), supplierDataStoreTest.find(lastSupplierIndex));
    private static Supplier testSupplier = new Supplier("testSupplier", "for testing");
    private static ProductCategory testCategory = new ProductCategory("testCategory", "test department", "for testing");



    @BeforeClass
    public static void setUpBefore() {
        productCategoryDataStoreTest.add(testCategory);
        supplierDataStoreTest.add(testSupplier);
    }



    @After
    public void after() {

    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(productDataStoreTest instanceof ProductDaoJdbc);
    }


    @Test
    public void add() throws Exception {
        productDataStoreTest.add(testProduct);
        int lastIndex = productDataStoreTest.getAll().size();

        System.out.println(lastIndex);
        Product expectedProduct = productDataStoreTest.find(lastIndex);
        testProduct.setId(lastIndex);
        productDataStoreTest.remove(lastIndex);
        assertEquals(expectedProduct, testProduct);

    }

    @Ignore
    @Test
    public void find() throws Exception {

    }

    @Ignore
    @Test
    public void remove() throws Exception {

    }

    @Ignore
    @Test
    public void getAll() throws Exception {

    }

    @Ignore
    @Test
    public void getBy() throws Exception {

    }

    @Ignore
    @Test
    public void getBy1() throws Exception {

    }

}