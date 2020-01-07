package com.example.onlineshop.view.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.CategoryViewholderBinding;
import com.example.onlineshop.databinding.HomePageViewholderBinding;
import com.example.onlineshop.databinding.ProductsViewholderBinding;
import com.example.onlineshop.model.MyQualifier;
import com.example.onlineshop.view.Adapter.ViewHolders.CategoryViewHolder;
import com.example.onlineshop.view.Adapter.ViewHolders.HomePageViewHolder;
import com.example.onlineshop.view.Adapter.ViewHolders.ProductImagesHolder;
import com.example.onlineshop.view.Adapter.ViewHolders.ProductsViewHolder;
import com.example.onlineshop.view.Adapter.ViewHolders.SeeMoreViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List mList = new ArrayList();
    private Activity mContext;
    private MyQualifier mQualifier;

    public void setList(List list) {
        mList = list;
        notifyDataSetChanged();
    }

    public RecyclerAdapter(Activity context, MyQualifier qualifier) {
        mQualifier = qualifier;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mQualifier == MyQualifier.HomePageHolder) {
            if (viewType == 1)
                return new SeeMoreViewHolder(LayoutInflater.from(mContext).inflate(R.layout.see_more, parent, false),mContext);
            return new HomePageViewHolder((HomePageViewholderBinding) LayoutInflating(R.layout.home_page_viewholder, parent),mContext);
        }

        else if (mQualifier == MyQualifier.CategoryHolder)
            return new CategoryViewHolder((CategoryViewholderBinding) LayoutInflating(R.layout.category_viewholder, parent), mContext);


        else if (mQualifier == MyQualifier.ProductsHolder)
            return new ProductsViewHolder((ProductsViewholderBinding) LayoutInflating(R.layout.products_viewholder, parent),mContext);

        else if(mQualifier == MyQualifier.ImageHolder)
            return new ProductImagesHolder(new ImageView(mContext),mContext);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SeeMoreViewHolder)
            return;
        ((BindCallBack) holder).bindHolder(mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 9)
            return 1;
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private ViewDataBinding LayoutInflating(@LayoutRes int layout, ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(mContext), layout, parent, false);
    }

    public interface BindCallBack<O> {
        void bindHolder(O model);
    }
}
