package com.example.ananpengkhun.myprojectfinal.dao;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by ananpengkhun on 2/4/17.
 */

public class Productsize extends RealmObject {
    private int nameItemId;
    private String nameItemSize;
    private String diameterOutsize;
    private String weightPerWrap;
    private String longPerWrap;
    private String amongPerWrap;
    private String effordUBaht;
    private String contrainUPiecePerBox;
    private String totalItemBigUnit;
    private String unit;
    private PricePerBath pricePerBath;

    public int getNameItemId() {
        return nameItemId;
    }

    public void setNameItemId(int nameItemId) {
        this.nameItemId = nameItemId;
    }

    public String getNameItemSize() {
        return nameItemSize;
    }

    public void setNameItemSize(String nameItemSize) {
        this.nameItemSize = nameItemSize;
    }

    public String getDiameterOutsize() {
        return diameterOutsize;
    }

    public void setDiameterOutsize(String diameterOutsize) {
        this.diameterOutsize = diameterOutsize;
    }

    public String getWeightPerWrap() {
        return weightPerWrap;
    }

    public void setWeightPerWrap(String weightPerWrap) {
        this.weightPerWrap = weightPerWrap;
    }

    public String getLongPerWrap() {
        return longPerWrap;
    }

    public void setLongPerWrap(String longPerWrap) {
        this.longPerWrap = longPerWrap;
    }

    public String getAmongPerWrap() {
        return amongPerWrap;
    }

    public void setAmongPerWrap(String amongPerWrap) {
        this.amongPerWrap = amongPerWrap;
    }

    public String getEffordUBaht() {
        return effordUBaht;
    }

    public void setEffordUBaht(String effordUBaht) {
        this.effordUBaht = effordUBaht;
    }

    public String getContrainUPiecePerBox() {
        return contrainUPiecePerBox;
    }

    public void setContrainUPiecePerBox(String contrainUPiecePerBox) {
        this.contrainUPiecePerBox = contrainUPiecePerBox;
    }

    public String getTotalItemBigUnit() {
        return totalItemBigUnit;
    }

    public void setTotalItemBigUnit(String totalItemBigUnit) {
        this.totalItemBigUnit = totalItemBigUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public PricePerBath getPricePerBath() {
        return pricePerBath;
    }

    public void setPricePerBath(PricePerBath pricePerBath) {
        this.pricePerBath = pricePerBath;
    }
}
