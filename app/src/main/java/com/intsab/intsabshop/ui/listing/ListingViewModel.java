package com.intsab.intsabshop.ui.listing;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.intsab.intsabshop.BaseViewModel;
import com.intsab.intsabshop.data.Models.ProductItem;

import java.util.List;

public class ListingViewModel extends BaseViewModel {

    public LiveData<List<ProductItem>> getProducts(int limit) {
        return repository.getProducts(limit);
    }

    public LiveData<List<ProductItem>> getProducts(String category) {
        return repository.getProductsByCategory(category);
    }
}