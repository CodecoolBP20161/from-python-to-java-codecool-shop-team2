package com.codecool.shop.model;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private  List<LineItem> DATA = new ArrayList<>();

    public void addItem(LineItem newItem) {
        for (LineItem lineItem:DATA){
            if (lineItem.getProductName().equals(newItem.getProductName())){
                int q = lineItem.getQuantity();
                lineItem.setQuantity(++q);
                return;
            }
        }
        DATA.add(newItem);
    }

    public List getList() {
        return DATA;
    }

    public Integer getAllQuantity () {
        Integer res = 0;
        for (LineItem lineItem:DATA){
            res+=lineItem.getQuantity();
        }
        return res;
    }

    public float getAllPrice() {
        float res = 0;
        for (LineItem lineItem:DATA){
            res+=lineItem.getSubTotalPrice();
        }
        return res;
    }
}
