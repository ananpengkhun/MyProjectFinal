package com.example.ananpengkhun.myprojectfinal.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ananpengkhun on 1/3/17.
 */

public class ProductEachSize implements Parcelable{

    /**
     * amongPerWrap : 25
     * contrainUPiecePerBox : -
     * diameterOutsize : -
     * effordUBaht : 1.00
     * longPerWrap : -
     * nameItemId : 1
     * nameItemSize : 18 (1/2)
     * priceUBaht : {"classEightFive ":"42.00","classFive":"-","classOne":"-","classOneThreeFive":"53.00","classThree":"-","classTwo ":"-","perKilo":"-","perMeter":"-","perPiece":"-","perWrap":"-"}
     * totalItemBigUnit : 10
     * unit : นิ้ว
     * weightPerWrap : -
     */

    private String amongPerWrap;
    private String contrainUPiecePerBox;
    private String diameterOutsize;
    private String effordUBaht;
    private String longPerWrap;
    private int nameItemId;
    private String nameItemSize;
    private PriceUBahtBean priceUBaht;
    private String totalItemBigUnit;
    private String unit;
    private String weightPerWrap;
    private int productSizeAlert;
    private int indexInProduct;

    public ProductEachSize() {
    }


    protected ProductEachSize(Parcel in) {
        amongPerWrap = in.readString();
        contrainUPiecePerBox = in.readString();
        diameterOutsize = in.readString();
        effordUBaht = in.readString();
        longPerWrap = in.readString();
        nameItemId = in.readInt();
        nameItemSize = in.readString();
        priceUBaht = in.readParcelable(PriceUBahtBean.class.getClassLoader());
        totalItemBigUnit = in.readString();
        unit = in.readString();
        weightPerWrap = in.readString();
        productSizeAlert = in.readInt();
        indexInProduct = in.readInt();
    }

    public static final Creator<ProductEachSize> CREATOR = new Creator<ProductEachSize>() {
        @Override
        public ProductEachSize createFromParcel(Parcel in) {
            return new ProductEachSize(in);
        }

        @Override
        public ProductEachSize[] newArray(int size) {
            return new ProductEachSize[size];
        }
    };

    public int getIndexInProduct() {
        return indexInProduct;
    }

    public void setIndexInProduct(int indexInProduct) {
        this.indexInProduct = indexInProduct;
    }

    public int getProductSizeAlert() {
        return productSizeAlert;
    }

    public void setProductSizeAlert(int productSizeAlert) {
        this.productSizeAlert = productSizeAlert;
    }

    public String getAmongPerWrap() {
        return amongPerWrap;
    }

    public void setAmongPerWrap(String amongPerWrap) {
        this.amongPerWrap = amongPerWrap;
    }

    public String getContrainUPiecePerBox() {
        return contrainUPiecePerBox;
    }

    public void setContrainUPiecePerBox(String contrainUPiecePerBox) {
        this.contrainUPiecePerBox = contrainUPiecePerBox;
    }

    public String getDiameterOutsize() {
        return diameterOutsize;
    }

    public void setDiameterOutsize(String diameterOutsize) {
        this.diameterOutsize = diameterOutsize;
    }

    public String getEffordUBaht() {
        return effordUBaht;
    }

    public void setEffordUBaht(String effordUBaht) {
        this.effordUBaht = effordUBaht;
    }

    public String getLongPerWrap() {
        return longPerWrap;
    }

    public void setLongPerWrap(String longPerWrap) {
        this.longPerWrap = longPerWrap;
    }

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

    public PriceUBahtBean getPriceUBaht() {
        return priceUBaht;
    }

    public void setPriceUBaht(PriceUBahtBean priceUBaht) {
        this.priceUBaht = priceUBaht;
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

    public String getWeightPerWrap() {
        return weightPerWrap;
    }

    public void setWeightPerWrap(String weightPerWrap) {
        this.weightPerWrap = weightPerWrap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(amongPerWrap);
        parcel.writeString(contrainUPiecePerBox);
        parcel.writeString(diameterOutsize);
        parcel.writeString(effordUBaht);
        parcel.writeString(longPerWrap);
        parcel.writeInt(nameItemId);
        parcel.writeString(nameItemSize);
        parcel.writeParcelable(priceUBaht, i);
        parcel.writeString(totalItemBigUnit);
        parcel.writeString(unit);
        parcel.writeString(weightPerWrap);
        parcel.writeInt(productSizeAlert);
        parcel.writeInt(indexInProduct);
    }


    public static class PriceUBahtBean implements Parcelable{
        /**
         * classEightFive  : 42.00
         * classFive : -
         * classOne : -
         * classOneThreeFive : 53.00
         * classThree : -
         * classTwo  : -
         * perKilo : -
         * perMeter : -
         * perPiece : -
         * perWrap : -
         */

        private String classEightFive;
        private String classFive;
        private String classOne;
        private String classOneThreeFive;
        private String classThree;
        private String classTwo;
        private String perKilo;
        private String perMeter;
        private String perPiece;
        private String perWrap;

        public PriceUBahtBean() {
        }

        protected PriceUBahtBean(Parcel in) {
            classEightFive = in.readString();
            classFive = in.readString();
            classOne = in.readString();
            classOneThreeFive = in.readString();
            classThree = in.readString();
            classTwo = in.readString();
            perKilo = in.readString();
            perMeter = in.readString();
            perPiece = in.readString();
            perWrap = in.readString();
        }

        public static final Creator<PriceUBahtBean> CREATOR = new Creator<PriceUBahtBean>() {
            @Override
            public PriceUBahtBean createFromParcel(Parcel in) {
                return new PriceUBahtBean(in);
            }

            @Override
            public PriceUBahtBean[] newArray(int size) {
                return new PriceUBahtBean[size];
            }
        };

        public String getClassEightFive() {
            return classEightFive;
        }

        public void setClassEightFive(String classEightFive) {
            this.classEightFive = classEightFive;
        }

        public String getClassFive() {
            return classFive;
        }

        public void setClassFive(String classFive) {
            this.classFive = classFive;
        }

        public String getClassOne() {
            return classOne;
        }

        public void setClassOne(String classOne) {
            this.classOne = classOne;
        }

        public String getClassOneThreeFive() {
            return classOneThreeFive;
        }

        public void setClassOneThreeFive(String classOneThreeFive) {
            this.classOneThreeFive = classOneThreeFive;
        }

        public String getClassThree() {
            return classThree;
        }

        public void setClassThree(String classThree) {
            this.classThree = classThree;
        }

        public String getClassTwo() {
            return classTwo;
        }

        public void setClassTwo(String classTwo) {
            this.classTwo = classTwo;
        }

        public String getPerKilo() {
            return perKilo;
        }

        public void setPerKilo(String perKilo) {
            this.perKilo = perKilo;
        }

        public String getPerMeter() {
            return perMeter;
        }

        public void setPerMeter(String perMeter) {
            this.perMeter = perMeter;
        }

        public String getPerPiece() {
            return perPiece;
        }

        public void setPerPiece(String perPiece) {
            this.perPiece = perPiece;
        }

        public String getPerWrap() {
            return perWrap;
        }

        public void setPerWrap(String perWrap) {
            this.perWrap = perWrap;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(classEightFive);
            parcel.writeString(classFive);
            parcel.writeString(classOne);
            parcel.writeString(classOneThreeFive);
            parcel.writeString(classThree);
            parcel.writeString(classTwo);
            parcel.writeString(perKilo);
            parcel.writeString(perMeter);
            parcel.writeString(perPiece);
            parcel.writeString(perWrap);
        }
    }
}
