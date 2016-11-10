package com.codecool.shop.model;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private  List<LineItem> DATA = new ArrayList<>();


    public void addItem(LineItem newItem) {
        for (LineItem lineItem:DATA){
            if (lineItem.getProductId().equals(newItem.getProductId())){
                int q = lineItem.getQuantity();
                lineItem.setQuantity(++q);
                return;
            }
        }
        DATA.add(newItem);
    }

    public Order editItem(List editAttr) {

        Integer newProdId = Integer.parseInt(editAttr.get(0).toString());
        Integer q = Integer.parseInt(editAttr.get(1).toString());
        Character setVal = editAttr.get(2).toString().charAt(0);

        for (LineItem item : this.getList()) {
            if (item.getProductId().equals(newProdId)) {
                if (setVal == '+') {
                    item.setQuantity(++q);
                } else {
                    item.setQuantity(--q);
                }
//                System.out.println(item.getQuantity());
            }
        }
        return this;
    }


    public List<LineItem> getList() {
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
