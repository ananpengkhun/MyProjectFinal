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
    private String price;
    private int prodAmount;
    private String prodUnit;
    private List<ProductTypeDao> prodType;
    private List<ProviderDao> prodProvider;
    private int prodAlert;

    public ProductDao() {
    }

    protected ProductDao(Parcel in) {
        prodCode = in.readString();
        prodName = in.readString();
        price = in.readString();
        prodAmount = in.readInt();
        prodUnit = in.readString();
        prodType = in.createTypedArrayList(ProductTypeDao.CREATOR);
        prodProvider = in.createTypedArrayList(ProviderDao.CREATOR);
        prodAlert = in.readInt();
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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public int getProdAmount() {
        return prodAmount;
    }

    public void setProdAmount(int prodAmount) {
        this.prodAmount = prodAmount;
    }

    public String getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public List<ProductTypeDao> getProdType() {
        return prodType;
    }

    public void setProdType(List<ProductTypeDao> prodType) {
        this.prodType = prodType;
    }

    public List<ProviderDao> getProdProvider() {
        return prodProvider;
    }

    public void setProdProvider(List<ProviderDao> prodProvider) {
        this.prodProvider = prodProvider;
    }

    public int getProdAlert() {
        return prodAlert;
    }

    public void setProdAlert(int prodAlert) {
        this.prodAlert = prodAlert;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(prodCode);
        parcel.writeString(prodName);
        parcel.writeString(price);
        parcel.writeInt(prodAmount);
        parcel.writeString(prodUnit);
        parcel.writeTypedList(prodType);
        parcel.writeTypedList(prodProvider);
        parcel.writeInt(prodAlert);
    }
}
