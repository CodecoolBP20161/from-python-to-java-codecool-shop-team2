package com.codecool.shop.model;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static List<LineItem> DATA = new ArrayList<>();
    private static Order instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private Order() {
    }

    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }


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
            System.out.println("lineItem quantity: "+lineItem.getQuantity());
            res+=lineItem.getQuantity();
        }
        return res;
    }

    public float getAllPrice() {
        float res = 0;
        for (LineItem lineItem:DATA){
            System.out.println("lineItem Subprice: "+lineItem.getSubTotalPrice());
            res+=lineItem.getSubTotalPrice();
        }
        return res;
    }
}
