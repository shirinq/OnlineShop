package com.example.onlineshop.view.Adapter.ViewHolders;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.databinding.HomePageViewholderBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.example.onlineshop.view.ProductActivity;
import com.squareup.picasso.Picasso;


public class HomePageViewHolder extends RecyclerView.ViewHolder implements RecyclerAdapter.BindCallBack<Product> {

    private HomePageViewholderBinding mBinding;
    private Product mProduct;

    public HomePageViewHolder(HomePageViewholderBinding binding, Context context) {
        super(binding.getRoot());

        mBinding = binding;
        mBinding.getRoot().setOnClickListener(view -> {
            context.startActivity(ProductActivity.newIntent(context,mProduct.getmId()));
        });
    }

    @Override
    public void bindHolder(Product product) {

        mProduct = product;
        mBinding.productName.setText(product.getName());
        mBinding.productPrice.setText(product.getPrice());
        Picasso.get().load(product.getImageUrl().get(0).getSrc()).into(mBinding.productImage);
    }
}
