package com.intsab.intsabshop;

import androidx.lifecycle.ViewModel;

import com.intsab.intsabshop.data.Repository.ShoppingRepository;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */
public class BaseViewModel extends ViewModel {
    public ShoppingRepository repository;

    public BaseViewModel() {
        repository = new ShoppingRepository();
    }
}
