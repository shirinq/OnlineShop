package com.example.onlineshop.viewmodel;

import androidx.lifecycle.LifecycleOwner;

import com.example.onlineshop.repository.ModelRepository;

public class RequestViewModel {

    private ModelRepository mRepository;


    public RequestViewModel() {
        this.mRepository = ModelRepository.getInstance();
    }

    public void getModels(){
        mRepository.getRecentProducts(1);
        mRepository.getPopularProducts(1);
        mRepository.getTopRatedProducts(1);
        mRepository.getCategories(1);
        mRepository.getSlider();
    }

    public boolean getCategories(int page){
        return mRepository.getCategories(page);
    }

    public boolean getCategoryProducts(int page ,int categoryId){
        return mRepository.getCategoryBaseProducts(page,categoryId);
    }

    public void getProduct(int productId){
        mRepository.getProduct(productId);
    }

    public void setRequestOwner(LifecycleOwner view){
        mRepository.setRequestOwner(view);
    }
}
