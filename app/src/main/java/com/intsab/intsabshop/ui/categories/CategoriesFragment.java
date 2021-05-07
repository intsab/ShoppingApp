package com.intsab.intsabshop.ui.categories;

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

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;
import com.intsab.intsabshop.ui.home.adapters.CategoryGridAdapter;

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

        LinearLayoutManager layoutManagerCat = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView categoriesRecyclerView = view.findViewById(R.id.recyclerView);
        categoriesRecyclerView.setLayoutManager(layoutManagerCat);


        categoriesViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            progressBar.setVisibility(View.GONE);
            if (categories != null) {
                CategoryGridAdapter catAdapter = new CategoryGridAdapter(categories, new AppUtils.AdapterClickListener() {
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

    private void hideCategoriesView() {
    }

}