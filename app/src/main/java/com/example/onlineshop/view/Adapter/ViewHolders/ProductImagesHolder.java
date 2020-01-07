package com.example.onlineshop.view.Adapter.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ProductDetailFragmentBinding;
import com.example.onlineshop.model.ImagesItem;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.squareup.picasso.Picasso;

public class ProductImagesHolder extends RecyclerView.ViewHolder implements RecyclerAdapter.BindCallBack<ImagesItem> {

    private ImageView mImageView;
    private ProductDetailFragmentBinding mBinding;

    public ProductImagesHolder(@NonNull View itemView, Context context) {
        super(itemView);

        mImageView = (ImageView) itemView;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 130);
        layoutParams.setMargins(0,0,15,0);
        mImageView.setBackground(context.getResources().getDrawable(R.drawable.image_background));
        mImageView.setLayoutParams(layoutParams);
    }

    @Override
    public void bindHolder(ImagesItem model) {
        Picasso.get().load(model.getSrc()).into(mImageView);
    }
}
