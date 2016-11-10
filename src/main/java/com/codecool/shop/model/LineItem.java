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
}
