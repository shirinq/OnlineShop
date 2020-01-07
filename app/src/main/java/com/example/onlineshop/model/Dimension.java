package com.example.onlineshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Dimension implements Parcelable {

    @SerializedName("length")
    private String length;

    @SerializedName("width")
    private String width;

    @SerializedName("height")
    private String height;

    public void setLength(String length){
        this.length = length;
    }

    public String getLength(){
        return length;
    }

    public void setWidth(String width){
        this.width = width;
    }

    public String getWidth(){
        return width;
    }

    public void setHeight(String height){
        this.height = height;
    }

    public String getHeight(){
        return height;
    }

    @Override
    public String toString(){
        return
                "Dimensions{" +
                        "length = '" + length + '\'' +
                        ",width = '" + width + '\'' +
                        ",height = '" + height + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.length);
        dest.writeString(this.width);
        dest.writeString(this.height);
    }

    public Dimension() {
    }

    protected Dimension(Parcel in) {
        this.length = in.readString();
        this.width = in.readString();
        this.height = in.readString();
    }

    public static final Parcelable.Creator<Dimension> CREATOR = new Parcelable.Creator<Dimension>() {
        @Override
        public Dimension createFromParcel(Parcel source) {
            return new Dimension(source);
        }

        @Override
        public Dimension[] newArray(int size) {
            return new Dimension[size];
        }
    };
}
