package com.intsab.intsabshop.ui.home;

import androidx.lifecycle.LiveData;

import com.intsab.intsabshop.BaseViewModel;
import com.intsab.intsabshop.data.Models.ProductItem;

import java.util.List;

public class HomeViewModel extends BaseViewModel {

    public LiveData<List<String>> getCategories() {
        return repository.getCategories();
    }

    public LiveData<List<ProductItem>> getProducts(int limit) {
        return repository.getProducts(limit);
    }
}