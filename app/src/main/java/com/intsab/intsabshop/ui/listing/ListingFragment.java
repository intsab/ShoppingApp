package com.intsab.intsabshop.ui.listing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.intsab.intsabshop.R;
import com.intsab.intsabshop.data.Models.ProductItem;

import java.util.List;

public class ListingFragment extends Fragment {
    private String category = "";
    private ListingViewModel listingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listingViewModel = new ViewModelProvider(this).get(ListingViewModel.class);
        return inflater.inflate(R.layout.fragment_listing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (category.isEmpty()) {
            listingViewModel.getProducts(10).observe(getViewLifecycleOwner(), productItems -> {
                loadView(productItems);
            });
        } else {
            listingViewModel.getProducts(category).observe(getViewLifecycleOwner(), productItems -> {
                loadView(productItems);
            });
        }

    }

    public void loadView(List<ProductItem> items) {
    }
}