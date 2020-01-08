package com.example.onlineshop.view.Adapter.ViewHolders;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.databinding.HomePageViewholderBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.example.onlineshop.view.ProductActivity;
import com.example.onlineshop.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;


public class HomePageViewHolder extends RecyclerView.ViewHolder implements RecyclerAdapter.BindCallBack<Product> {

    private HomePageViewholderBinding mBinding;
    private ProductViewModel mViewModel;

    public HomePageViewHolder(HomePageViewholderBinding binding, Context context) {
        super(binding.getRoot());

        mBinding = binding;
        mViewModel = new ProductViewModel(context);
        mBinding.setProductViewModel(mViewModel);
    }

    @Override
    public void bindHolder(Product product) {

        mViewModel.setProduct(product);
        Picasso.get().load(product.getImageUrl().get(0).getSrc()).into(mBinding.productImage);
    }
}
