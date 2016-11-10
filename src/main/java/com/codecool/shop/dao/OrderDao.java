package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import java.util.List;

public interface OrderDao {
    void addItem(LineItem item);
    List getList();
    Integer getAllQuantity();
    float getAllPrice();
}
