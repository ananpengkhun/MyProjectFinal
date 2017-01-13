package com.example.ananpengkhun.myprojectfinal.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ananpengkhun on 12/28/16.
 */

public class DataDao implements Parcelable {


    public DataDao() {
    }

    /**
     * _id : 58636d022fcc0188ccad5ff6
     * productType : [{"typeId":1,"status":"success","typeCode":"001","typeDes":"ใช้แล้วจะติดใจ","name":"งานรับแรงดัน","data":[{"productId":1,"nameCode":"A","provider":"ตราช้าง SCG","nameItem":"ท่อประปาชนิดปลายเรียบและบานหัว","dataItem":[{"nameItemId":1,"nameItemSize":"18 (1/2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"1.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"42.00","classOneThreeFive":"53.00"}},{"nameItemId":2,"nameItemSize":"20 (3/4\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"1.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"53.00","classOneThreeFive":"64.00"}},{"nameItemId":3,"nameItemSize":"25 (1\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"2.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"70.00","classOneThreeFive":"101.00"}},{"nameItemId":4,"nameItemSize":"35 (1 1/4\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"10","effordUBaht":"2.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"66.00","classEightFive ":"87.00","classOneThreeFive":"132.00"}},{"nameItemId":5,"nameItemSize":"40 (1 1/2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"10","effordUBaht":"2.50","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"80.00","classEightFive ":"114.00","classOneThreeFive":"170.00"}},{"nameItemId":6,"nameItemSize":"55 (2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"10","effordUBaht":"3.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"120.00","classEightFive ":"180.00","classOneThreeFive":"260.00"}},{"nameItemId":7,"nameItemSize":"65 (2 1/2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"4.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"195.00","classEightFive ":"285.00","classOneThreeFive":"430.00"}},{"nameItemId":8,"nameItemSize":"80 (3\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"5.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"265.00","classEightFive ":"395.00","classOneThreeFive":"600.00"}},{"nameItemId":9,"nameItemSize":"100 (4\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"6.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"425.00","classEightFive ":"640.00","classOneThreeFive":"965.00"}},{"nameItemId":10,"nameItemSize":"125 (5\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"8.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"650.00","classEightFive ":"965.00","classOneThreeFive":"1455.00"}},{"nameItemId":11,"nameItemSize":"150 (6\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"18.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"905.00","classEightFive ":"1355.00","classOneThreeFive":"2050.00"}}]},{"productId":2,"nameCode":"B","provider":"ตราช้าง SCG","nameItem":"ท่อเซาะร่องชนิดปลายเรียบและบานหัว","dataItem":[{"nameItemId":1,"nameItemSize":"18 (1/2)","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"1.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"42.00","classOneThreeFive":"53.00"}}]}]}]
     */


    private List<ProductTypeBean> productType;

    public DataDao(List<ProductTypeBean> productType) {
        this.productType = productType;
    }

    protected DataDao(Parcel in) {
        productType = in.createTypedArrayList(ProductTypeBean.CREATOR);
    }

    public static final Creator<DataDao> CREATOR = new Creator<DataDao>() {
        @Override
        public DataDao createFromParcel(Parcel in) {
            return new DataDao(in);
        }

        @Override
        public DataDao[] newArray(int size) {
            return new DataDao[size];
        }
    };


    public List<ProductTypeBean> getProductType() {
        return productType;
    }

    public void setProductType(List<ProductTypeBean> productType) {
        this.productType = productType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(productType);
    }

    public static class ProductTypeBean implements Parcelable {

        public ProductTypeBean() {
        }

        /**
         * typeId : 1
         * status : success
         * typeCode : 001
         * typeDes : ใช้แล้วจะติดใจ
         * name : งานรับแรงดัน
         * data : [{"productId":1,"nameCode":"A","provider":"ตราช้าง SCG","nameItem":"ท่อประปาชนิดปลายเรียบและบานหัว","dataItem":[{"nameItemId":1,"nameItemSize":"18 (1/2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"1.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"42.00","classOneThreeFive":"53.00"}},{"nameItemId":2,"nameItemSize":"20 (3/4\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"1.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"53.00","classOneThreeFive":"64.00"}},{"nameItemId":3,"nameItemSize":"25 (1\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"2.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"70.00","classOneThreeFive":"101.00"}},{"nameItemId":4,"nameItemSize":"35 (1 1/4\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"10","effordUBaht":"2.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"66.00","classEightFive ":"87.00","classOneThreeFive":"132.00"}},{"nameItemId":5,"nameItemSize":"40 (1 1/2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"10","effordUBaht":"2.50","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"80.00","classEightFive ":"114.00","classOneThreeFive":"170.00"}},{"nameItemId":6,"nameItemSize":"55 (2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"10","effordUBaht":"3.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"120.00","classEightFive ":"180.00","classOneThreeFive":"260.00"}},{"nameItemId":7,"nameItemSize":"65 (2 1/2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"4.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"195.00","classEightFive ":"285.00","classOneThreeFive":"430.00"}},{"nameItemId":8,"nameItemSize":"80 (3\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"5.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"265.00","classEightFive ":"395.00","classOneThreeFive":"600.00"}},{"nameItemId":9,"nameItemSize":"100 (4\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"6.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"425.00","classEightFive ":"640.00","classOneThreeFive":"965.00"}},{"nameItemId":10,"nameItemSize":"125 (5\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"8.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"650.00","classEightFive ":"965.00","classOneThreeFive":"1455.00"}},{"nameItemId":11,"nameItemSize":"150 (6\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"18.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"905.00","classEightFive ":"1355.00","classOneThreeFive":"2050.00"}}]},{"productId":2,"nameCode":"B","provider":"ตราช้าง SCG","nameItem":"ท่อเซาะร่องชนิดปลายเรียบและบานหัว","dataItem":[{"nameItemId":1,"nameItemSize":"18 (1/2)","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"1.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"42.00","classOneThreeFive":"53.00"}}]}]
         */

        private int typeId;
        private String status;
        private String typeCode;
        private String typeDes;
        private String name;
        private List<DataBean> data;

        public ProductTypeBean(int typeId, String status, String typeCode, String typeDes, String name, List<DataBean> data) {
            this.typeId = typeId;
            this.status = status;
            this.typeCode = typeCode;
            this.typeDes = typeDes;
            this.name = name;
            this.data = data;
        }

        protected ProductTypeBean(Parcel in) {
            typeId = in.readInt();
            status = in.readString();
            typeCode = in.readString();
            typeDes = in.readString();
            name = in.readString();
            data = in.createTypedArrayList(DataBean.CREATOR);
        }

        public static final Creator<ProductTypeBean> CREATOR = new Creator<ProductTypeBean>() {
            @Override
            public ProductTypeBean createFromParcel(Parcel in) {
                return new ProductTypeBean(in);
            }

            @Override
            public ProductTypeBean[] newArray(int size) {
                return new ProductTypeBean[size];
            }
        };

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(typeId);
            parcel.writeString(status);
            parcel.writeString(typeCode);
            parcel.writeString(typeDes);
            parcel.writeString(name);
            parcel.writeTypedList(data);
        }

        public static class DataBean implements Parcelable {

            public DataBean() {
            }

            /**
             * productId : 1
             * nameCode : A
             * provider : ตราช้าง SCG
             * nameItem : ท่อประปาชนิดปลายเรียบและบานหัว
             * dataItem : [{"nameItemId":1,"nameItemSize":"18 (1/2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"1.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"42.00","classOneThreeFive":"53.00"}},{"nameItemId":2,"nameItemSize":"20 (3/4\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"1.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"53.00","classOneThreeFive":"64.00"}},{"nameItemId":3,"nameItemSize":"25 (1\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"25","effordUBaht":"2.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"70.00","classOneThreeFive":"101.00"}},{"nameItemId":4,"nameItemSize":"35 (1 1/4\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"10","effordUBaht":"2.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"66.00","classEightFive ":"87.00","classOneThreeFive":"132.00"}},{"nameItemId":5,"nameItemSize":"40 (1 1/2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"10","effordUBaht":"2.50","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"80.00","classEightFive ":"114.00","classOneThreeFive":"170.00"}},{"nameItemId":6,"nameItemSize":"55 (2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"10","effordUBaht":"3.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"120.00","classEightFive ":"180.00","classOneThreeFive":"260.00"}},{"nameItemId":7,"nameItemSize":"65 (2 1/2\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"4.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"195.00","classEightFive ":"285.00","classOneThreeFive":"430.00"}},{"nameItemId":8,"nameItemSize":"80 (3\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"5.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"265.00","classEightFive ":"395.00","classOneThreeFive":"600.00"}},{"nameItemId":9,"nameItemSize":"100 (4\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"6.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"425.00","classEightFive ":"640.00","classOneThreeFive":"965.00"}},{"nameItemId":10,"nameItemSize":"125 (5\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"8.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"650.00","classEightFive ":"965.00","classOneThreeFive":"1455.00"}},{"nameItemId":11,"nameItemSize":"150 (6\")","diameterOutsize":"-","weightPerWrap":"-","longPerWrap":"-","amongPerWrap":"-","effordUBaht":"18.00","contrainUPiecePerBox":"-","totalItemBigUnit":"10","priceUBaht":{"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"905.00","classEightFive ":"1355.00","classOneThreeFive":"2050.00"}}]
             */

            private int productId;
            private int productQuantity;
            private int productPrice;
            private String productUnit;
            private int productAlert;
            public String productImg;
            private String nameCode;
            private int provider;
            private String nameItem;
            private List<DataItemBean> dataItem;


            public DataBean(int productId, int productQuantity, String productImg,int productPrice, String productUnit, int productAlert, String nameCode, int provider, String nameItem, List<DataItemBean> dataItem) {
                this.productId = productId;
                this.productQuantity = productQuantity;
                this.productPrice = productPrice;
                this.productUnit = productUnit;
                this.productAlert = productAlert;
                this.nameCode = nameCode;
                this.provider = provider;
                this.nameItem = nameItem;
                this.dataItem = dataItem;
                this.productImg = productImg;
            }


            protected DataBean(Parcel in) {
                productId = in.readInt();
                productQuantity = in.readInt();
                productPrice = in.readInt();
                productUnit = in.readString();
                productAlert = in.readInt();
                productImg = in.readString();
                nameCode = in.readString();
                provider = in.readInt();
                nameItem = in.readString();
                dataItem = in.createTypedArrayList(DataItemBean.CREATOR);
            }

            public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel in) {
                    return new DataBean(in);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };

            public String getProductImg() {
                return productImg;
            }

            public void setProductImg(String productImg) {
                this.productImg = productImg;
            }

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

            public List<DataItemBean> getDataItem() {
                return dataItem;
            }

            public void setDataItem(List<DataItemBean> dataItem) {
                this.dataItem = dataItem;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(productId);
                parcel.writeInt(productQuantity);
                parcel.writeInt(productPrice);
                parcel.writeString(productUnit);
                parcel.writeInt(productAlert);
                parcel.writeString(productImg);
                parcel.writeString(nameCode);
                parcel.writeInt(provider);
                parcel.writeString(nameItem);
                parcel.writeTypedList(dataItem);
            }


            public static class DataItemBean implements Parcelable {

                public DataItemBean() {
                }

                /**
                 * nameItemId : 1
                 * nameItemSize : 18 (1/2")
                 * diameterOutsize : -
                 * weightPerWrap : -
                 * longPerWrap : -
                 * amongPerWrap : 25
                 * effordUBaht : 1.00
                 * contrainUPiecePerBox : -
                 * totalItemBigUnit : 10
                 * priceUBaht : {"perMeter":"-","perWrap":"-","perKilo":"-","perPiece":"-","classOne":"-","classTwo ":"-","classThree":"-","classFive":"-","classEightFive ":"42.00","classOneThreeFive":"53.00"}
                 */

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
                private PriceUBahtBean priceUBaht;

                public DataItemBean(int nameItemId, String nameItemSize, String diameterOutsize, String weightPerWrap, String longPerWrap, String amongPerWrap, String effordUBaht, String contrainUPiecePerBox, String totalItemBigUnit, String unit, PriceUBahtBean priceUBaht) {
                    this.nameItemId = nameItemId;
                    this.nameItemSize = nameItemSize;
                    this.diameterOutsize = diameterOutsize;
                    this.weightPerWrap = weightPerWrap;
                    this.longPerWrap = longPerWrap;
                    this.amongPerWrap = amongPerWrap;
                    this.effordUBaht = effordUBaht;
                    this.contrainUPiecePerBox = contrainUPiecePerBox;
                    this.totalItemBigUnit = totalItemBigUnit;
                    this.unit = unit;
                    this.priceUBaht = priceUBaht;
                }

                protected DataItemBean(Parcel in) {
                    nameItemId = in.readInt();
                    nameItemSize = in.readString();
                    diameterOutsize = in.readString();
                    weightPerWrap = in.readString();
                    longPerWrap = in.readString();
                    amongPerWrap = in.readString();
                    effordUBaht = in.readString();
                    contrainUPiecePerBox = in.readString();
                    totalItemBigUnit = in.readString();
                    unit = in.readString();
                    priceUBaht = in.readParcelable(PriceUBahtBean.class.getClassLoader());
                }

                public static final Creator<DataItemBean> CREATOR = new Creator<DataItemBean>() {
                    @Override
                    public DataItemBean createFromParcel(Parcel in) {
                        return new DataItemBean(in);
                    }

                    @Override
                    public DataItemBean[] newArray(int size) {
                        return new DataItemBean[size];
                    }
                };

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
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

                public PriceUBahtBean getPriceUBaht() {
                    return priceUBaht;
                }

                public void setPriceUBaht(PriceUBahtBean priceUBaht) {
                    this.priceUBaht = priceUBaht;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeInt(nameItemId);
                    parcel.writeString(nameItemSize);
                    parcel.writeString(diameterOutsize);
                    parcel.writeString(weightPerWrap);
                    parcel.writeString(longPerWrap);
                    parcel.writeString(amongPerWrap);
                    parcel.writeString(effordUBaht);
                    parcel.writeString(contrainUPiecePerBox);
                    parcel.writeString(totalItemBigUnit);
                    parcel.writeString(unit);
                    parcel.writeParcelable(priceUBaht, i);
                }

                public static class PriceUBahtBean implements Parcelable {

                    public PriceUBahtBean() {
                    }

                    /**
                     * perMeter : -
                     * perWrap : -
                     * perKilo : -
                     * perPiece : -
                     * classOne : -
                     * classTwo  : -
                     * classThree : -
                     * classFive : -
                     * classEightFive  : 42.00
                     * classOneThreeFive : 53.00
                     */

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



                    public PriceUBahtBean(String perMeter, String perWrap, String perKilo, String perPiece, String classOne, String classTwo, String classThree, String classFive, String classEightFive, String classOneThreeFive) {
                        this.perMeter = perMeter;
                        this.perWrap = perWrap;
                        this.perKilo = perKilo;
                        this.perPiece = perPiece;
                        this.classOne = classOne;
                        this.classTwo = classTwo;
                        this.classThree = classThree;
                        this.classFive = classFive;
                        this.classEightFive = classEightFive;
                        this.classOneThreeFive = classOneThreeFive;
                    }

                    protected PriceUBahtBean(Parcel in) {
                        perMeter = in.readString();
                        perWrap = in.readString();
                        perKilo = in.readString();
                        perPiece = in.readString();
                        classOne = in.readString();
                        classTwo = in.readString();
                        classThree = in.readString();
                        classFive = in.readString();
                        classEightFive = in.readString();
                        classOneThreeFive = in.readString();
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

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {
                        parcel.writeString(perMeter);
                        parcel.writeString(perWrap);
                        parcel.writeString(perKilo);
                        parcel.writeString(perPiece);
                        parcel.writeString(classOne);
                        parcel.writeString(classTwo);
                        parcel.writeString(classThree);
                        parcel.writeString(classFive);
                        parcel.writeString(classEightFive);
                        parcel.writeString(classOneThreeFive);
                    }
                }
            }
        }
    }
}
