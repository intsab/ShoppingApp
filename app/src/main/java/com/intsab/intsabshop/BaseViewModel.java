package com.intsab.intsabshop;

import androidx.lifecycle.ViewModel;

import com.intsab.intsabshop.data.Models.CartItem;
import com.intsab.intsabshop.data.Repository.LocalRepository;
import com.intsab.intsabshop.data.Repository.ShoppingRepository;

import static com.intsab.intsabshop.Utils.AppUtils.application;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */
public class BaseViewModel extends ViewModel {
    public ShoppingRepository repository;
    public LocalRepository localRepository;

    public BaseViewModel() {
        repository = new ShoppingRepository();
        localRepository = new LocalRepository(application);

    }

   public void insertCartItem(CartItem item) {
        localRepository.insert(item);
    }
}
