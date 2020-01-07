package com.example.onlineshop.view.Adapter.ViewHolders;

import android.app.Activity;
import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.databinding.CategoryViewholderBinding;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.squareup.picasso.Picasso;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements RecyclerAdapter.BindCallBack<Category> {

    private CategoryViewholderBinding mBinding;
    private Category mCategory;
    private Context mActivity;


    public CategoryViewHolder(CategoryViewholderBinding binding, Activity activity) {
        super(binding.getRoot());
        mActivity = activity;
        mBinding = binding;

        mBinding.getRoot().setOnClickListener(view -> ((FragmentAdder) mActivity).CategoryDetail(mCategory.getmId()));
    }

    @Override
    public void bindHolder(Category category) {
        mCategory = category;
        mBinding.categoryTitle.setText(category.getmName());
        Picasso.get().load(category.getmImages().getSrc()).into(mBinding.categoryImage);
    }

    public interface FragmentAdder {
        void CategoryDetail(int id);
    }
}
