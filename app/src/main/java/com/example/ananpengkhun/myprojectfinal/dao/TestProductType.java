package com.example.ananpengkhun.myprojectfinal.dao;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmModule;

/**
 * Created by ananpengkhun on 1/9/17.
 */
public class TestProductType extends RealmObject{
    private int typeId;

    private String status;
    private String typeCode;
    private String typeDes;
    private String name;
    private RealmList<Product> data;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDes() {
        return typeDes;
    }

    public void setTypeDes(String typeDes) {
        this.typeDes = typeDes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Product> getData() {
        return data;
    }

    public void setData(RealmList<Product> data) {
        this.data = data;
    }
}
