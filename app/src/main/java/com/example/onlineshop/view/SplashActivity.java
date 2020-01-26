package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.onlineshop.R;
import com.example.onlineshop.network.ModelGetter;
import com.example.onlineshop.viewmodel.RepositoryViewModel;
import com.example.onlineshop.viewmodel.RequestViewModel;
import com.example.onlineshop.viewmodel.ConnectivityViewModel;
import com.google.android.material.snackbar.Snackbar;

public class SplashActivity extends AppCompatActivity implements ModelGetter.NetworkUser {

    private RequestViewModel mRequestViewModel;
    private RepositoryViewModel mRepositoryViewModel;
    private ConnectivityViewModel mConnectivity;
    private Snackbar mSnackbar;
    private LottieAnimationView mAnim;
    private int successfulReq = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAnim = findViewById(R.id.splash);

        mRequestViewModel = new RequestViewModel();
        mRepositoryViewModel = new RepositoryViewModel();
        mConnectivity = ViewModelProviders.of(this).get(ConnectivityViewModel.class);

        mRequestViewModel.setRequestOwner(this);

        makeRequest();

        mRepositoryViewModel.LivePopular().observe(this, products -> StartMainActivity());
        mRepositoryViewModel.LiveRecent().observe(this, products -> StartMainActivity());
        mRepositoryViewModel.LiveTopRated().observe(this, products -> StartMainActivity());

    }

    private void StartMainActivity() {
        successfulReq++;
        if (successfulReq == 3) {
            startActivity(MainActivity.newIntent(this));
            this.finish();
        }
    }

    private void showSnackBar() {
        mSnackbar = Snackbar.make(findViewById(R.id.splash), getString(R.string.connection_fail), Snackbar.LENGTH_INDEFINITE);
        View snackbarLayout = mSnackbar.getView();
        TextView textView =  snackbarLayout.findViewById(R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_refresh_white_24dp, 0, 0, 0);
        ViewCompat.setLayoutDirection(mSnackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
        textView.setOnClickListener(view -> makeRequest());
        mSnackbar.show();
        mAnim.pauseAnimation();
    }

    private void makeRequest() {
        if (mConnectivity.checkConnectivity()) {
            mRequestViewModel.getModels();
            mAnim.playAnimation();
            if (mSnackbar != null)
                mSnackbar.dismiss();

        } else showSnackBar();
    }

    @Override
    public void onFailureCalled() {
        showSnackBar();
    }
}
