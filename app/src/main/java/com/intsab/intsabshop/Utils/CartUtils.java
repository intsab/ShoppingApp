package com.intsab.intsabshop.Utils;

import com.intsab.intsabshop.data.Models.CartItem;

import java.util.ArrayList;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */

public class CartUtils {
    public static ArrayList<CartItem> cartList = new ArrayList<>();

    public static void clearCart() {
        cartList.clear();
    }

    public static double getCartTotal() {
        double totalPrice = 0;
        for (int x = 0; x < cartList.size(); x++) {
            totalPrice = totalPrice + cartList.get(x).getPrice();
        }
        return totalPrice;
    }

    public static void removeItemFromCart(CartItem item) {
        cartList.remove(item);
    }

    public static void addItemToCart(CartItem item) {
        cartList.add(item);
    }

    public static void updateCart(CartItem item) {
        for (int x = 0; x < cartList.size(); x++) {
            if (item.getId() == cartList.get(x).getId()) {
                cartList.get(x).setQuantity(item.getQuantity());
            }
        }
    }
}
