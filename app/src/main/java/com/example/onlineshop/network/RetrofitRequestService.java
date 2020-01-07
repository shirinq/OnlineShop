package com.example.onlineshop.network;

import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RetrofitRequestService {

    @GET(".")
    Call<List<Product>> getProducts(@QueryMap Map<String, String> map);

    @GET("{id}/")
    Call<Product> getProduct(@Path("id") int id,@QueryMap Map<String, String> map);

    @GET("categories/")
    Call<List<Category>> getCategories(@QueryMap Map<String, String> map);
}
