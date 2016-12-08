package com.codecool.shop.dao;

import com.codecool.shop.model.Customer;
import java.util.List;

public interface CustomerDao {
    void add(Customer customer);

    Customer findBy(int id);
    Customer findBy(String email);
    void remove(int id);


    List<Customer> getAll();

    String verifyCustomer(Customer newCustomer);
    void updateWithShipping(String email, String country, String city, String zipcode, String address);
    void updateWithBilling(String email, String country, String city, String zipcode, String address);
}
