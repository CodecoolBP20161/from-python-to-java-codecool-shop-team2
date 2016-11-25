package com.codecool.shop.dao.implementation;

import com.codecool.shop.service.DatabaseService;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    DatabaseService databaseService = new DatabaseService();

    private static SupplierDaoJdbc instance = null;

    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(Supplier supplier) {
        try {

            PreparedStatement stmt;
            stmt = databaseService.getConnection().prepareStatement("INSERT INTO supplier (name, description) VALUES (?, ?)");
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getDescription());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {

        String query = "SELECT * FROM supplier WHERE id ='" + id + "';";


        try (Connection connection = databaseService.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                supplier.setId(resultSet.getInt(1));
                return supplier;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void remove(int id) {

        String query = "DELETE FROM supplier WHERE id ='" + id + "';";
        databaseService.executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {

        String query = "SELECT * FROM supplier;";

        List<Supplier> resultList = new ArrayList<>();

        try (Connection connection = databaseService.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Supplier supplier = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
                supplier.setId(resultSet.getInt(1));
                resultList.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

}
