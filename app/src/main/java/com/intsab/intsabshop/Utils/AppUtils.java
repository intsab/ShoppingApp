package com.intsab.intsabshop.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.intsab.intsabshop.R;

import java.net.InetAddress;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */
public class AppUtils {
    public static final Integer[] BANNER_IMAGES = {R.drawable.iv_1, R.drawable.iv_2, R.drawable.iv_1, R.drawable.iv_2};

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
}
