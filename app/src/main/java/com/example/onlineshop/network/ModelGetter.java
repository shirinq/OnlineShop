package com.example.onlineshop.network;

import android.util.Log;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;

import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ModelRepository;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelGetter {

    private String TAG = "ProductGetter";
    private String baseUrl = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/";
    private NetworkUser mNetworkUser;

    private Map<String, String> mQueryMap = new HashMap<String, String>() {{

        put("consumer_key", "ck_5ad328c825899016dd30e0eca8f8da571070501b");
        put("consumer_secret", "cs_0880549adefc045db888c8b223ab612353ccf117");

    }};

    private Retrofit mNetwork = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
            .build();

    private RetrofitRequestService mRequestService;
    private ModelRepository mRepository;

    public ModelGetter(ModelRepository repository) {
        mRequestService = mNetwork.create(RetrofitRequestService.class);
        mRepository = repository;
    }

    /**
     * GET ALL PRODUCTS
     *
     * @param page
     */
    public void getProductsFromNet(int page) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(mQueryMap);
        localMap.put("page", String.valueOf(page));

        mRequestService.getProducts(localMap).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful())
                    return;
                mRepository.setTotalProducts(Integer.valueOf(response.headers().get("x-wp-total")));
                List<Product> finalList = mRepository.getLiveProducts().getValue();
                if (finalList == null)
                    finalList = new ArrayList<>();
                finalList.addAll(response.body());
                mRepository.getLiveProducts().setValue(finalList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: ProductsHolder " + t.getMessage());
                mNetworkUser.onFailureCalled();
            }
        });
    }

    /**
     * SINGLE PRODUCT
     *
     * @param id
     */
    public void getSingleProductFromNet(int id) {

        mRequestService.getProduct(id, mQueryMap).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful())
                    mRepository.getLiveProduct().setValue(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d(TAG, "onFailure: getProduct " + t.getMessage());
                mNetworkUser.onFailureCalled();
            }
        });
    }

    /**+
     * Slider
     */
    public void getSliderFromNet() {

        mRequestService.getProduct(608, mQueryMap).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful())
                    mRepository.getLiveSlider().setValue(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d(TAG, "onFailure: getSlider " + t.getMessage());
                mNetworkUser.onFailureCalled();
            }
        });
    }

    /**
     * GET RECENT PRODUCTS
     *
     * @param page
     */

    public void getRecentProductsFromNet(int page) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(mQueryMap);
        localMap.put("page", String.valueOf(page));
        localMap.put("orderby", "date");


        mRequestService.getProducts(localMap).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful())
                    return;
                List<Product> finalList = mRepository.getLiveRecentProducts().getValue();
                if (finalList == null)
                    finalList = new ArrayList<>();
                finalList.addAll(response.body());
                mRepository.getLiveRecentProducts().setValue(finalList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mNetworkUser.onFailureCalled();
                Log.d(TAG, "onFailure: RecentProducts " + t.getMessage());
                mNetworkUser.onFailureCalled();
            }
        });
    }

    /**
     * GET TOP RATED PRODUCTS
     *
     * @param page
     */
    public void getTopRatedProductsFromNet(int page) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(mQueryMap);
        localMap.put("page", String.valueOf(page));
        localMap.put("orderby", "rating");

        mRequestService.getProducts(localMap).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful())
                    return;
                List<Product> finalList = mRepository.getLiveTopRatedProducts().getValue();
                if (finalList == null)
                    finalList = new ArrayList<>();
                finalList.addAll(response.body());
                mRepository.getLiveTopRatedProducts().setValue(finalList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: TopRatedProducts " + t.getMessage());
                mNetworkUser.onFailureCalled();
            }
        });
    }

    /**
     * GET POPULAR PRODUCTS
     *
     * @param page
     */
    public void getPopularProductsFromNet(int page) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(mQueryMap);
        localMap.put("page", String.valueOf(page));
        localMap.put("orderby", "popularity");

        mRequestService.getProducts(localMap).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(!response.isSuccessful())
                    return;

                List<Product> finalList = mRepository.getLivePopularProducts().getValue();
                if (finalList == null)
                    finalList = new ArrayList<>();
                finalList.addAll(response.body());
                mRepository.getLivePopularProducts().setValue(finalList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: PopularProducts " + t.getMessage());
                mNetworkUser.onFailureCalled();
            }
        });
    }

    /**
     * GET PRODUCTS OF A CATEGORY
     *
     * @param page
     * @param categoryId
     */
    public void getProductsOfCategoryFromNet(int page, int categoryId) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(mQueryMap);
        localMap.put("page", String.valueOf(page));
        localMap.put("category", String.valueOf(categoryId));

        mRequestService.getProducts(localMap).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful())
                    return;
                mRepository.setTotalCategoryBaseProducts(Integer.valueOf(response.headers().get("x-wp-total")));
                List<Product> finalList = mRepository.getLiveProducts().getValue();
                if (finalList == null)
                    finalList = new ArrayList<>();
                finalList.addAll(response.body());
                mRepository.getLiveProducts().setValue(finalList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: ProductsOfCategory " + t.getMessage());
                mNetworkUser.onFailureCalled();
            }
        });
    }

    /**
     * GET ALL CATEGORIES
     *
     * @param page
     */
    public void getCategoriesFromNet(int page) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(mQueryMap);
        localMap.put("page", String.valueOf(page));
        localMap.put("per_page", String.valueOf(12));

        mRequestService.getCategories(localMap).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                if(!response.isSuccessful())
                    return;

                mRepository.setTotalCategories(Integer.valueOf(response.headers().get("x-wp-total")));
                List<Category> finalList = mRepository.getLiveCategories().getValue();
                if (finalList == null)
                    finalList = new ArrayList<>();
                finalList.addAll(response.body());
                mRepository.getLiveCategories().setValue(finalList);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d(TAG, "onFailure: Categories " + t.getMessage());
                mNetworkUser.onFailureCalled();
            }
        });
    }

    /**
     * SEARCHING
     *
     * @param name
     */

    public void searchProducts(String name) {

        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(mQueryMap);
        localMap.put("search", name);

        mRequestService.getProducts(localMap).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(!response.isSuccessful())
                    return;

                mRepository.setTotalSearchResult(Integer.valueOf(response.headers().get("x-wp-total")));
                List<Product> finalList = mRepository.getLiveSearchResult().getValue();
                if (finalList == null)
                    finalList = new ArrayList<>();
                finalList.addAll(response.body());
                mRepository.getLiveSearchResult().setValue(finalList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: searchProducts " + t.getMessage());
                mNetworkUser.onFailureCalled();
            }
        });
    }



    public void setViewGroup(LifecycleOwner owner){
        mNetworkUser = (NetworkUser) owner;
    }

    public interface NetworkUser {
        void onFailureCalled();
    }
}
