package com.codecool.shop.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Customer {
    private Integer customerId;
    private String customerName;
    private String email;
    private String hashedPW;
    private static HashMap DEFAULT_MAP = new HashMap();
    private static HashMap fillMap(HashMap map) {
        map.put("Country", null);
        map.put("City", null);
        map.put("Zipcode", null);
        map.put("Address", null);
        return map;
    }
    private HashMap billingAddress = fillMap(DEFAULT_MAP);
    private HashMap shippingAdress = fillMap(DEFAULT_MAP);

    public Customer(String customerName, String email, String hashedPassword) {
        this.customerName = customerName;
        this.email = email;
        this.hashedPW = hashedPassword;
    }

    // if user need own salty hashed password
    private Customer(String customerName, String email, String hashedPassword, String rawPW){
        this.customerName = customerName;
        this.email = email;
        this.hashedPW = setHashedPW(rawPW);
    }

    public void setCustomerId(Integer id) {this.customerId = id;}

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String setHashedPW(String password) {
        return this.hashedPW = this.StringConvertHash(this.getCustomerName() + password);
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

    public Boolean verifyPassword(String customerName, String password){
        return this.getHashedPW().equals(this.StringConvertHash(customerName + password));
    }

    public static Customer getFromClient(String clientInput){
        // if the input is right, the userData something like this:
        //[username=username, email=email%40address.com, password=password]
        List<String> userData = new ArrayList<>(Arrays.asList(clientInput.split("&")));
        for (int i=0;i<userData.size();i++) {
            String [] parts = userData.get(i).split("=");
            userData.set(i, parts[1]);
        }
        // need to change %40 onto @ in the email address, what get the client, then send welcome
        userData.set(1, userData.get(1).replaceAll("%40","@"));
        return new Customer(userData.get(0), userData.get(1), null, userData.get(2));
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
