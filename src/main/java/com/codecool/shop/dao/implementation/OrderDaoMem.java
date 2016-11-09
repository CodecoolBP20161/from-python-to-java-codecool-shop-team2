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
    public void addItem(LineItem item) {
        for (LineItem lineItem:DATA){
            if (lineItem.getProductName().equals(item.getProductName())){
                int q = lineItem.getQuantity();
                lineItem.setQuantity(q++);
                return;
            }
        }
        DATA.add(item);
    }

    @Override
    public List getList() {
        return DATA;
    }

    @Override
    public Integer getAllQuantity () {
        Integer res = 0;
        for (LineItem lineItem:DATA){
            res+=lineItem.getQuantity();
        }
        return res;
    }

    @Override
    public Integer getAllPrice() {
        return null;
    }
}
