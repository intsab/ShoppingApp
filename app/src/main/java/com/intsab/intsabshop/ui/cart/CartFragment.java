package com.intsab.intsabshop.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.intsab.intsabshop.MainActivity;
import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;
import com.intsab.intsabshop.Utils.CartUtils;
import com.intsab.intsabshop.data.Models.CartItem;
import com.intsab.intsabshop.ui.cart.adapters.CartAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.intsab.intsabshop.Utils.AppUtils.KEY_AMOUNT;
import static com.intsab.intsabshop.Utils.AppUtils.KEY_ITEMS;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;
    List<CartItem> cartItemList = new ArrayList<>();

    RecyclerView cartRecyclerView;
    ProgressBar progressBar;
    CartAdapter itemsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        return inflater.inflate(R.layout.cart_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartRecyclerView = view.findViewById(R.id.cart_rv);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = (ProgressBar) view.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);


        cartViewModel.getAllCartItems().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartItemList = cartItems;
                progressBar.setVisibility(View.GONE);
                if (cartItems != null) {
                    itemsAdapter = new CartAdapter(cartItems, requireContext(), new AppUtils.CartQuantityClickListener() {
                        @Override
                        public void minusClicked(CartItem item) {
                            cartViewModel.update(item.get_id(), item.getQuantity());
                        }

                        @Override
                        public void plusClicked(CartItem item) {
                            cartViewModel.update(item.get_id(), item.getQuantity());
                        }
                    });
                    cartRecyclerView.setAdapter(itemsAdapter);
                } else {
                    showNoItem();
                }

            }
        });

        view.findViewById(R.id.btn_checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(KEY_AMOUNT, "" + CartUtils.getCartTotal(cartItemList));
                bundle.putString(KEY_ITEMS, "" + cartItemList.size());
                Navigation.findNavController(view).navigate(R.id.action_nav_cart_to_nav_conformation, bundle);
            }
        });
    }

    private void showNoItem() {
    }

}