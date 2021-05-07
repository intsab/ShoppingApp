package com.intsab.intsabshop.Utils;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.intsab.intsabshop.R;
import com.intsab.intsabshop.data.Models.CartItem;

import java.net.InetAddress;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */
public class AppUtils {
    public static Application application;
    public static final Integer[] BANNER_IMAGES = {R.drawable.iv_1, R.drawable.iv_2, R.drawable.iv_1, R.drawable.iv_2};
    public static String KEY_PRODUCT_ID = "product_id";
    public static String KEY_CATEGORY_ID = "category_id";
    public static String KEY_AMOUNT = "amount";
    public static String KEY_ITEMS = "total_items";

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView);
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("https://fakestoreapi.com");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public interface AdapterClickListener {
        public void clicked(String title);
    }

    public interface AddToCartClickListener {
        public void addToCartListener(CartItem item);
    }

    public interface CartQuantityClickListener {
        public void minusClicked(CartItem item);

        public void plusClicked(CartItem item);
    }
}
