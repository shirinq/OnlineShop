package com.example.onlineshop.viewmodel;


import androidx.lifecycle.LiveData;

import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ModelRepository;

import java.util.List;


public class RepositoryViewModel {

    private ModelRepository mRepository = ModelRepository.getInstance();

    public LiveData<List<Product>> LiveProducts() { return mRepository.getLiveProducts(); }

    public LiveData<List<Product>> LiveRecent(){
        return mRepository.getLiveRecentProducts();
    }

    public LiveData<List<Product>> LivePopular(){
        return mRepository.getLivePopularProducts();
    }

    public LiveData<List<Product>> LiveTopRated(){
        return mRepository.getLiveTopRatedProducts();
    }

    public LiveData<List<Category>> LiveCategory(){
        return mRepository.getLiveCategories();
    }

    public LiveData<List<Product>> LiveSearchProduct() {
        return mRepository.getLiveSearchResult();
    }

    public LiveData<Product> LiveProduct(){
        return mRepository.getLiveProduct();
    }

    public LiveData<Product> LiveSlider(){
        return mRepository.getLiveSlider();
    }
}
