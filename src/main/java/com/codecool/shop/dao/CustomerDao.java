package com.codecool.shop.dao;

import com.codecool.shop.model.Customer;
import java.util.List;

/**
 * This is an interface for Customer data access object.
 * For now, this work with memory or database storing.
 */
public interface CustomerDao {
    /**
     * This method add a Customer object to the adjusted store.
     *
     * @param customer not null
     */
    void add(Customer customer);

    /**
     * This method can find a Customer object by id in the adjusted store.
     *
     * @param id not null
     * @return Customer object
     */
    Customer findBy(int id);


    /**
     * This method can find a Customer object by email in the adjusted store.
     *
     * @param email not null
     * @return Customer object
     */
    Customer findBy(String email);

    /**
     * This method can delete a Customer object by id in the adjusted store.
     *
     * @param id not null
     */
    void remove(int id);


    /**
     * Get all customer from the adjusted store.
     *
     * @return a List
     */
    List<Customer> getAll();

    /**
     * Verifying a Customer, that is mean, no username or email address repeat.
     *
     * @param newCustomer object, not null
     * @return the verifying status, as String message
     */
    String verifyCustomer(Customer newCustomer);
    void updateWith(String columnName, String email, String country, String city, String zipcode, String address);
}
