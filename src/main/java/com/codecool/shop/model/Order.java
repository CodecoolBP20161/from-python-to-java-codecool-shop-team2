package com.codecool.shop.model;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private  List<LineItem> DATA = new ArrayList<>();

    // add just the new LineItem
    // if item is exist, then increase the quantity
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

    // edit (or remove) an existing LineItem in Order's list, then give back the new Order object
    public Order editItem(List editAttr) throws IllegalArgumentException {

        // convert the list elements to the necessary type
        Integer newProdId = Integer.parseInt(editAttr.get(0).toString());
        Integer quantity = Integer.parseInt(editAttr.get(1).toString());
        Character setValue = editAttr.get(2).toString().charAt(0);

        // get the exist Order list, then find the given LineItem
        for (LineItem item : DATA) {
            if (item.getProductId().equals(newProdId)) {
                // modify the item based on setValue
                if (setValue == '+') {
                    item.setQuantity(++quantity);
                }
                else if (setValue == '-') {
                    item.setQuantity(--quantity);
                }
                else {
                    throw new IllegalArgumentException ("Value should be + or - at 3rd place of editAttr ArrayList");
                }
                // if the item's quantity 0, then remove from Order's list (after get the item's index in ArrayList
                if (item.getQuantity()<1) {
                    DATA.remove(DATA.indexOf(item));
                    break;
                }
            }
        }
        return this;
    }

    public List<LineItem> getList() {
        return DATA;
    }

    // return all item quantity in the Order's list
    public Integer getAllQuantity () {
        Integer res = 0;
        for (LineItem lineItem:DATA){
            res+=lineItem.getQuantity();
        }
        return res;
    }

    // return all item price in the Order's list
    public float getAllPrice() {
        float res = 0;
        for (LineItem lineItem:DATA){
            res+=lineItem.getSubTotalPrice();
        }
        return res;
    }
}
