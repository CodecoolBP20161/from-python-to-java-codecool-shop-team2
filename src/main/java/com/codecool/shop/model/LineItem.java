package com.codecool.shop.model;

public class LineItem {
    private Product product;
    private String productName;
    private Integer quantity;
    private float subTotalPrice;

    public LineItem (Product product) {
        this.product = product;
        this.setProductName();
        this.setQuantity(1);
    }

    public String getProductName() {
        return productName;
    }

    private void setProductName() {
        this.productName = this.product.getName();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.setSubTotalPrice();
    }

    public float getSubTotalPrice() {
        return subTotalPrice;
    }

    private void setSubTotalPrice() {
        this.subTotalPrice = this.quantity*this.product.getDefaultPrice();
    }




    public static void main(String[] args) {
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        Product product = new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon);

        LineItem item = new LineItem(product);
//        System.out.println(item.getProductName());
//        System.out.println(item.getQuantity());
//        System.out.println(item.getSubTotalPrice());
        item.setQuantity(100);
//        System.out.println(item.getQuantity());
        System.out.println(item.getSubTotalPrice());
    }

}
