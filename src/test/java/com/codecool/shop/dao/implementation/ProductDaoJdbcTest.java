package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.TestDatabaseService;
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

    private static Supplier testSupplier = new Supplier("testSupplier", "for testing");
    private static ProductCategory testCategory = new ProductCategory("testCategory", "test department", "for testing");

    @BeforeClass
    public static void setUpBefore() {
        productCategoryDataStoreTest.add(testCategory);
        supplierDataStoreTest.add(testSupplier);
    }

    @Before
    public void setUp() {
        Product testProduct = new Product("Testproduct", 50f, "USD", "for testing", productCategoryDataStoreTest.find(1), supplierDataStoreTest.find(1));
        productDataStoreTest.add(testProduct);
    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(productDataStoreTest instanceof ProductDaoJdbc);
    }


    @Test
    public void add() throws Exception {
        Product testProduct = new Product("Testproduct", 50f, "USD", "for testing", productCategoryDataStoreTest.find(1), supplierDataStoreTest.find(1));
        int indexBefore = productDataStoreTest.getAll().size();
        productDataStoreTest.add(testProduct);
        int indexAfter = productDataStoreTest.getAll().size();

        assertTrue(indexAfter - indexBefore == 1);
    }


    @Test
    public void find() throws Exception {
        Product expectedProduct = productDataStoreTest.find(1);
        int expectedId = expectedProduct.getId();
        assertTrue(expectedId == 1);
    }


    @Test
    public void remove() throws Exception {
        int indexBefore = productDataStoreTest.getAll().size();
        productDataStoreTest.remove(indexBefore);
        int indexAfter = productDataStoreTest.getAll().size();

        assertTrue(indexBefore - indexAfter == 1);
    }


    @Test
    public void getAll() throws Exception {
        int productsInTestDb = productDataStoreTest.getAll().size();
        List<Product> products = productDataStoreTest.getAll();
        int productsSize = products.size();
        assertEquals(productsSize, productsInTestDb);
    }


    @Test
    public void getBy() throws Exception {
        List<Product> bySupplierProducts = productDataStoreTest.getBy(supplierDataStoreTest.find(1));
        Product testProduct = bySupplierProducts.get(0);
        testSupplier.setId(1);
        assertEquals(testProduct.getSupplier(), testSupplier);
    }


    @Test
    public void getBy1() throws Exception {
        List<Product> byProductCategoryProducts = productDataStoreTest.getBy(productCategoryDataStoreTest.find(1));
        Product testProduct = byProductCategoryProducts.get(0);
        testCategory.setId(1);
        assertEquals(testProduct.getProductCategory(), testCategory);
    }

}