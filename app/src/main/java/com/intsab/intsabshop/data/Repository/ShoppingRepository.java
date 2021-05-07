package com.intsab.intsabshop.data.Repository;

import androidx.lifecycle.MutableLiveData;

import com.intsab.intsabshop.data.DataSource.ShoppingDataSource;
import com.intsab.intsabshop.data.Models.ProductItem;
import com.intsab.intsabshop.data.Network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */
public class ShoppingRepository {
    public ShoppingDataSource getApiInterface() {
        return RetrofitClient.getClient().create(ShoppingDataSource.class);
    }

    public MutableLiveData<List<ProductItem>> getProducts(int limit) {
        MutableLiveData<List<ProductItem>> productsLiveData = new MutableLiveData<>();
        Call<List<ProductItem>> call = getApiInterface().getProducts("/products?limit=" + limit);
        call.enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(@NotNull Call<List<ProductItem>> call, @NotNull Response<List<ProductItem>> response) {
                productsLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<ProductItem>> call, @NotNull Throwable t) {
                productsLiveData.postValue(null);
            }
        });

        return productsLiveData;
    }

    public MutableLiveData<ProductItem> getProductDetails(int id) {
        MutableLiveData<ProductItem> productDetailsLiveData = new MutableLiveData<>();

        Call<ProductItem> call = getApiInterface().getProductDetails("/products/" + id);
        call.enqueue(new Callback<ProductItem>() {
            @Override
            public void onResponse(@NotNull Call<ProductItem> call, @NotNull Response<ProductItem> response) {
                productDetailsLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ProductItem> call, @NotNull Throwable t) {
                productDetailsLiveData.postValue(null);

            }
        });

        return productDetailsLiveData;
    }

    public MutableLiveData<List<ProductItem>> getProductsByCategory(String category) {
        MutableLiveData<List<ProductItem>> productsLiveData = new MutableLiveData<>();

        Call<List<ProductItem>> call = getApiInterface().getProductsByCategories("/products/category/" + category);
        call.enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(@NotNull Call<List<ProductItem>> call, @NotNull Response<List<ProductItem>> response) {
                productsLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<ProductItem>> call, @NotNull Throwable t) {
                productsLiveData.postValue(null);
            }
        });
        return productsLiveData;
    }

    public MutableLiveData<List<String>> getCategories() {
        MutableLiveData<List<String>> categoriesLiveData = new MutableLiveData<>();

        Call<List<String>> call = getApiInterface().getCategories("/products/categories");
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NotNull Call<List<String>> call, @NotNull Response<List<String>> response) {
                categoriesLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<String>> call, @NotNull Throwable t) {
                categoriesLiveData.postValue(null);
            }
        });
        return categoriesLiveData;
    }
}

