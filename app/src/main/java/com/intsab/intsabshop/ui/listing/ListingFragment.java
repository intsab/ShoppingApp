package com.intsab.intsabshop.ui.listing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;
import com.intsab.intsabshop.data.Models.CartItem;
import com.intsab.intsabshop.data.Models.ProductItem;
import com.intsab.intsabshop.ui.home.adapters.ItemsListAdapter;

import java.util.List;

public class ListingFragment extends Fragment implements AppUtils.AddToCartClickListener, AppUtils.AdapterClickListener {
    private String category = "";
    private ListingViewModel listingViewModel;
    RecyclerView topProductsRecyclerView;
    ProgressBar progressBar;
    ItemsListAdapter itemsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listingViewModel = new ViewModelProvider(this).get(ListingViewModel.class);
        return inflater.inflate(R.layout.fragment_listing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        category = getArguments().getString(AppUtils.KEY_CATEGORY_ID);
        topProductsRecyclerView = view.findViewById(R.id.recyclerView);
        topProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar = (ProgressBar) view.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

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

    public void loadView(List<ProductItem> productItems) {
        progressBar.setVisibility(View.GONE);
        if (productItems != null) {
            itemsAdapter = new ItemsListAdapter(productItems, requireContext(), this, this);
            topProductsRecyclerView.setAdapter(itemsAdapter);
        } else {
            hideItemsView();
        }
    }

    private void hideItemsView() {
    }

    @Override
    public void clicked(String title) {
        int productId = 0;
        try {
            productId = Integer.parseInt(title);
        } catch (Exception exp) {
            productId = -1;
        }
        if (productId > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt(AppUtils.KEY_PRODUCT_ID, productId);
            Navigation.findNavController(requireView()).navigate(R.id.action_nav_listing_to_nav_details, bundle);
        } else {
            Toast.makeText(requireContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addToCartListener(CartItem item) {
        item.setQuantity(1);
        listingViewModel.insertCartItem(item);
    }
}