package com.example.onlineshop.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ListContainerBinding;
import com.example.onlineshop.model.MyQualifier;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.example.onlineshop.viewmodel.ConnectivityViewModel;
import com.example.onlineshop.viewmodel.RepositoryViewModel;
import com.example.onlineshop.viewmodel.RequestViewModel;

import org.jetbrains.annotations.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends ParentFragment {

    private ListContainerBinding mBinding;
    private RecyclerAdapter mAdapter;
    private RepositoryViewModel mRepositoryViewModel;
    private RequestViewModel mRequestViewModel;
    private ConnectivityViewModel mConnectivityViewModel;
    private int mCurrentPage = 1;


    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new RecyclerAdapter(getActivity(), MyQualifier.CategoryHolder);

        mRepositoryViewModel = new RepositoryViewModel();
        mRequestViewModel = new RequestViewModel();
        mConnectivityViewModel = ViewModelProviders.of(this).get(ConnectivityViewModel.class);

        mRepositoryViewModel.LiveCategory().observe(this, categories -> {
            mAdapter.setList(categories);
            mBinding.progressBar.setVisibility(View.GONE);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_container, container, false);
        mBinding.modelRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mBinding.modelRecycler.setAdapter(mAdapter);
        RecyclerScrollHandler();
        return mBinding.getRoot();
    }

    @Override
    public void ConnectivityChange() {
        makeRequest();
    }

    private void RecyclerScrollHandler() {
        mBinding.modelRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    makeRequest();
                }
            }
        });
    }

    private void makeRequest() {
        if (!mConnectivityViewModel.checkConnectivity())
            return;
        mBinding.progressBar.setVisibility(View.VISIBLE);
        boolean hasNext = mRequestViewModel.getCategories(mCurrentPage);
        mCurrentPage++;
        if (hasNext)
            mBinding.progressBar.setVisibility(View.GONE);
    }
}
