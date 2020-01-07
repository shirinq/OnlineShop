package com.example.onlineshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product implements Parcelable {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("price")
    private String mPrice;

    @SerializedName("regular_price")
    private String mRegularPrice;

    @SerializedName("average_rating")
    private String avRate;

    @SerializedName("rating_count")
    private long mRatingCount;

    @SerializedName("weight")
    private String weight;

    @SerializedName("sale_price")
    private String  mSalePrice;

    @SerializedName("on_sale")
    private boolean isOnSale;

    @SerializedName("purchasable")
    private boolean mPurchasable;

    @SerializedName("total_sales")
    private int mTotalSales;

    @SerializedName("dimensions")
    private Dimension mDimensions;

    @SerializedName("categories")
    private List<Category> mCategories;

    @SerializedName("tags")
    private List<TagsItem> mTagsItems;

    @SerializedName("images")
    private List<ImagesItem> imageUrl;

    public int getmId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String  getPrice() {
        return mPrice;
    }

    public void setPrice(String  price) {
        this.mPrice = price;
    }

    public String  getRegularPrice() {
        return mRegularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.mRegularPrice = regularPrice;
    }

    public String  getAvRate() {
        return avRate;
    }

    public void setAvRate(String  avRate) {
        this.avRate = avRate;
    }

    public long getRatingCount() {
        return mRatingCount;
    }

    public void setRatingCount(long ratingCount) {
        this.mRatingCount = ratingCount;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSalePrice() {
        return mSalePrice;
    }

    public void setSalePrice(String salePrice) {
        this.mSalePrice = salePrice;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public boolean isPurchasable() {
        return mPurchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.mPurchasable = purchasable;
    }

    public int getTotalSales() {
        return mTotalSales;
    }

    public void setTotalSales(int totalSales) {
        this.mTotalSales = totalSales;
    }

    public Dimension getDimensions() {
        return mDimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.mDimensions = dimensions;
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        this.mCategories = categories;
    }

    public List<TagsItem> getTagsItems() {
        return mTagsItems;
    }

    public void setTagsItems(List<TagsItem> tagsItems) {
        this.mTagsItems = tagsItems;
    }

    public List<ImagesItem> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<ImagesItem> imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
        dest.writeString(this.mDescription);
        dest.writeString(this.mPrice);
        dest.writeString(this.mRegularPrice);
        dest.writeString(this.avRate);
        dest.writeLong(this.mRatingCount);
        dest.writeString(this.weight);
        dest.writeString(this.mSalePrice);
        dest.writeByte(this.isOnSale ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mPurchasable ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mTotalSales);
        dest.writeParcelable(this.mDimensions, flags);
        dest.writeList(this.mCategories);
        dest.writeList(this.mTagsItems);
        dest.writeList(this.imageUrl);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mDescription = in.readString();
        this.mPrice = in.readString();
        this.mRegularPrice = in.readString();
        this.avRate = in.readString();
        this.mRatingCount = in.readLong();
        this.weight = in.readString();
        this.mSalePrice = in.readString();
        this.isOnSale = in.readByte() != 0;
        this.mPurchasable = in.readByte() != 0;
        this.mTotalSales = in.readInt();
        this.mDimensions = in.readParcelable(Dimension.class.getClassLoader());
        this.mCategories = new ArrayList<Category>();
        in.readList(this.mCategories, Category.class.getClassLoader());
        this.mTagsItems = new ArrayList<TagsItem>();
        in.readList(this.mTagsItems, TagsItem.class.getClassLoader());
        this.imageUrl = new ArrayList<ImagesItem>();
        in.readList(this.imageUrl, ImagesItem.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
