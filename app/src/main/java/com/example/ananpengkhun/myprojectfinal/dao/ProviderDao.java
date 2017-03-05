package com.example.ananpengkhun.myprojectfinal.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class ProviderDao implements Parcelable{
    private int provId;
    private String provName;
    private String provAddress;
    private String provPhone;
    private String provEmail;
    private String provImg;

    public ProviderDao() {
    }

    protected ProviderDao(Parcel in) {
        provId = in.readInt();
        provName = in.readString();
        provAddress = in.readString();
        provPhone = in.readString();
        provEmail = in.readString();
        provImg = in.readString();
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

    public String getProvImg() {
        return provImg;
    }

    public void setProvImg(String provImg) {
        this.provImg = provImg;
    }

    public int getProvId() {
        return provId;
    }

    public void setProvId(int provId) {
        this.provId = provId;
    }

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
        parcel.writeInt(provId);
        parcel.writeString(provName);
        parcel.writeString(provAddress);
        parcel.writeString(provPhone);
        parcel.writeString(provEmail);
        parcel.writeString(provImg);
    }
}
