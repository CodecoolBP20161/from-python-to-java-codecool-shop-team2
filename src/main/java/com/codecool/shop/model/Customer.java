package com.codecool.shop.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Customer {
    private Integer customerId;
    private String customerName;
    private String email;
    private String hashedPW;

    public Customer(String customerName, String email, String password) {
//        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
//        setHashedPW(password);
        this.hashedPW = password;
    }
    public void setCustomerId(Integer id) {this.customerId = id;}

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPW(String password) {
        this.hashedPW = this.StringConvertHash(this.getCustomerName() + this.hashedPW);
    }

    public Integer getCustomerId() {return customerId;}

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPW() {
        return hashedPW;
    }

    private String StringConvertHash(String inputString) {
        String hashCode = null;
        try {

            // create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            // update input string in message digest
            digest.update(inputString.getBytes(), 0, inputString.length());

            // converts message digest value in base 16 (hex)
            hashCode = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashCode;
    }

    public Boolean verifyCustomer(String customerName, String password){
        return this.getHashedPW().equals(this.StringConvertHash(customerName + password));
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", hashedPW='" + hashedPW + '\'' +
                '}';
    }
}
