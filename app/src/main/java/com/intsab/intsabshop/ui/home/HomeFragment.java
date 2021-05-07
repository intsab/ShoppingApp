package com.intsab.intsabshop.ui.home;

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
import androidx.viewpager.widget.ViewPager;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;
import com.intsab.intsabshop.data.Models.CartItem;
import com.intsab.intsabshop.ui.home.adapters.FeaturedImagesAdapter;
import com.intsab.intsabshop.ui.home.adapters.HorizontalGridAdapter;
import com.intsab.intsabshop.ui.home.adapters.ItemsListAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

import static com.intsab.intsabshop.Utils.AppUtils.BANNER_IMAGES;

public class HomeFragment extends Fragment implements AppUtils.AddToCartClickListener, AppUtils.AdapterClickListener {

    private HomeViewModel homeViewModel;
    int bothResults = 0;
    private final ArrayList<Integer> bannerImagesArray = new ArrayList<Integer>();
    HorizontalGridAdapter catAdapter;
    ItemsListAdapter itemsAdapter;
    RecyclerView categoriesRecyclerView;
    RecyclerView topProductsRecyclerView;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBannerView(view);
        setupViews(view);
        progressBar = (ProgressBar) view.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

        homeViewModel.getProducts(10).observe(getViewLifecycleOwner(), productItems -> {
            bothResults = bothResults + 1;
            hideProgress();
            if (productItems != null) {
                itemsAdapter = new ItemsListAdapter(productItems, requireContext(), this, this);
                topProductsRecyclerView.setAdapter(itemsAdapter);
            } else {
                hideItemsView();
            }

        });
        homeViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            bothResults = bothResults + 1;
            hideProgress();
            if (categories != null) {
                catAdapter = new HorizontalGridAdapter(categories, new AppUtils.AdapterClickListener() {
                    @Override
                    public void clicked(String title) {
                        Bundle bundle = new Bundle();
                        bundle.putString(AppUtils.KEY_CATEGORY_ID, title);
                        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_listing, bundle);
                    }
                });
                categoriesRecyclerView.setAdapter(catAdapter);
            } else {
                hideCategoriesView();
            }
        });
    }

    private void hideProgress() {
        if (bothResults % 2 == 0) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void hideItemsView() {
    }

    private void setupViews(View view) {
        LinearLayoutManager layoutManagerCat = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView = view.findViewById(R.id.rv_categories);
        categoriesRecyclerView.setLayoutManager(layoutManagerCat);

        topProductsRecyclerView = view.findViewById(R.id.rv_items);
        topProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        view.findViewById(R.id.view_all_categories).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_categoriesFragment);
            }
        });

        view.findViewById(R.id.view_all_items).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(AppUtils.KEY_CATEGORY_ID, "");
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_listing, bundle);
            }
        });
    }

    private void hideCategoriesView() {
    }

    private void setBannerView(View view) {
        bannerImagesArray.clear();
        for (int i = 0; i < AppUtils.BANNER_IMAGES.length; i++)
            bannerImagesArray.add(BANNER_IMAGES[i]);
        ViewPager mPager = (ViewPager) view.findViewById(R.id.view_pager);
        mPager.setAdapter(new FeaturedImagesAdapter(requireActivity(), bannerImagesArray));

        DotsIndicator dotsIndicator = view.findViewById(R.id.dots_indicator);
        dotsIndicator.setViewPager(mPager);
    }

    @Override
    public void addToCartListener(CartItem item) {
        item.setQuantity(1);
        homeViewModel.insertCartItem(item);
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
            Navigation.findNavController(requireView()).navigate(R.id.action_nav_home_to_nav_details, bundle);
        } else {
            Toast.makeText(requireContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}