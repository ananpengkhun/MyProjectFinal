package com.example.ananpengkhun.myprojectfinal.dao;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ananpengkhun on 2/4/17.
 */

public class Product extends RealmObject {

    private int productId;
    private int productQuantity;
    private int productPrice;
    private int productInType;
    private String productUnit;
    private int productAlert;
    public String productImg;
    private String nameCode;
    private int provider;
    private String nameItem;
    private RealmList<Productsize> dataItem;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductInType() {
        return productInType;
    }

    public void setProductInType(int productInType) {
        this.productInType = productInType;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public int getProductAlert() {
        return productAlert;
    }

    public void setProductAlert(int productAlert) {
        this.productAlert = productAlert;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public RealmList<Productsize> getDataItem() {
        return dataItem;
    }

    public void setDataItem(RealmList<Productsize> dataItem) {
        this.dataItem = dataItem;
    }
}
