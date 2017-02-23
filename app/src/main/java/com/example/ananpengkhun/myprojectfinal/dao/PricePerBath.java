package com.example.ananpengkhun.myprojectfinal.dao;

import io.realm.RealmObject;

/**
 * Created by ananpengkhun on 2/4/17.
 */

public class PricePerBath extends RealmObject {
    private String perMeter;
    private String perWrap;
    private String perKilo;
    private String perPiece;
    private String classOne;
    private String classTwo;
    private String classThree;
    private String classFive;
    private String classEightFive;
    private String classOneThreeFive;

    public String getPerMeter() {
        return perMeter;
    }

    public void setPerMeter(String perMeter) {
        this.perMeter = perMeter;
    }

    public String getPerWrap() {
        return perWrap;
    }

    public void setPerWrap(String perWrap) {
        this.perWrap = perWrap;
    }

    public String getPerKilo() {
        return perKilo;
    }

    public void setPerKilo(String perKilo) {
        this.perKilo = perKilo;
    }

    public String getPerPiece() {
        return perPiece;
    }

    public void setPerPiece(String perPiece) {
        this.perPiece = perPiece;
    }

    public String getClassOne() {
        return classOne;
    }

    public void setClassOne(String classOne) {
        this.classOne = classOne;
    }

    public String getClassTwo() {
        return classTwo;
    }

    public void setClassTwo(String classTwo) {
        this.classTwo = classTwo;
    }

    public String getClassThree() {
        return classThree;
    }

    public void setClassThree(String classThree) {
        this.classThree = classThree;
    }

    public String getClassFive() {
        return classFive;
    }

    public void setClassFive(String classFive) {
        this.classFive = classFive;
    }

    public String getClassEightFive() {
        return classEightFive;
    }

    public void setClassEightFive(String classEightFive) {
        this.classEightFive = classEightFive;
    }

    public String getClassOneThreeFive() {
        return classOneThreeFive;
    }

    public void setClassOneThreeFive(String classOneThreeFive) {
        this.classOneThreeFive = classOneThreeFive;
    }
}
