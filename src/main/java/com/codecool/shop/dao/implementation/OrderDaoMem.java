package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao{

    private static List<LineItem> DATA = new ArrayList<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }


    @Override
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

    @Override
    public List getList() {
        return DATA;
    }

    @Override
    public Integer getAllQuantity () {
        Integer res = 0;
        for (LineItem lineItem:DATA){
            System.out.println("lineItem quantity: "+lineItem.getQuantity());
            res+=lineItem.getQuantity();
        }
        return res;
    }

    @Override
    public float getAllPrice() {
        float res = 0;
        for (LineItem lineItem:DATA){
            System.out.println("lineItem Subprice: "+lineItem.getSubTotalPrice());
            res+=lineItem.getSubTotalPrice();
        }
        return res;
    }
}
