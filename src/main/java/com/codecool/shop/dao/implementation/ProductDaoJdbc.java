package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DatabaseController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.List;


public class ProductDaoJdbc implements ProductDao {

    DatabaseController databaseController = new DatabaseController();

    @Override
    public void add(Product product) {
        try {
            PreparedStatement stmt;
            stmt = databaseController.getConnection().prepareStatement
                    ("INSERT INTO product" +
                            "(id," +
                            "name," +
                            "description," +
                            "defaultprice," +
                            "currencystring," +
                            "productcategory," +
                            "supplier) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, String.valueOf(product.getId()));
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, String.valueOf(product.getDefaultPrice()));
            stmt.setString(5, String.valueOf(product.getDefaultCurrency()));
            stmt.setString(6, String.valueOf(product.getProductCategory().getId()));
            stmt.setString(7, String.valueOf(product.getSupplier().getId()));
            stmt.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {

        String query = "SELECT * FROM product WHERE id ='" + id + "';";
        databaseController.executeQuery(query);
        return null;
    }


    @Override
    public void remove(int id) {

        String query = "DELETE * FROM product WHERE id ='" + id + "';";
        databaseController.executeQuery(query);
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product;";
        databaseController.executeQuery(query);
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM product WHERE supplier ='" + supplier.getId() + "';";
        databaseController.executeQuery(query);
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM product WHERE category ='" + productCategory.getId() + "';";
        databaseController.executeQuery(query);
        return null;
    }

}
