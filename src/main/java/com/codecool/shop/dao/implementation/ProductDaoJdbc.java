package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DatabaseController;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoJdbc implements ProductDao {

    DatabaseController databaseController = new DatabaseController();

    private static ProductDaoJdbc instance = null;

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

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
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {

        String query = "SELECT * FROM product WHERE id ='" + id + "';";

        try (Connection connection = databaseController.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultprice"),
                        resultSet.getString("currencystring"),
                        resultSet.getString("description"),
                        resultSet.getObject("productcategory", ProductCategory.class),
                        resultSet.getObject("supplier", Supplier.class));
                product.setId(resultSet.getInt(1));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = databaseController.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultprice"),
                        resultSet.getString("currencystring"),
                        resultSet.getString("description"),
                        resultSet.getObject("productcategory", ProductCategory.class),
                        resultSet.getObject("supplier", Supplier.class));
                product.setId(resultSet.getInt(1));
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM product WHERE supplier ='" + supplier.getId() + "';";
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = databaseController.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultprice"),
                        resultSet.getString("currencystring"),
                        resultSet.getString("description"),
                        resultSet.getObject("productcategory", ProductCategory.class),
                        resultSet.getObject("supplier", Supplier.class));
                product.setId(resultSet.getInt(1));
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM product WHERE category ='" + productCategory.getId() + "';";
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = databaseController.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultprice"),
                        resultSet.getString("currencystring"),
                        resultSet.getString("description"),
                        resultSet.getObject("productcategory", ProductCategory.class),
                        resultSet.getObject("supplier", Supplier.class));
                product.setId(resultSet.getInt(1));
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

}
