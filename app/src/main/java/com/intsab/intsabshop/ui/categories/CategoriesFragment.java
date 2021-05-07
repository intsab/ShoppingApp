package com.intsab.intsabshop.ui.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;
import com.intsab.intsabshop.Utils.decoration.GridDecoration;
import com.intsab.intsabshop.ui.categories.adapters.CategoryGridAdapter;

public class CategoriesFragment extends Fragment {
    ProgressBar progressBar;
    private CategoriesViewModel categoriesViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        categoriesViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        return inflater.inflate(R.layout.categories_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        RecyclerView categoriesRecyclerView = view.findViewById(R.id.recyclerView);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        categoriesRecyclerView.addItemDecoration(new GridDecoration(16));

        categoriesViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            progressBar.setVisibility(View.GONE);
            if (categories != null) {
                CategoryGridAdapter catAdapter = new CategoryGridAdapter(categories, new AppUtils.AdapterClickListener() {
                    @Override
                    public void clicked(String title) {
                        Bundle bundle = new Bundle();
                        bundle.putString(AppUtils.KEY_CATEGORY_ID, title);
                        Navigation.findNavController(view).navigate(R.id.action_categoriesFragment_to_nav_listing, bundle);
                    }
                });
                categoriesRecyclerView.setAdapter(catAdapter);
            } else {
                hideCategoriesView();
            }
        });
    }

    private void hideCategoriesView() {
    }

}