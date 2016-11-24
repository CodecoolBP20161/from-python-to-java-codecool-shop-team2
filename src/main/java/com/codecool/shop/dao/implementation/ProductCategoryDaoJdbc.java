package com.codecool.shop.dao.implementation;

import com.codecool.shop.service.DatabaseService;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao{
    DatabaseService databaseService = new DatabaseService();

    private static ProductCategoryDaoJdbc instance = null;

    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        try {
            PreparedStatement stmt;
            stmt = databaseService.getConnection().prepareStatement(
                    "INSERT INTO productcategory (name, description, department) VALUES (?, ?, ?)");
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setString(3, category.getDepartment());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM productcategory WHERE id ='" + id + "';";

        try (Connection connection = databaseService.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                ProductCategory category = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department"));
                category.setId(resultSet.getInt(1));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM productcategory WHERE id ='" + id + "';";
        databaseService.executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM productcategory;";

        List<ProductCategory> resultList = new ArrayList<>();

        try (Connection connection = databaseService.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                ProductCategory category = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department"));
                category.setId(resultSet.getInt(1));
                resultList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public static void main(String[] args) {
        ProductCategoryDaoJdbc test = new ProductCategoryDaoJdbc();

        System.out.println(test.find(1));
    }
}
