package com.example.ananpengkhun.myprojectfinal.dao;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.RealmModule;

/**
 * Created by ananpengkhun on 3/6/17.
 */

public class ReportDao extends RealmObject {
    private String prodNameRep;
    private int prodQuantityRep;
    private int productIdRep;
    private int productSizeIdRep;

    @Index
    private String date;

    public int getProductIdRep() {
        return productIdRep;
    }

    public void setProductIdRep(int productIdRep) {
        this.productIdRep = productIdRep;
    }

    public int getProductSizeIdRep() {
        return productSizeIdRep;
    }

    public void setProductSizeIdRep(int productSizeIdRep) {
        this.productSizeIdRep = productSizeIdRep;
    }

    public String getProdNameRep() {
        return prodNameRep;
    }

    public void setProdNameRep(String prodNameRep) {
        this.prodNameRep = prodNameRep;
    }

    public int getProdQuantityRep() {
        return prodQuantityRep;
    }

    public void setProdQuantityRep(int prodQuantityRep) {
        this.prodQuantityRep = prodQuantityRep;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
