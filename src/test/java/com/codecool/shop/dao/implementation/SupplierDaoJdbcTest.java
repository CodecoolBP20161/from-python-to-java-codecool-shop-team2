package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.TestDatabaseService;
import org.junit.*;

import java.sql.Statement;

import static org.junit.Assert.*;

public class SupplierDaoJdbcTest {

    SupplierDao testSupplierStore = SupplierDaoJdbc.getInstance();
    Supplier testSupplier = new Supplier("testSupplier", "supplier for testing");

    @Before
    public void setUp() {
        testSupplierStore.add(testSupplier);
    }


    @Test
    public void getInstance() throws Exception {

        assertTrue(testSupplierStore instanceof SupplierDaoJdbc);

    }

    @Test
    public void add() throws Exception {
        testSupplier.setId(2);
        assertEquals(testSupplier, testSupplierStore.find(2));
    }


    @Test
    public void find() throws Exception {
        testSupplier.setId(3);
        assertEquals(testSupplier, testSupplierStore.find(3));
    }


    @Test
    public void remove() throws Exception {
        int beforeSize = testSupplierStore.getAll().size();
        testSupplierStore.remove(testSupplierStore.find(beforeSize).getId()-1);
        int afterSize = testSupplierStore.getAll().size();
        assertTrue(beforeSize - afterSize == 1);
    }


    @Test
    public void getAll() throws Exception {
        testSupplierStore.add(testSupplier);
        int allSize = testSupplierStore.getAll().size();
        assertEquals(testSupplierStore.getAll().size(), allSize);

    }

}