package com.example.onlineshop.view;


import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ProductDetailFragmentBinding;
import com.example.onlineshop.model.MyQualifier;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.example.onlineshop.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {

    private static final String ARG_PRODUCT = "product";
    private Product mProduct;
    private ProductDetailFragmentBinding mBinding;
    private RecyclerAdapter mAdapter;


    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(Product product){
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT,product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProduct = getArguments().getParcelable(ARG_PRODUCT);
        mAdapter = new RecyclerAdapter(getActivity(), MyQualifier.ImageHolder);
        mAdapter.setList(mProduct.getImageUrl());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.product_detail_fragment,container,false);
        setupRecycler();
        Picasso.get().load(mProduct.getImageUrl().get(0).getSrc()).into(mBinding.bigImage);
        mBinding.setProductViewModel(new ProductViewModel(mProduct));
        mBinding.productRegularPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return mBinding.getRoot();
    }

    private void setupRecycler(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mBinding.smallImagesList.setLayoutManager(layoutManager);
        mBinding.smallImagesList.setAdapter(mAdapter);
    }
}
