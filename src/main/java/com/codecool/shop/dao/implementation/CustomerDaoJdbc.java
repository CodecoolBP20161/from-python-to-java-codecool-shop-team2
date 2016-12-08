package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.model.Customer;
import com.codecool.shop.service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoJdbc implements CustomerDao {

    private static CustomerDaoJdbc instance = null;
    private DatabaseService databaseService = new DatabaseService();

    private CustomerDaoJdbc() {}

    public static CustomerDaoJdbc getInstance() {
        if (instance==null) {
            instance = new CustomerDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Customer customer) {
        try {
            PreparedStatement stmt;
            stmt = databaseService.getConnection().prepareStatement(
                    "INSERT INTO customer (name, email, hashedPW) VALUES (?, ?, ?)");
            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getHashedPW());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Customer find(String query){
        try (Connection connection = databaseService.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("hashedPW"));
                customer.setCustomerId(resultSet.getInt(1));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Customer findBy(int id) {
        String query = "SELECT * FROM customer WHERE id ='" + id + "';";
        return find(query);
    }


    @Override
    public Customer findBy(String email) {
        String query = "SELECT * FROM customer WHERE email ='" + email + "';";
        return find(query);
    }



    @Override
    public void remove(int id) {
        String query = "DELETE FROM customer WHERE id ='" + id + "';";
        databaseService.executeQuery(query);
    }

    @Override
    public List<Customer> getAll() {
        String query = "SELECT * FROM customer;";

        List<Customer> resultList = new ArrayList<>();

        try (Connection connection = databaseService.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("hashedPW"));
                customer.setCustomerId(resultSet.getInt(1));
                resultList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public String verifyCustomer(Customer newCustomer){
        for (Customer ctr : getAll()){
            if (ctr.getCustomerName().equals(newCustomer.getCustomerName())) {
                return "Username already exist!";
            }
            if (ctr.getEmail().equals(newCustomer.getEmail())) {
                return "Email already used!";
            }
        }
        return "OK";
    }

    @Override
    public void updateWith(
            String columnName, String email, String country, String city, String zipcode, String address){
        String query = "UPDATE customer " +
                "SET "+columnName+"country = '" + country + "', " +
                columnName+"city = '" + city + "', " +
                columnName+"zipcode = '" + zipcode + "', " +
                columnName+"address = '" + address + "' WHERE email = '" + email + "';";
        databaseService.executeQuery(query);
    }
}
