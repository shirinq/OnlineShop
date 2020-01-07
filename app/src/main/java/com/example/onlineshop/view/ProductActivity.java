package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;
import com.example.onlineshop.viewmodel.RepositoryViewModel;
import com.example.onlineshop.viewmodel.RequestViewModel;

public class ProductActivity extends AppCompatActivity {

    private static final String EXTRA_PRODUCT_ID = "product_id";

    public static Intent newIntent(Context target, int productId){
        Intent intent = new Intent(target, ProductActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        new RequestViewModel().getProduct(getIntent().getIntExtra(EXTRA_PRODUCT_ID,0));

        if (savedInstanceState == null){
            new RepositoryViewModel().LiveProduct().observe(this,product ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,
                                ProductDetailFragment.newInstance(product))
                        .commit());
        }
    }
}
