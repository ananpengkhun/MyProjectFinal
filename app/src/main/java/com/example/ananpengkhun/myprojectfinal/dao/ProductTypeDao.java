package com.example.ananpengkhun.myprojectfinal.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class ProductTypeDao  implements Parcelable{
    private String prodTypeName;
    private String prodTypeCode;
    private String prodTypeDes;

    public ProductTypeDao() {
    }

    protected ProductTypeDao(Parcel in) {
        prodTypeName = in.readString();
        prodTypeCode = in.readString();
        prodTypeDes = in.readString();
    }

    public static final Creator<ProductTypeDao> CREATOR = new Creator<ProductTypeDao>() {
        @Override
        public ProductTypeDao createFromParcel(Parcel in) {
            return new ProductTypeDao(in);
        }

        @Override
        public ProductTypeDao[] newArray(int size) {
            return new ProductTypeDao[size];
        }
    };

    public String getProdTypeName() {
        return prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    public String getProdTypeCode() {
        return prodTypeCode;
    }

    public void setProdTypeCode(String prodTypeCode) {
        this.prodTypeCode = prodTypeCode;
    }

    public String getProdTypeDes() {
        return prodTypeDes;
    }

    public void setProdTypeDes(String prodTypeDes) {
        this.prodTypeDes = prodTypeDes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(prodTypeName);
        parcel.writeString(prodTypeCode);
        parcel.writeString(prodTypeDes);
    }
}
