package com.example.onlineshop.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.ProductActivity;


public class ProductViewModel extends BaseObservable {

    private Product mProduct;
    private Context mContext;

    public ProductViewModel(Product product) {
        mProduct = product;
    }

    public ProductViewModel(Context context) {
        mContext = context;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
        notifyChange();
    }

    @Bindable
    public String getName(){
        return mProduct.getName();
    }

    @Bindable
    public String getPrice(){
        return mProduct.getPrice();
    }

    @Bindable
    public String getRegularPrice(){
        return mProduct.getRegularPrice();
    }

    @Bindable
    public String getAvRate(){
        return mProduct.getAvRate();
    }

    @Bindable
    public String getDescription(){
        return mProduct.getDescription();
    }

    public void productDetail(){
        mContext.startActivity(ProductActivity.newIntent(mContext,mProduct.getmId()));
    }
}
