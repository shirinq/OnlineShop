package com.example.onlineshop.view.Adapter.ViewHolders;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.model.MyQualifier;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SeeMoreViewHolder extends RecyclerView.ViewHolder {

    private FloatingActionButton mButton;
    private SeeMoreFragmentAdder mActivity;

    public SeeMoreViewHolder(@NonNull View itemView, Activity activity) {
        super(itemView);
        mActivity = (SeeMoreFragmentAdder) activity;
        mButton = itemView.findViewById(R.id.see_more);
        mButton.setOnClickListener(view ->{});
    }

    public interface SeeMoreFragmentAdder {
        void ProductsList(MyQualifier qualifier);
    }
}
