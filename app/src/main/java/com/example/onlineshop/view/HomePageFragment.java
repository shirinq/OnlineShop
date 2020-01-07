package com.example.onlineshop.view;


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
import com.example.onlineshop.databinding.FragmentHomePageBinding;
import com.example.onlineshop.model.MyQualifier;
import com.example.onlineshop.view.Adapter.RecyclerAdapter;
import com.example.onlineshop.view.Adapter.SliderAdapter;
import com.example.onlineshop.viewmodel.RepositoryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private FragmentHomePageBinding mBinding;

    private RecyclerAdapter mRecentAdapter;
    private RecyclerAdapter mPopularAdapter;
    private RecyclerAdapter mTopRatedAdapter;

    private SliderAdapter mSliderAdapter;

    private RepositoryViewModel mViewModel;


    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance(){
        return new HomePageFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new RepositoryViewModel();

        mRecentAdapter = new RecyclerAdapter(getActivity(), MyQualifier.HomePageHolder);
        mPopularAdapter = new RecyclerAdapter(getActivity(), MyQualifier.HomePageHolder);
        mTopRatedAdapter = new RecyclerAdapter(getActivity(), MyQualifier.HomePageHolder);

        mSliderAdapter = new SliderAdapter(getActivity());

        mViewModel.LiveRecent().observe(this, products -> mRecentAdapter.setList(products));
        mViewModel.LivePopular().observe(this, products -> mPopularAdapter.setList(products));
        mViewModel.LiveTopRated().observe(this, products -> mTopRatedAdapter.setList(products));

        mViewModel.LiveProduct().observe(this, product -> mSliderAdapter.setList(product.getImageUrl()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page,container,false);

        setupRecyclers(mBinding.recentRecycler,mRecentAdapter);
        setupRecyclers(mBinding.popularRecycler,mPopularAdapter);
        setupRecyclers(mBinding.topRatedRecycler,mTopRatedAdapter);

        setupSlider();

        return mBinding.getRoot();
    }

    private void setupRecyclers(RecyclerView recyclerView , RecyclerAdapter adapter){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupSlider(){
        mBinding.imageSlider.setSliderAdapter(mSliderAdapter);
    }
}
