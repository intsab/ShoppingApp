package com.intsab.intsabshop.data.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */

@Entity(tableName = "cart_table")
public class CartItem extends ProductItem {
    @PrimaryKey(autoGenerate = true)
    private int _id;

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

}
