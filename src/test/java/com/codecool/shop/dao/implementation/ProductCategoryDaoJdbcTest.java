package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.TestDatabaseService;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductCategoryDaoJdbcTest {
    ProductCategoryDao testCategoryStore = ProductCategoryDaoJdbc.getInstance();
    ProductCategory testCategory = new ProductCategory("testCategory","test department",  "for testing");


    TestDatabaseService databaseService = new TestDatabaseService();

    @Before
    public void setUp(){
        testCategoryStore.add(testCategory);

    }

    @Test
    public void addCategoryTest() throws Exception {
        testCategoryStore.add(testCategory);
        assertEquals(1, testCategoryStore.find(1).getId());
    }


    @Test
    public void getInstanceTest() throws Exception {
        assertTrue(testCategoryStore instanceof ProductCategoryDaoJdbc);
    }

    @Test
    public void findCategoryTest() throws Exception {
        testCategoryStore.add(testCategory);

        testCategory.setId(2);
        assertEquals(testCategory, testCategoryStore.find(2));
    }

    @Test
    public void getAllCategoryTest() throws Exception {
        List<ProductCategory> tester = testCategoryStore.getAll();
        assertEquals(tester, testCategoryStore.getAll());
    }


    @Test
    public void removeCategoryTest() throws Exception {
        int before = testCategoryStore.getAll().size();
        testCategoryStore.remove(before);
        int after = testCategoryStore.getAll().size();

        assertTrue(before - after == 1);
    }

}
