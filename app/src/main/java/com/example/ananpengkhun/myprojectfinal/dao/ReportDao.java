package com.example.ananpengkhun.myprojectfinal.dao;

import io.realm.RealmObject;
import io.realm.annotations.RealmModule;

/**
 * Created by ananpengkhun on 3/6/17.
 */

public class ReportDao extends RealmObject {
    private String prodNameRep;
    private int prodQuantityRep;

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
}
