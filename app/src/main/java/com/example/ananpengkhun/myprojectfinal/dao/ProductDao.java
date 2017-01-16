package com.example.ananpengkhun.myprojectfinal.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class ProductDao implements Parcelable{
    private int prodInType;
    private int prodId;
    private int productQuantity;
    private String prodCode;
    private String prodName;
    private int providerId;
    private int productPrice;
    private int productAlert;
    private String productImg;
    private List<ProductEachSize> productEachSizes;

    public ProductDao() {
    }

    protected ProductDao(Parcel in) {
        prodInType = in.readInt();
        prodId = in.readInt();
        productQuantity = in.readInt();
        prodCode = in.readString();
        prodName = in.readString();
        providerId = in.readInt();
        productPrice = in.readInt();
        productAlert = in.readInt();
        productImg = in.readString();
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

    public int getProductAlert() {
        return productAlert;
    }

    public void setProductAlert(int productAlert) {
        this.productAlert = productAlert;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getProdInType() {
        return prodInType;
    }

    public void setProdInType(int prodInType) {
        this.prodInType = prodInType;
    }

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
        parcel.writeInt(prodInType);
        parcel.writeInt(prodId);
        parcel.writeInt(productQuantity);
        parcel.writeString(prodCode);
        parcel.writeString(prodName);
        parcel.writeInt(providerId);
        parcel.writeInt(productPrice);
        parcel.writeInt(productAlert);
        parcel.writeString(productImg);
        parcel.writeTypedList(productEachSizes);
    }
}
