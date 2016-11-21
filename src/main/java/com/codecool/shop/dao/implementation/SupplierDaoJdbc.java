package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DatabaseController;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.List;

/**
 * Created by bt on 2016.11.21..
 */
public class SupplierDaoJdbc implements SupplierDao {
    DatabaseController databaseController = new DatabaseController();


    @Override
    public void add(Supplier supplier) {
        try {

            PreparedStatement stmt;
            stmt = databaseController.getConnection().prepareStatement("INSERT INTO codecoolshop (id, name, description) VALUES (?, ?, ?)");
            stmt.setString(1, String.valueOf(supplier.getId()));
            stmt.setString(2, supplier.getName());
            stmt.setString(3, supplier.getDescription());
            stmt.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Supplier find(int id) {

        String query = "SELECT * FROM codecoolshop WHERE id ='" + id + "';";

        try {
            Connection connection = databaseController.getConnection();
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
