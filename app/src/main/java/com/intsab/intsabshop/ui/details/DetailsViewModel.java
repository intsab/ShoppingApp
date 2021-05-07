package com.intsab.intsabshop.ui.details;

import androidx.lifecycle.LiveData;

import com.intsab.intsabshop.BaseViewModel;
import com.intsab.intsabshop.data.Models.ProductItem;

public class DetailsViewModel extends BaseViewModel {
    public LiveData<ProductItem> getProductDetails(int id) {
        return repository.getProductDetails(id);
    }
}