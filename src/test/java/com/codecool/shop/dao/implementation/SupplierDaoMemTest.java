package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SupplierDaoMemTest {

    SupplierDao supplierStoreTest = SupplierDaoMem.getInstance();
    Supplier supplierTest = new Supplier("nameTest", "descriptionTest");


    @Before
    public void setUp() {
        supplierStoreTest.remove(1);
    }

    @Test
    public void getInstanceTest() throws Exception {
        assertTrue(supplierStoreTest instanceof SupplierDaoMem);
    }


    @Test
    public void addTesting() throws Exception {
        supplierStoreTest.add(supplierTest);
        assertEquals(supplierTest, supplierStoreTest.find(1));
    }


    @Test
    public void findTesting() throws Exception {
        supplierStoreTest.add(supplierTest);
        assertEquals(supplierTest, supplierStoreTest.find(1));

    }

    @Test
    public void removeTesting() throws Exception {
        supplierStoreTest.remove(1);
        assertEquals(null, supplierStoreTest.find(1));

    }

    @Test
    public void getAll() throws Exception {
        supplierStoreTest.add(supplierTest);
        assertEquals(1, supplierStoreTest.getAll().size());

    }

}