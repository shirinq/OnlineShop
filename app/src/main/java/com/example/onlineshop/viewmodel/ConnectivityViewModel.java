package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ConnectivityViewModel extends AndroidViewModel {

    private Context mContext;
    public ConnectivityViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
    }

    public boolean checkConnectivity(){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null;
    }
}
