package com.example.onlineshop.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ModelGetter;

import java.util.ArrayList;
import java.util.List;

public class ModelRepository {

    private static ModelRepository mInstance;

    private MutableLiveData<List<Product>> mLiveProducts;
    private MutableLiveData<List<Product>> mLiveRecentProducts;
    private MutableLiveData<List<Product>> mLiveTopRatedProducts;
    private MutableLiveData<List<Product>> mLivePopularProducts;
    private MutableLiveData<List<Category>> mLiveCategories;
    private MutableLiveData<List<Product>> mLiveSearchResult;
    private MutableLiveData<Product> mLiveProduct;

    private int mTotalProducts;
    private int mTotalCategories;
    private int mTotalCategoryBaseProducts;
    private int mTotalSearchResult;

    private List<Integer> mSelectedProducts;
    private List<Integer> mFavoriteProducts;
    private ModelGetter mModelGetter;

    private ModelRepository() {
        mSelectedProducts = new ArrayList<>();
        mFavoriteProducts = new ArrayList<>();
        mLiveProducts = new MutableLiveData<>();
        mLiveCategories = new MutableLiveData<>();
        mLiveRecentProducts = new MutableLiveData<>();
        mLiveTopRatedProducts = new MutableLiveData<>();
        mLivePopularProducts = new MutableLiveData<>();
        mLiveSearchResult = new MutableLiveData<>();
        mLiveProduct = new MutableLiveData<>();
        mModelGetter = new ModelGetter(this);
    }

    public static ModelRepository getInstance() {
        if (mInstance == null)
            mInstance = new ModelRepository();
        return mInstance;
    }

    /**
     * PRODUCTS
     *
     * @param page
     */
    public void getAllProducts(int page) {
        mModelGetter.getProductsFromNet(page);
    }

    public int getTotalProducts() {
        return mTotalProducts;
    }

    public MutableLiveData<List<Product>> getLiveProducts() {
        return mLiveProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.mTotalProducts = totalProducts;
    }

    /**
     * ALL CATEGORIES
     *
     * @return
     */

    public MutableLiveData<List<Category>> getLiveCategories() {
        return mLiveCategories;
    }

    public void setTotalCategories(int totalCategories) {
        this.mTotalCategories = totalCategories;
    }

    public int getTotalCategories() {
        return mTotalCategories;
    }

    public boolean getCategories(int page) {
        if (page > 1)
            if (mLiveCategories.getValue().size() == mTotalCategories)
                return false;
        mModelGetter.getCategoriesFromNet(page);
        return true;
    }

    /**
     * RECENT
     *
     * @return
     */

    public MutableLiveData<List<Product>> getLiveRecentProducts() {
        return mLiveRecentProducts;
    }

    public boolean getRecentProducts(int page) {
        if (page > 1)
            if (mLiveRecentProducts.getValue().size() == mTotalProducts)
                return false;
        mModelGetter.getRecentProductsFromNet(page);
        return true;
    }

    /**
     * TOP RATED
     *
     * @return
     */

    public MutableLiveData<List<Product>> getLiveTopRatedProducts() {
        return mLiveTopRatedProducts;
    }

    public boolean getTopRatedProducts(int page) {
        if (page > 1)
            if (mLiveTopRatedProducts.getValue().size() == mTotalProducts)
                return false;
        mModelGetter.getTopRatedProductsFromNet(page);
        return true;
    }


    /**
     * POPULAR
     *
     * @return
     */

    public MutableLiveData<List<Product>> getLivePopularProducts() {
        return mLivePopularProducts;
    }

    public boolean getPopularProducts(int page) {
        if (page > 1)
            if (mLivePopularProducts.getValue().size() == mTotalProducts)
                return false;
        mModelGetter.getPopularProductsFromNet(page);
        return true;
    }

    /**
     * PRODUCTS OF A CATEGORY
     *
     * @return
     */

    public boolean getCategoryBaseProducts(int page, int categoryId) {

        if(page==1)
            mLiveProducts.setValue(new ArrayList<>());

        if (page > 1)
            if (mLiveProducts.getValue().size() == mTotalCategoryBaseProducts)
                return false;

        mModelGetter.getProductsOfCategoryFromNet(page, categoryId);
        return true;
    }

    public int getTotalCategoryBaseProducts() {
        return mTotalCategoryBaseProducts;
    }

    public void setTotalCategoryBaseProducts(int totalSize) {
        this.mTotalCategoryBaseProducts = totalSize;
    }

    /**
     * SINGLE PRODUCT
     * @param id
     */
    public void getProduct(int id) {
        mModelGetter.getSingleProductFromNet(id);
    }

    public MutableLiveData<Product> getLiveProduct() {
        return mLiveProduct;
    }



    /**
     * SEARCH RESULT
     *
     * @return
     */

    public void searchProducts(String name) {
        mModelGetter.searchProducts(name);
    }

    public MutableLiveData<List<Product>> getLiveSearchResult() {
        return mLiveSearchResult;
    }

    public int getTotalSearchResult() {
        return mTotalSearchResult;
    }

    public void setTotalSearchResult(int totalSearchResult) {
        this.mTotalSearchResult = totalSearchResult;
    }

    /**
     * SHOPPING CART
     *
     * @param productId
     */

    public void addToShoppingCart(Integer productId) {
        mSelectedProducts.add(productId);
    }

    public void deleteFromShoppingCart(Integer productId) {
        mSelectedProducts.remove(productId);
    }

    public List<Integer> getSelectedProducts() {
        return mSelectedProducts;
    }

    /**
     * SIGN PRODUCTS
     *
     * @param productId
     */

    public void addToFavorite(Integer productId) {
        mFavoriteProducts.add(productId);
    }

    public void deleteFromFavorite(Integer productId) {
        mFavoriteProducts.remove(productId);
    }

    public List<Integer> getFavoriteProducts() {
        return mFavoriteProducts;
    }

}
