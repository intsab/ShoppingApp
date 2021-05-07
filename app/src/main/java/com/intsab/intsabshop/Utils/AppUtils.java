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
class AppUtils {

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_category)
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
}
