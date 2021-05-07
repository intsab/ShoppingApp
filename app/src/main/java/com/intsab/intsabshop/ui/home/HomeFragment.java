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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;
import com.intsab.intsabshop.ui.home.adapters.CategoryGridAdapter;
import com.intsab.intsabshop.ui.home.adapters.FeaturedImagesAdapter;
import com.intsab.intsabshop.ui.home.adapters.ItemsListAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

import static com.intsab.intsabshop.Utils.AppUtils.BANNER_IMAGES;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private final ArrayList<Integer> bannerImagesArray = new ArrayList<Integer>();
    CategoryGridAdapter catAdapter;
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
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        homeViewModel.getProducts(10).observe(getViewLifecycleOwner(), productItems -> {
            progressBar.setVisibility(View.GONE);
            if (productItems != null) {
                itemsAdapter = new ItemsListAdapter(productItems, requireContext(), new AppUtils.AdapterClickListener() {
                    @Override
                    public void clicked(String title) {
                        Toast.makeText(requireContext(), "" + title, Toast.LENGTH_SHORT).show();
                    }
                });
                topProductsRecyclerView.setAdapter(itemsAdapter);
            } else {
                hideItemsView();
            }


        });
        homeViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            progressBar.setVisibility(View.GONE);
            if (categories != null) {
                catAdapter = new CategoryGridAdapter(categories, new AppUtils.AdapterClickListener() {
                    @Override
                    public void clicked(String title) {
                        Toast.makeText(requireContext(), "" + title, Toast.LENGTH_SHORT).show();
                    }
                });
                categoriesRecyclerView.setAdapter(catAdapter);
            } else {
                hideCategoriesView();
            }
        });
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

            }
        });

        view.findViewById(R.id.view_all_items).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void hideCategoriesView() {
    }

    private void setBannerView(View view) {
        for (int i = 0; i < AppUtils.BANNER_IMAGES.length; i++)
            bannerImagesArray.add(BANNER_IMAGES[i]);
        ViewPager mPager = (ViewPager) view.findViewById(R.id.view_pager);
        mPager.setAdapter(new FeaturedImagesAdapter(requireActivity(), bannerImagesArray));

        DotsIndicator dotsIndicator = view.findViewById(R.id.dots_indicator);
        dotsIndicator.setViewPager(mPager);
    }
}