package com.example.onlineshop.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ListContainerBinding;
import com.example.onlineshop.model.MyQualifier;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.example.onlineshop.viewmodel.ConnectivityViewModel;
import com.example.onlineshop.viewmodel.RepositoryViewModel;
import com.example.onlineshop.viewmodel.RequestViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsListFragment extends ParentFragment {

    private static final String ARG_CATEGORY_ID = "categoryId";
    private static final String ARG_QUALIFIER = "qualifier";
    private ListContainerBinding mBinding;
    private RecyclerAdapter mAdapter;

    private RepositoryViewModel mRepositoryViewModel;
    private RequestViewModel mRequestViewModel;
    private ConnectivityViewModel mConnectivityViewModel;

    private Integer mCategoryId;
    private int mCurrentPage = 2;

    public ProductsListFragment() {
        // Required empty public constructor
    }

    public static ProductsListFragment newInstance(MyQualifier qualifier) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUALIFIER, qualifier);
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductsListFragment newInstance(int categoryId) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_ID, categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoryId = getArguments().getInt(ARG_CATEGORY_ID);

        mAdapter = new RecyclerAdapter(getActivity(), MyQualifier.ProductsHolder);

        mRepositoryViewModel = new RepositoryViewModel();
        mRequestViewModel = new RequestViewModel();
        mConnectivityViewModel = ViewModelProviders.of(this).get(ConnectivityViewModel.class);

        mRepositoryViewModel.LiveProducts().observe(this, products -> {
            mAdapter.setList(products);
            mBinding.progressBar.setVisibility(View.GONE);
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_container, container, false);
        /*StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);*/
        mBinding.modelRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.modelRecycler.setAdapter(mAdapter);

        if (mCategoryId != null)
            RecyclerScrollHandler();

        return mBinding.getRoot();
    }

    @Override
    public void ConnectivityChange() {
        if (!mConnectivityViewModel.checkConnectivity())
            Toast.makeText(getContext(),R.string.no_connection,Toast.LENGTH_SHORT).show();
        //makeRequest();
    }

    private void RecyclerScrollHandler() {
        mBinding.modelRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1))
                    makeRequest();
            }
        });
    }

    private void makeRequest() {

        if (!mConnectivityViewModel.checkConnectivity())
            return;

        boolean hasNext = mRequestViewModel.getCategoryProducts(mCurrentPage, mCategoryId);
        mCurrentPage++;
        if (!hasNext)
            mBinding.progressBar.setVisibility(View.GONE);
        else
            mBinding.progressBar.setVisibility(View.VISIBLE);
    }

}
