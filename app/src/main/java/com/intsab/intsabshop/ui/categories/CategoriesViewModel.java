package com.intsab.intsabshop.ui.categories;

import androidx.lifecycle.LiveData;

import com.intsab.intsabshop.BaseViewModel;

import java.util.List;

public class CategoriesViewModel extends BaseViewModel {

    public LiveData<List<String>> getCategories() {
        return repository.getCategories();
    }
}