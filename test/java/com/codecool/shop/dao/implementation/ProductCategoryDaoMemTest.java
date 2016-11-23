package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ProductCategoryDaoMemTest {
    ProductCategoryDao testCategoryStore = ProductCategoryDaoMem.getInstance();
    ProductCategory testCategory = new ProductCategory("catName", "catDepartment", "catDescription");

    @Before
    public void seUp() {
        testCategoryStore.remove(1);
    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(testCategoryStore instanceof ProductCategoryDaoMem);
    }

    @Test
    public void add() throws Exception {
        testCategoryStore.add(testCategory);
        assertEquals(testCategory, testCategoryStore.find(1));

    }

    @Test
    public void find() throws Exception {
        testCategoryStore.add(testCategory);
        assertEquals(testCategory, testCategoryStore.find(1));
    }

    @Test
    public void remove() throws Exception {
        testCategoryStore.remove(1);
        assertEquals(null, testCategoryStore.find(1));
    }

    @Test
    public void getAll() throws Exception {
        testCategoryStore.add(testCategory);
        assertEquals(1, testCategoryStore.getAll().size());
    }

}