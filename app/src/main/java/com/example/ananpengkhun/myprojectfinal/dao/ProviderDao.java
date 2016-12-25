package com.example.ananpengkhun.myprojectfinal.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class ProviderDao implements Parcelable{

    private String provName;
    private String provAddress;
    private String provPhone;
    private String provEmail;

    public ProviderDao() {
    }

    protected ProviderDao(Parcel in) {
        provName = in.readString();
        provAddress = in.readString();
        provPhone = in.readString();
        provEmail = in.readString();
    }

    public static final Creator<ProviderDao> CREATOR = new Creator<ProviderDao>() {
        @Override
        public ProviderDao createFromParcel(Parcel in) {
            return new ProviderDao(in);
        }

        @Override
        public ProviderDao[] newArray(int size) {
            return new ProviderDao[size];
        }
    };

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getProvAddress() {
        return provAddress;
    }

    public void setProvAddress(String provAddress) {
        this.provAddress = provAddress;
    }

    public String getProvPhone() {
        return provPhone;
    }

    public void setProvPhone(String provPhone) {
        this.provPhone = provPhone;
    }

    public String getProvEmail() {
        return provEmail;
    }

    public void setProvEmail(String provEmail) {
        this.provEmail = provEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(provName);
        parcel.writeString(provAddress);
        parcel.writeString(provPhone);
        parcel.writeString(provEmail);
    }
}
