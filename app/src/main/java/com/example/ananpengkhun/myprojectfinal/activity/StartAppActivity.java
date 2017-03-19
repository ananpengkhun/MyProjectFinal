package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.PricePerBath;
import com.example.ananpengkhun.myprojectfinal.dao.Product;
import com.example.ananpengkhun.myprojectfinal.dao.Productsize;
import com.example.ananpengkhun.myprojectfinal.dao.TestProductType;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class StartAppActivity extends AppCompatActivity {


    @BindView(R.id.progress) ProgressBar progress;
    @BindView(R.id.activity_start_app) RelativeLayout activityStartApp;
    private Realm realm;
    //private Realm realmReport;
    private RealmAsyncTask realmAsyncTask;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static final String MyPreference = "ProdType_index";
    private DataDao dataDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        ButterKnife.bind(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("default.realm")
                .build();
//        RealmConfiguration realmConfigurationReport = new RealmConfiguration.Builder()
//                .name("report.realm")
//                .build();

        //realmReport = Realm.getInstance(realmConfigurationReport);
        realm = Realm.getInstance(realmConfiguration);
        Log.d("start", "onCreate realm: " + realm.getPath());
        //Log.d("start", "onCreate realmReport: " + realmReport.getPath());

//        realmReport.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                for (int i = 0; i < 3; i++) {
//                    ReportDao reportDao = realm.createObject(ReportDao.class);
//                    reportDao.setProdNameRep("name:" + i);
//                    reportDao.setProdQuantityRep(i);
//                }
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//
//                RealmResults<ReportDao> results = realmReport.where(ReportDao.class).findAll();
//                results.load();
//                for (int i = 0; i < results.size(); i++) {
//                    Log.d("realmReport", "onSuccess: " + results.get(i).getProdNameRep() + " ---- " + results.get(i).getProdQuantityRep());
//                }
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                Log.d("realmReport", "onError realmReport: " + error.getMessage());
//            }
//        });


        Intent intent = getIntent();
        if (intent.getIntExtra("refresh", -1) == 2328) {
            // refresh data

        } else {
            if (intent.getParcelableExtra("data") != null) {
                dataDao = intent.getParcelableExtra("data");
                Log.d("start", "onCreate: " + dataDao.getProductType().size());
            }
            boolean start;
            sp = getSharedPreferences(MyPreference, MODE_PRIVATE);
            if (!sp.getBoolean("startApp", false)) {
                editor = sp.edit();
                start = sp.getBoolean("startApp", false);
                editor.putBoolean("startApp", true);
                editor.apply();
            } else {
                start = sp.getBoolean("startApp", false);
            }

            Log.d("empty", "run: " + start);

            if (!start) {
                realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        for (int i = 0; i < dataDao.getProductType().size(); i++) {

                            TestProductType testProductType = realm.createObject(TestProductType.class);
                            testProductType.setTypeId(dataDao.getProductType().get(i).getTypeId());
                            testProductType.setName(dataDao.getProductType().get(i).getName());
                            testProductType.setTypeCode(dataDao.getProductType().get(i).getTypeCode());
                            testProductType.setStatus(dataDao.getProductType().get(i).getStatus());
                            testProductType.setTypeDes(dataDao.getProductType().get(i).getTypeDes());
                            if (dataDao.getProductType().get(i).getData() != null) {
                                for (int j = 0; j < dataDao.getProductType().get(i).getData().size(); j++) {
                                    //Product product = realm.copyToRealm(dataDao.getProductType().get(i).getData().get(j));
                                    Product product = new Product();
                                    product.setNameCode(dataDao.getProductType().get(i).getData().get(j).getNameCode());
                                    product.setNameItem(dataDao.getProductType().get(i).getData().get(j).getNameItem());
                                    product.setProvider(dataDao.getProductType().get(i).getData().get(j).getProvider());
                                    product.setProductImg(dataDao.getProductType().get(i).getData().get(j).getProductImg());
                                    product.setProductInType(dataDao.getProductType().get(i).getData().get(j).getProductInType());
                                    product.setProductId(dataDao.getProductType().get(i).getData().get(j).getProductId());
                                    product.setProductQuantity(dataDao.getProductType().get(i).getData().get(j).getProductQuantity());
                                    product.setProductPrice(dataDao.getProductType().get(i).getData().get(j).getProductPrice());
                                    product.setProductAlert(dataDao.getProductType().get(i).getData().get(j).getProductAlert());
                                    //Log.d("realm", "execute: " + j + dataDao.getProductType().get(i).getData().get(j).getNameItem());
                                    testProductType.getData().add(product);

                                    if (dataDao.getProductType().get(i).getData().get(j).getDataItem() != null) {
                                        for (int z = 0; z < dataDao.getProductType().get(i).getData().get(j).getDataItem().size(); z++) {

                                            //productDao.setProductEachSizes();
                                            DataDao.ProductTypeBean.DataBean list = dataDao.getProductType().get(i).getData().get(j);
                                            Productsize productEachSize = new Productsize();
                                            //Log.d("realm", "execute: "+dataDao.getProductType().get(i).getData().get(j).getDataItem().get(z).getNameItemId());
                                            productEachSize.setAmongPerWrap(list.getDataItem().get(z).getAmongPerWrap());
                                            productEachSize.setContrainUPiecePerBox(list.getDataItem().get(z).getContrainUPiecePerBox());
                                            productEachSize.setDiameterOutsize(list.getDataItem().get(z).getDiameterOutsize());
                                            productEachSize.setEffordUBaht(list.getDataItem().get(z).getEffordUBaht());
                                            productEachSize.setLongPerWrap(list.getDataItem().get(z).getLongPerWrap());
                                            productEachSize.setNameItemId(list.getDataItem().get(z).getNameItemId());
                                            productEachSize.setNameItemSize(list.getDataItem().get(z).getNameItemSize());
                                            productEachSize.setTotalItemBigUnit(list.getDataItem().get(z).getTotalItemBigUnit());
                                            productEachSize.setUnit(list.getDataItem().get(z).getUnit());
                                            productEachSize.setWeightPerWrap(list.getDataItem().get(z).getWeightPerWrap());

                                            Log.d("prosizealert", "execute: " + list.getDataItem().get(z).getProductSizeAlert());
                                            productEachSize.setProductSizeAlert(list.getDataItem().get(z).getProductSizeAlert());
                                            productEachSize.setIndexInProduct(list.getDataItem().get(z).getIndexInProduct());

                                            //product.getDataItem().add(productEachSize);

                                            PricePerBath pricePerBath = new PricePerBath();
                                            pricePerBath.setClassEightFive(list.getDataItem().get(z).getPriceUBaht().getClassEightFive());
                                            pricePerBath.setClassFive(list.getDataItem().get(z).getPriceUBaht().getClassFive());
                                            pricePerBath.setClassOne(list.getDataItem().get(z).getPriceUBaht().getClassOne());

                                            pricePerBath.setClassOneThreeFive(list.getDataItem().get(z).getPriceUBaht().getClassOneThreeFive());
                                            pricePerBath.setClassThree(list.getDataItem().get(z).getPriceUBaht().getClassThree());
                                            pricePerBath.setClassTwo(list.getDataItem().get(z).getPriceUBaht().getClassTwo());
                                            //Log.d(TAG, "init: "+list.getDataItem().get(z).getPriceUBaht().getClassTwo());
                                            pricePerBath.setPerKilo(list.getDataItem().get(z).getPriceUBaht().getPerKilo());
                                            pricePerBath.setPerMeter(list.getDataItem().get(z).getPriceUBaht().getPerMeter());
                                            pricePerBath.setPerPiece(list.getDataItem().get(z).getPriceUBaht().getPerPiece());
                                            pricePerBath.setPerWrap(list.getDataItem().get(z).getPriceUBaht().getPerWrap());
                                            productEachSize.setPricePerBath(pricePerBath);


                                            testProductType.getData().get(j).getDataItem().add(productEachSize);
                                            //productEachSizes.add(productEachSize);

                                        }
                                        //productDao.setProductEachSizes(productEachSizes);
                                    }


                                }

                            }
                        }
                    }

                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        RealmQuery<TestProductType> query = realm.where(TestProductType.class);

                        RealmResults<TestProductType> results = query.findAllAsync();
                        results.load();

                        Log.d("realm", "readAllRealmResult: " + results.size());
                        Toast.makeText(StartAppActivity.this, "Insert Data Success . ", Toast.LENGTH_LONG).show();

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Log.d("realm", "onError: " + error.toString());
                        Toast.makeText(StartAppActivity.this, "Insert Data Fail !!! . ", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        //testProductTypes = new ArrayList<>();
        // productTypeDaos = new ArrayList<>();
//

//        mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/productType");
//        valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
////                    TestProductType providerDao = new TestProductType();
////                    providerDao.setName(snapshot.getValue(TestProductType.class).getName());
////                    providerDao.setStatus(snapshot.getValue(TestProductType.class).getStatus());
////                    providerDao.setTypeCode(snapshot.getValue(TestProductType.class).getTypeCode());
////                    providerDao.setTypeDes(snapshot.getValue(TestProductType.class).getTypeDes());
////                    providerDao.setTypeId(snapshot.getValue(TestProductType.class).getTypeId());
//
//
//                    //Log.d("start", "onDataChange: "+providerDao.getName());
//                    dataBeen = new ArrayList<>();
//                    DataDao.ProductTypeBean providerDao = new DataDao.ProductTypeBean();
//                    providerDao.setName(snapshot.getValue(DataDao.ProductTypeBean.class).getName());
//                    providerDao.setStatus(snapshot.getValue(DataDao.ProductTypeBean.class).getStatus());
//                    providerDao.setTypeCode(snapshot.getValue(DataDao.ProductTypeBean.class).getTypeCode());
//                    providerDao.setTypeDes(snapshot.getValue(DataDao.ProductTypeBean.class).getTypeDes());
//                    providerDao.setTypeId(snapshot.getValue(DataDao.ProductTypeBean.class).getTypeId());
//
//                    //Log.d("start", "onDataChange: "+snapshot.child("data").toString());
//                    for(DataSnapshot snapshot1 : snapshot.child("data").getChildren()){
//                        //Log.d("start", "onDataChange: "+snapshot1.child("data").toString());
//                        DataDao.ProductTypeBean.DataBean dataBean = new DataDao.ProductTypeBean.DataBean();
//                        dataBean.setNameCode(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getNameCode());
//                        dataBean.setNameItem(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getNameItem());
//                        dataBean.setProductAlert(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductAlert());
//                        dataBean.setProductId(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductId());
//                        dataBean.setProductPrice(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductPrice());
//                        dataBean.setProductQuantity(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductQuantity());
//                        dataBean.setProductUnit(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductUnit());
//                        dataBean.setProvider(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProvider());
//
//                        if(snapshot1.child("dataItem").getValue() != null) {
//                            dataItemBeen = new ArrayList<>();
//                            for (DataSnapshot snapshot2 : snapshot1.child("dataItem").getChildren()) {
//                                DataDao.ProductTypeBean.DataBean.DataItemBean dataItemBean = new DataDao.ProductTypeBean.DataBean.DataItemBean();
//                                dataItemBean.setAmongPerWrap(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getAmongPerWrap());
//                                dataItemBean.setContrainUPiecePerBox(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getContrainUPiecePerBox());
//                                dataItemBean.setDiameterOutsize(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getDiameterOutsize());
//                                dataItemBean.setEffordUBaht(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getEffordUBaht());
//                                dataItemBean.setLongPerWrap(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getLongPerWrap());
//                                dataItemBean.setNameItemId(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getNameItemId());
//                                dataItemBean.setNameItemSize(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getNameItemSize());
//                                dataItemBean.setTotalItemBigUnit(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getTotalItemBigUnit());
//                                dataItemBean.setUnit(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getUnit());
//                                dataItemBean.setWeightPerWrap(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getWeightPerWrap());
//
//                                 for(DataSnapshot snapshot3 : snapshot2.child("priceUBaht").getChildren()) {
//
//
//
//                                     DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean priceUBahtBean = new DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean();
//                                     priceUBahtBean.setClassEightFive(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassEightFive());
//                                     priceUBahtBean.setClassFive(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassFive());
//                                     priceUBahtBean.setClassOne(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassOne());
//
//                                     Log.d("start", "onDataChange: " + snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassOne());
//                                     priceUBahtBean.setClassOneThreeFive(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassOneThreeFive());
//                                     priceUBahtBean.setClassThree(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassThree());
//                                     priceUBahtBean.setClassTwo(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassTwo());
//                                     priceUBahtBean.setPerKilo(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getPerKilo());
//                                     priceUBahtBean.setPerMeter(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getPerMeter());
//                                     priceUBahtBean.setPerPiece(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getPerPiece());
//                                     priceUBahtBean.setPerWrap(snapshot3.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getPerWrap());
//                                     dataItemBean.setPriceUBaht(priceUBahtBean);
//                                 }
//
//                                dataItemBeen.add(dataItemBean);
//                            }
//                            dataBean.setDataItem(dataItemBeen);
//                        }
//                        dataBeen.add(dataBean);
//                    }
//                    providerDao.setData(dataBeen);
//
//                    //testProductTypes.add(providerDao);
//                    productTypeDaos.add(providerDao);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//
//        mRootRef.addListenerForSingleValueEvent(valueEventListener);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(StartAppActivity.this, MainActivity.class);
                //intent.putExtra("data", dataDao);
                startActivity(intent);
                finish();
            }
        }, 2000);


    }

    @Override
    protected void onStop() {
//        if (realmAsyncTask != null && !realmAsyncTask.isCancelled()) {
//            realmAsyncTask.cancel();
//        }
        super.onStop();
        realm.close();
//        if (valueEventListener != null) {
//            mRootRef.removeEventListener(valueEventListener);
//        }
    }


}
