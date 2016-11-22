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
        databaseController.executeQuery(query);
        return null;
    }

    @Override
    public void remove(int id) {

        String query = "DELETE FROM codecoolshop WHERE id ='" + id + "';";
        databaseController.executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {

        String query = "SELECT * FROM codecoolshop;";
        databaseController.executeQuery(query);
        return null;
    }
}
