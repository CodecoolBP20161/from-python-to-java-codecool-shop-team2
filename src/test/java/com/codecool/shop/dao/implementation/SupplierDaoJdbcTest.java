package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.DatabaseService;
import com.codecool.shop.service.TestDatabaseService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.Statement;

import static org.junit.Assert.*;

public class SupplierDaoJdbcTest {

    TestDatabaseService databaseService = new TestDatabaseService();
    SupplierDao testSupplierStore = SupplierDaoJdbc.getInstance();
    Supplier apple = new Supplier("mac", "mac");

    @BeforeClass
    public void setUp() {
        testSupplierStore.remove(1);
    }


    @Test
    public void getInstance() throws Exception {
        Supplier apple = new Supplier("Mac", "Digital content and services");
        assertTrue(testSupplierStore instanceof SupplierDaoJdbc);

    }

    @Test
    public void add() throws Exception {
        testSupplierStore.add(apple);
        assertEquals(apple, testSupplierStore.find(1));


    }

    @Test
    public void find() throws Exception {
        testSupplierStore.add(apple);
        assertEquals(apple, testSupplierStore.find(1));
    }

    @Test
    public void remove() throws Exception {
        testSupplierStore.remove(1);
        assertEquals(null, testSupplierStore.find(1));
    }

    @Test
    public void getAll() throws Exception {
        testSupplierStore.add(apple);
        assertEquals(1, testSupplierStore.getAll().size());

    }

}