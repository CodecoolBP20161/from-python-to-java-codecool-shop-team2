package implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
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
        // Before every test case delete element in the static list (in ProductCategoryDaoMem)
        testCategoryStore.remove(1);
    }

    @Test
    public void getInstanceTest() throws Exception {
        assertTrue(testCategoryStore instanceof ProductCategoryDaoMem);
    }

    @Test
    public void addCategoryTest() throws Exception {
        testCategoryStore.add(testCategory);
        assertEquals(testCategory, testCategoryStore.find(1));
    }

    @Test
    public void findCategoryTest() throws Exception {
        testCategoryStore.add(testCategory);
        assertEquals(testCategory, testCategoryStore.find(1));
        assertEquals(null, testCategoryStore.find(0));
    }

    @Test
    public void removeCategoryTest() throws Exception {
        testCategoryStore.add(testCategory);
        testCategoryStore.remove(-1);
        assertEquals(testCategory, testCategoryStore.find(1));
        testCategoryStore.remove(1);
        assertEquals(null, testCategoryStore.find(1));
    }

    @Test
    public void getAllCategoryTest() throws Exception {
        testCategoryStore.add(testCategory);
        assertEquals(1, testCategoryStore.getAll().size());
    }
}