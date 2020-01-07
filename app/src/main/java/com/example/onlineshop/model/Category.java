package com.example.onlineshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("slug")
    private String mSlug;

    @SerializedName("image")
    private ImagesItem mImages;

    @SerializedName("count")
    private Integer mCount;

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmSlug() {
        return mSlug;
    }

    public ImagesItem getmImages() {
        return mImages;
    }

    public Integer getmCount() {
        return mCount;
    }
}
