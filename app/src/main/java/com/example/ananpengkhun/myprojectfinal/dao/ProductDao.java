package com.example.ananpengkhun.myprojectfinal.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class ProductDao implements Parcelable{
    private String prodCode;
    private String prodName;
    private int providerId;
    private String productImg;
    private List<ProductEachSize> productEachSizes;

    public ProductDao() {
    }

    protected ProductDao(Parcel in) {
        prodCode = in.readString();
        prodName = in.readString();
        productImg = in.readString();
        providerId = in.readInt();
        productEachSizes = in.createTypedArrayList(ProductEachSize.CREATOR);
    }

    public static final Creator<ProductDao> CREATOR = new Creator<ProductDao>() {
        @Override
        public ProductDao createFromParcel(Parcel in) {
            return new ProductDao(in);
        }

        @Override
        public ProductDao[] newArray(int size) {
            return new ProductDao[size];
        }
    };

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }


    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public List<ProductEachSize> getProductEachSizes() {
        return productEachSizes;
    }

    public void setProductEachSizes(List<ProductEachSize> productEachSizes) {
        this.productEachSizes = productEachSizes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(prodCode);
        parcel.writeString(prodName);
        parcel.writeString(productImg);
        parcel.writeInt(providerId);
        parcel.writeTypedList(productEachSizes);
    }
}
