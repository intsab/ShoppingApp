package com.intsab.intsabshop.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.intsab.intsabshop.data.Models.CartItem;

import java.util.List;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */

@Dao
public interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CartItem word);

    @Query("DELETE FROM cart_table WHERE _id = :id")
    void deleteById(long id);

    @Query("DELETE FROM cart_table")
    void deleteAllCart();

    @Query("SELECT * FROM cart_table")
    LiveData<List<CartItem>> getAllCartItems();

    @Query("UPDATE cart_table SET quantity=:qty WHERE _id = :id")
    void update(int id, int qty);

}