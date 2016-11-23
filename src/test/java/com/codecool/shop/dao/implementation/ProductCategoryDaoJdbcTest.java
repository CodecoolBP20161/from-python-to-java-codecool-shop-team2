package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductCategoryDaoJdbcTest {
    ProductCategoryDao testCategoryStore = ProductCategoryDaoJdbc.getInstance();
    ProductCategory testCategory = new ProductCategory("catName",  "catDescription", "catDepartment");

    @Before
    public void seUp() {
        // default case: the database set the id
        testCategory.setId(3);
        testCategoryStore.add(testCategory);
    }

    @Test
    public void getInstanceTest() throws Exception {
        assertTrue(testCategoryStore instanceof ProductCategoryDaoJdbc);
    }

//    @Test
//    public void addCategoryTest() throws Exception {
//        assertEquals(3, testCategoryStore.getAll().size());
//        testCategoryStore.add(testCategory);
//        assertEquals(4, testCategoryStore.getAll().size());
//        testCategoryStore.remove(4);
//    }

    @Test
    public void findCategoryTest() throws Exception {
//        assertEquals(null, testCategoryStore.find(0));
        assertEquals(testCategory, testCategoryStore.find(3));
    }


//    @Test
//    public void removeCategoryTest() throws Exception {
//        testCategoryStore.remove(-1);
//        assertEquals(testCategory, testCategoryStore.find(1));
//        testCategoryStore.remove(1);
//        assertEquals(null, testCategoryStore.find(1));
//    }
//
//    @Test
//    public void getAllCategoryTest() throws Exception {
//        testCategoryStore.add(testCategory);
//        assertEquals(1, testCategoryStore.getAll().size());
//    }

    @After
    public void TearDown(){
        testCategoryStore.remove(3);
//        testCategoryStore.remove(5);
    }
}
