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
        customer.setHashedPW(customer.getHashedPW());
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

    @Override
    public Customer find(int id) {
        String query = "SELECT * FROM customer WHERE id ='" + id + "';";

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
                System.out.println("hashedPW from db: "+customer.getHashedPW());
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public static void main(String[] args) {
        CustomerDao customerStore = CustomerDaoJdbc.getInstance();
        Customer customer = new Customer("cName", "cEmail", "cPassword");
        customerStore.add(customer);
        System.out.println(customer.getHashedPW());

//        customer1.setHashedPW("cPassword");
//        System.out.println(customer1.getHashedPW());
//        Customer customer2 = customerStore.find(3);
//        System.out.println(customer.getCustomerName());
//        customerStore.add(customer);
//        System.out.println(customerStore.find(1));
//        System.out.println(customerStore.getAll().size());
//        System.out.println(customer1.verifyCustomer("cName", "cPassword"));
        System.out.println(customerStore.find(1).verifyCustomer("cName", "cPassword"));
//        System.out.println(customerStore.find(3).getHashedPW());
    }
}
