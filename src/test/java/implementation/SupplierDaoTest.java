package implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by bt on 2016.11.23..
 */
public class SupplierDaoTest {

    @RunWith(Parameterized.class)
    public class InterfaceTesting {
        public SupplierDao supplierDao;

        public InterfaceTesting(SupplierDao supplierDao) {
            this.supplierDao = supplierDao;
        }

        @Parameterized.Parameters
        public Collection<Object[]> instancesToTest() {
            return Arrays.asList(
                    new Object[]{SupplierDaoMem.getInstance()},
                    new Object[]{SupplierDaoJdbc.getInstance()}
            );
        }


        @Test
        public void add() throws Exception {

        }

        @Test
        public void find() throws Exception {

        }

        @Test
        public void remove() throws Exception {

        }

        @Test
        public void getAll() throws Exception {

        }
    }

}