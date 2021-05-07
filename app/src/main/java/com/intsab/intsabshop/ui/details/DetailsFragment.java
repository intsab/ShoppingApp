package com.intsab.intsabshop.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;
import com.intsab.intsabshop.data.Models.ProductItem;

public class DetailsFragment extends Fragment {
    int id = 1;
    private DetailsViewModel detailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getInt(AppUtils.KEY_PRODUCT_ID);
        detailsViewModel.getProductDetails(id).observe(getViewLifecycleOwner(), productItems -> {
            loadDetails(view, productItems);
        });
    }

    private void loadDetails(View view, ProductItem item) {
        TextView description = view.findViewById(R.id.description);
        ImageView imageView = view.findViewById(R.id.iv_product);
        TextView title = view.findViewById(R.id.title);
        TextView price = view.findViewById(R.id.price);

        if (item == null) {
            Toast.makeText(requireContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        } else {
            description.setText(item.getDescription());
            title.setText(item.getTitle());
            price.setText("Price is " + item.getPrice());
            AppUtils.loadImage(requireContext(), item.getImage(), imageView);
        }

    }
}