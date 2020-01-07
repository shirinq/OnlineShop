package com.example.onlineshop.viewmodel;

import android.widget.ImageView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivityViewModel {

    private Map<ImageView, Integer> black = new HashMap<>();
    private Map<ImageView, Integer> colored = new HashMap<>();

    public MainActivityViewModel(ActivityMainBinding mainBinding) {
        black.put(mainBinding.home, R.drawable.ic_shop_black);
        black.put(mainBinding.category,R.drawable.ic_category_black);
        black.put(mainBinding.search,R.drawable.ic_search_black);
        black.put(mainBinding.tagged,R.drawable.ic_bookmark_black);
        black.put(mainBinding.shoppingBag,R.drawable.ic_shopping_bags_empty_black);

        colored.put(mainBinding.home,R.drawable.ic_shop_colored);
        colored.put(mainBinding.category,R.drawable.ic_category_colored);
        colored.put(mainBinding.search,R.drawable.ic_search_colored);
        colored.put(mainBinding.tagged,R.drawable.ic_bookmark_colored);
        colored.put(mainBinding.shoppingBag,R.drawable.ic_shopping_bags_empty_colored);
    }

    public Map<ImageView, Integer> getBlack() {
        return black;
    }

    public Map<ImageView, Integer> getColored() {
        return colored;
    }
}
