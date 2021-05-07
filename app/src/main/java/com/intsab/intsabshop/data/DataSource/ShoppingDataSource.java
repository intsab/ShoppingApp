package com.intsab.intsabshop.data.DataSource;


import com.intsab.intsabshop.data.Models.ProductItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */

public interface ShoppingDataSource {
    @GET()
    Call<List<ProductItem>> getProducts(@Url String url);

    @GET()
    Call<ProductItem> getProductDetails(@Url String url);

    @GET()
    Call<List<String>> getCategories(@Url String url);

    @GET()
    Call<List<ProductItem>> getProductsByCategories(@Url String url);

}
