package com.intsab.intsabshop.ui.cart;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.intsab.intsabshop.BaseViewModel;
import com.intsab.intsabshop.data.Models.CartItem;

import java.util.List;

public class CartViewModel extends BaseViewModel {

    public LiveData<List<CartItem>> getAllCartItems() {
        return localRepository.getAllCartItems();
    }

    void delete(int _id) {
        localRepository.delete(_id);
    }

    void deleteAll() {
        localRepository.deleteAll();
    }

    void update(int _id, int qty) {
        localRepository.update(_id, qty);
    }
}