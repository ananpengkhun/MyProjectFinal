package com.example.ananpengkhun.myprojectfinal.dao;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class ProductDao {
    private String prodCode;
    private String prodName;
    private String price;
    private int prodAmount;
    private String prodUnit;
    private String prodType;
    private String prodProvider;
    private int prodAlert;


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

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdProvider() {
        return prodProvider;
    }

    public void setProdProvider(String prodProvider) {
        this.prodProvider = prodProvider;
    }

    public int getProdAlert() {
        return prodAlert;
    }

    public void setProdAlert(int prodAlert) {
        this.prodAlert = prodAlert;
    }
}
