package com.intsab.intsabshop.data.Models;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */
public class CartItem extends ProductItem {
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
