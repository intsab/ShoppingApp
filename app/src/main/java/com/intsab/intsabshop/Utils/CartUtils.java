package com.intsab.intsabshop.Utils;

import com.intsab.intsabshop.data.Models.CartItem;
import com.intsab.intsabshop.data.Models.ProductItem;

import java.util.List;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */

public class CartUtils {


    public static CartItem productItemToCart(ProductItem item) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(0);

        cartItem.setImage(item.getImage());
        cartItem.setPrice(item.getPrice());
        cartItem.setId(item.getId());
        cartItem.setTitle(item.getTitle());
        cartItem.setDescription(item.getDescription());
        cartItem.setCategory(item.getCategory());
        return cartItem;
    }

    public static double getCartTotal(List<CartItem> cartItems) {
        double totalPrice = 0;
        for (int x = 0; x < cartItems.size(); x++) {
            totalPrice = totalPrice + cartItems.get(x).getPrice();
        }
        return totalPrice;
    }
}
