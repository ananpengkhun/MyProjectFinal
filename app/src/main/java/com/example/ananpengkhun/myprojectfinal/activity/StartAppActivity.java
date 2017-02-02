package com.example.ananpengkhun.myprojectfinal.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.TestProductType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class StartAppActivity extends AppCompatActivity {


    private DataDao dataDao;
    private DatabaseReference mRootRef;
    private ValueEventListener valueEventListener;
    private List<DataDao.ProductTypeBean> productTypeDaos;
    private List<DataDao.ProductTypeBean.DataBean> dataBeen;
    private List<DataDao.ProductTypeBean.DataBean.DataItemBean> dataItemBeen;
    //    private List<TestProductType> testProductTypes;
    private Dialog dialog;
    private Realm realm;
    private RealmResults<TestProductType> testProductTypes;
    private RealmAsyncTask realmAsyncTask;
    private TestProductType testProductType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        //testProductTypes = new ArrayList<>();
        // productTypeDaos = new ArrayList<>();
//
        mRootRef = FirebaseDatabase.getInstance().getReference();

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("start", "onDataChange: " + dataSnapshot.toString());

                dataDao = dataSnapshot.getValue(DataDao.class);
            }

            //
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("start", "onCancelled: " + databaseError.getMessage());
            }
        };
        mRootRef.addListenerForSingleValueEvent(valueEventListener);
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
                realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        for (int i = 0; i < dataDao.getProductType().size(); i++) {
                            TestProductType testProductType = realm.createObject(TestProductType.class, dataDao.getProductType().get(i).getTypeId());
                            //testProductType.setTypeId();
                            testProductType.setName(dataDao.getProductType().get(i).getName());
                            testProductType.setTypeCode(dataDao.getProductType().get(i).getTypeCode());
                            testProductType.setStatus(dataDao.getProductType().get(i).getStatus());
                            testProductType.setTypeDes(dataDao.getProductType().get(i).getTypeDes());
                        }
                    }

                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        RealmQuery<TestProductType> query = realm.where(TestProductType.class);

                        RealmResults<TestProductType> results = query.findAllAsync();
                        results.load();

                        Log.d("realm", "readAllRealmResult: "+results.size());
                        Toast.makeText(StartAppActivity.this,"Insert Data Success . ",Toast.LENGTH_LONG).show();

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Log.d("realm", "onError: "+error.toString());
                        Toast.makeText(StartAppActivity.this,"Insert Data Fail !!! . ",Toast.LENGTH_LONG).show();
                    }
                });

                RealmResults<TestProductType> results = realm.where(TestProductType.class).findAllAsync();
                results.load();
                Log.d("menza01", "run: "+results.size());
                Intent intent = new Intent(StartAppActivity.this, MainActivity.class);
                intent.putExtra("data", dataDao);
                startActivity(intent);
                finish();
            }
        }, 2000);


    }

    @Override
    protected void onStop() {
        if (realmAsyncTask != null && !realmAsyncTask.isCancelled()) {
            realmAsyncTask.cancel();
        }
        super.onStop();
//        if (valueEventListener != null) {
//            mRootRef.removeEventListener(valueEventListener);
//        }
    }


}
