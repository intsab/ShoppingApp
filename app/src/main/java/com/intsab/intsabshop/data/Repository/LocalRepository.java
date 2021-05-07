package com.intsab.intsabshop.data.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.intsab.intsabshop.data.Models.CartItem;
import com.intsab.intsabshop.data.database.CartDao;
import com.intsab.intsabshop.data.database.CartDatabase;

import java.util.List;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */

public class LocalRepository {

    private CartDao cartDao;
    private LiveData<List<CartItem>> mCartItemsLiveDara;

    public LocalRepository(Application application) {
        CartDatabase db = CartDatabase.getInstance(application);
        cartDao = db.cartDao();
        mCartItemsLiveDara = cartDao.getAllCartItems();
    }

    public LiveData<List<CartItem>> getAllCartItems() {
        return mCartItemsLiveDara;
    }

    public void insert(CartItem item) {
        CartDatabase.databaseWriteExecutor.execute(() -> {
            cartDao.insert(item);
        });
    }

    public void delete(int _id) {
        CartDatabase.databaseWriteExecutor.execute(() -> {
            cartDao.deleteById(_id);
        });
    }

    public void deleteAll() {
        CartDatabase.databaseWriteExecutor.execute(() -> {
            cartDao.deleteAllCart();
        });
    }

    public void update(int _id, int qty) {
        CartDatabase.databaseWriteExecutor.execute(() -> {
            cartDao.update(_id, qty);
        });
    }
}