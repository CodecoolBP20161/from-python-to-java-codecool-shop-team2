package implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by adambodnar on 2016. 11. 23..
 */

@RunWith(Parameterized.class)
public class ProductDaoTest {


    public ProductDao productDao;


    public ProductDaoTest(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void add() throws Exception {

    }

    @org.junit.Test
    public void find() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void getBy() throws Exception {

    }

    @Test
    public void getBy1() throws Exception {

    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[]{ProductDaoMem.getInstance()};
                new Object[]{ProductDaoJdbc.getInstance()}
        );
    }

}