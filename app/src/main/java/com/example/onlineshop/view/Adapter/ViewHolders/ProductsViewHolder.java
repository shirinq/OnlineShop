package com.example.onlineshop.view.Adapter.ViewHolders;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineshop.databinding.ProductsViewholderBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.example.onlineshop.view.ProductActivity;
import com.squareup.picasso.Picasso;

public class ProductsViewHolder extends RecyclerView.ViewHolder implements RecyclerAdapter.BindCallBack<Product> {

    private ProductsViewholderBinding mBinding;
    private Product mProduct;
    private boolean visible = true;

    public ProductsViewHolder(ProductsViewholderBinding binding, Context context) {
        super(binding.getRoot());
        mBinding = binding;
        mBinding.productImage.setOnClickListener(view -> {
            if(visible)
                setViewsVisibility(View.GONE);
            else
                setViewsVisibility(View.VISIBLE);
        });

        mBinding.chip.setOnClickListener(view -> context.startActivity(ProductActivity.newIntent(context,mProduct.getmId())));
    }

    @Override
    public void bindHolder(Product product) {

        mProduct = product;

        setViewsVisibility(View.VISIBLE);
        visible = true;
        mBinding.productName.setText(product.getName());
        mBinding.productPrice.setText(product.getPrice());
        mBinding.productRate.setText(product.getAvRate());

        Picasso.get().load(product.getImageUrl().get(0).getSrc()).centerInside().fit().into(mBinding.productImage);
    }

    private void setViewsVisibility(int visibility){
        mBinding.detail.setVisibility(visibility);
        mBinding.productRate.setVisibility(visibility);
        mBinding.star.setVisibility(visibility);
        visible = !visible;
    }
}
