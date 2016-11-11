package com.codecool.shop.model;

public class LineItem {
    private Product product;
    private Integer productId;
    private String productName;
    private Integer quantity;
    private float subTotalPrice;

    public LineItem (Product product) {
        // this necessary for easier object setting
        this.product = product;
        this.setProductId();
        this.setProductName();
        // default value
        this.setQuantity(1);
    }

    public Integer getProductId() {return productId;}

    private void setProductId() {
        this.productId = this.product.id;
    }

    // this used by review.html
    public String getProductName() {
        return productName;
    }

    private void setProductName() {
        this.productName = this.product.getName();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {this.quantity = quantity;this.setSubTotalPrice();}

    public float getSubTotalPrice() {
        return subTotalPrice;
    }

    private void setSubTotalPrice() {
        this.subTotalPrice = this.quantity*this.product.getDefaultPrice();
    }
}
