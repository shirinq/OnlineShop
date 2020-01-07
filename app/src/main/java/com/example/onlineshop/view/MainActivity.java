package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivityMainBinding;
import com.example.onlineshop.model.MyQualifier;
import com.example.onlineshop.view.Adapter.ViewHolders.CategoryViewHolder;
import com.example.onlineshop.view.Adapter.ViewHolders.SeeMoreViewHolder;
import com.example.onlineshop.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements CategoryViewHolder.FragmentAdder, SeeMoreViewHolder.SeeMoreFragmentAdder {

    private ActivityMainBinding mBinding;
    private MainActivityViewModel mainViewModel;
    private boolean doubleBackToExitPressedOnce = false;

    public static Intent newIntent(Context context){
        return new Intent(context,MainActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mainViewModel = new MainActivityViewModel(mBinding);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,HomePageFragment.newInstance())
                    .commit();
        }

        mBinding.category.setOnClickListener(view -> {
            BottomAppBarOnClick(mBinding.category);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, CategoriesFragment.newInstance())
                    .commit();
        });

        mBinding.home.setOnClickListener(view -> {
            BottomAppBarOnClick(mBinding.home);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,HomePageFragment.newInstance())
                    .commit();
        });

        mBinding.search.setOnClickListener(view -> BottomAppBarOnClick(mBinding.search));

        mBinding.tagged.setOnClickListener(view -> BottomAppBarOnClick(mBinding.tagged));

        mBinding.shoppingBag.setOnClickListener(view -> BottomAppBarOnClick(mBinding.shoppingBag));

        mBinding.profile.setOnClickListener(view -> {
            if(!mBinding.drawerLayout.isDrawerOpen(Gravity.RIGHT)) mBinding.drawerLayout.openDrawer(Gravity.RIGHT);
            else mBinding.drawerLayout.closeDrawer(Gravity.RIGHT);
        });
    }

    private void BottomAppBarOnClick(ImageView view){
        view.setImageDrawable(getResources().getDrawable(mainViewModel.getColored().get(view)));
        for(ImageView imageView : mainViewModel.getBlack().keySet()){
            if(imageView==view)
                continue;
            imageView.setImageDrawable(getResources().getDrawable(mainViewModel.getBlack().get(imageView)));
        }
    }

    @Override
    public void CategoryDetail(int categoryId) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ProductsListFragment.newInstance(categoryId))
                .addToBackStack("CategoryProductsFragment")
                .commit();
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce || getSupportFragmentManager().getBackStackEntryCount()==1) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getResources().getString(R.string.exit_app), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
    }

    @Override
    public void ProductsList(MyQualifier qualifier) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ProductsListFragment.newInstance(qualifier))
                .addToBackStack("SeeMoreProductsFragment")
                .commit();
    }
}
