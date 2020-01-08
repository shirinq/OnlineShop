package com.example.onlineshop.view.Adapter.ViewHolders;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineshop.databinding.ProductsViewholderBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.example.onlineshop.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;

public class ProductsViewHolder extends RecyclerView.ViewHolder implements RecyclerAdapter.BindCallBack<Product> {

    private ProductsViewholderBinding mBinding;
    private ProductViewModel mViewModel;
    private boolean visible = true;

    public ProductsViewHolder(ProductsViewholderBinding binding, Context context) {
        super(binding.getRoot());
        mBinding = binding;

        mViewModel = new ProductViewModel(context);

        mBinding.chip.setOnClickListener(view -> mViewModel.productDetail());

        mBinding.productImage.setOnClickListener(view -> {
            if(visible)
                setViewsVisibility(View.GONE);
            else
                setViewsVisibility(View.VISIBLE);
        });
    }

    @Override
    public void bindHolder(Product product) {

        mViewModel.setProduct(product);

        mBinding.productName.setText(product.getName());
        mBinding.productRate.setText(product.getAvRate());

        setViewsVisibility(View.VISIBLE);
        visible = true;

        Picasso.get().load(product.getImageUrl().get(0).getSrc()).centerInside().fit().into(mBinding.productImage);
    }

    private void setViewsVisibility(int visibility){
        mBinding.detail.setVisibility(visibility);
        mBinding.productRate.setVisibility(visibility);
        mBinding.star.setVisibility(visibility);
        visible = !visible;
    }
}
