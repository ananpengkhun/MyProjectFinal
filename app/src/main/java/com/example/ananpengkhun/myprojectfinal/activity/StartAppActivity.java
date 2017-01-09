package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductEachSize;
import com.example.ananpengkhun.myprojectfinal.dao.ProductTypeDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;
import com.example.ananpengkhun.myprojectfinal.dao.TestProductType;
import com.example.ananpengkhun.myprojectfinal.httpmanager.DataHttpManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartAppActivity extends AppCompatActivity {

    @BindView(R.id.tv_countdown) TextView tvCountdown;
    @BindView(R.id.activity_start_app) RelativeLayout activityStartApp;

    private DataDao dataDao;
    private DatabaseReference mRootRef;
    private ValueEventListener valueEventListener;
    private List<DataDao.ProductTypeBean> productTypeDaos;
    private List<DataDao.ProductTypeBean.DataBean> dataBeen;
    private List<DataDao.ProductTypeBean.DataBean.DataItemBean> dataItemBeen;
//    private List<TestProductType> testProductTypes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        ButterKnife.bind(this);

        //testProductTypes = new ArrayList<>();
        productTypeDaos = new ArrayList<>();
//
//        mRootRef = FirebaseDatabase.getInstance().getReference();
//
//        valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                dataDao = dataSnapshot.getValue(DataDao.class);
//                //Log.d("start", "onDataChange: " + dataDao.getProductType().get(0).getData().get(0).getDataItem().get(0).getContrainUPiecePerBox());
//                Intent intent = new Intent(StartAppActivity.this, MainActivity.class);
//                intent.putExtra("data", dataDao);
//                startActivity(intent);
//                finish();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d("start", "onCancelled: "+databaseError.getMessage());
//            }
//        };
//        mRootRef.addValueEventListener(valueEventListener);
        mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/productType");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    TestProductType providerDao = new TestProductType();
//                    providerDao.setName(snapshot.getValue(TestProductType.class).getName());
//                    providerDao.setStatus(snapshot.getValue(TestProductType.class).getStatus());
//                    providerDao.setTypeCode(snapshot.getValue(TestProductType.class).getTypeCode());
//                    providerDao.setTypeDes(snapshot.getValue(TestProductType.class).getTypeDes());
//                    providerDao.setTypeId(snapshot.getValue(TestProductType.class).getTypeId());


                    //Log.d("start", "onDataChange: "+providerDao.getName());
                    dataBeen = new ArrayList<>();
                    DataDao.ProductTypeBean providerDao = new DataDao.ProductTypeBean();
                    providerDao.setName(snapshot.getValue(DataDao.ProductTypeBean.class).getName());
                    providerDao.setStatus(snapshot.getValue(DataDao.ProductTypeBean.class).getStatus());
                    providerDao.setTypeCode(snapshot.getValue(DataDao.ProductTypeBean.class).getTypeCode());
                    providerDao.setTypeDes(snapshot.getValue(DataDao.ProductTypeBean.class).getTypeDes());
                    providerDao.setTypeId(snapshot.getValue(DataDao.ProductTypeBean.class).getTypeId());

                    Log.d("start", "onDataChange: "+snapshot.child("data").toString());
                    for(DataSnapshot snapshot1 : snapshot.child("data").getChildren()){
                        //Log.d("start", "onDataChange: "+snapshot1.child("data").toString());
                        DataDao.ProductTypeBean.DataBean dataBean = new DataDao.ProductTypeBean.DataBean();
                        dataBean.setNameCode(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getNameCode());
                        dataBean.setNameItem(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getNameItem());
                        dataBean.setProductAlert(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductAlert());
                        dataBean.setProductId(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductId());
                        dataBean.setProductPrice(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductPrice());
                        dataBean.setProductQuantity(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductQuantity());
                        dataBean.setProductUnit(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProductUnit());
                        dataBean.setProvider(snapshot1.getValue(DataDao.ProductTypeBean.DataBean.class).getProvider());

                        if(snapshot1.child("dataItem").getValue() != null) {
                            dataItemBeen = new ArrayList<>();
                            for (DataSnapshot snapshot2 : snapshot1.child("dataItem").getChildren()) {
                                DataDao.ProductTypeBean.DataBean.DataItemBean dataItemBean = new DataDao.ProductTypeBean.DataBean.DataItemBean();
                                dataItemBean.setAmongPerWrap(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getAmongPerWrap());
                                dataItemBean.setContrainUPiecePerBox(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getContrainUPiecePerBox());
                                dataItemBean.setDiameterOutsize(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getDiameterOutsize());
                                dataItemBean.setEffordUBaht(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getEffordUBaht());
                                dataItemBean.setLongPerWrap(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getLongPerWrap());
                                dataItemBean.setNameItemId(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getNameItemId());
                                dataItemBean.setNameItemSize(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getNameItemSize());
                                dataItemBean.setTotalItemBigUnit(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getTotalItemBigUnit());
                                dataItemBean.setUnit(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getUnit());
                                dataItemBean.setWeightPerWrap(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.class).getWeightPerWrap());

                                DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean priceUBahtBean = new DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean();
                                priceUBahtBean.setClassEightFive(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassEightFive());
                                priceUBahtBean.setClassFive(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassFive());
                                priceUBahtBean.setClassOne(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassOne());
                                priceUBahtBean.setClassOneThreeFive(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassOneThreeFive());
                                priceUBahtBean.setClassThree(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassThree());
                                priceUBahtBean.setClassTwo(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getClassTwo());
                                priceUBahtBean.setPerKilo(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getPerKilo());
                                priceUBahtBean.setPerMeter(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getPerMeter());
                                priceUBahtBean.setPerPiece(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getPerPiece());
                                priceUBahtBean.setPerWrap(snapshot2.getValue(DataDao.ProductTypeBean.DataBean.DataItemBean.PriceUBahtBean.class).getPerWrap());

                                dataItemBean.setPriceUBaht(priceUBahtBean);

                                dataItemBeen.add(dataItemBean);
                            }
                            dataBean.setDataItem(dataItemBeen);
                        }
                        dataBeen.add(dataBean);
                    }
                    providerDao.setData(dataBeen);

                    //testProductTypes.add(providerDao);
                    productTypeDaos.add(providerDao);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mRootRef.addListenerForSingleValueEvent(valueEventListener);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // Log.d("start", "onCreate: "+testProductTypes.size());
//                Log.d("start", "onCreate: "+productTypeDaos.size());
//                Log.d("start", "onCreate: "+productTypeDaos.get(0).getData().get(0).getDataItem().get(0).getAmongPerWrap());
                Intent intent = new Intent(StartAppActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("data", (ArrayList<DataDao.ProductTypeBean>) productTypeDaos);
                startActivity(intent);
                finish();
            }
        },500);




    }

    @Override
    protected void onStop() {
        super.onStop();

        if (valueEventListener != null) {
            mRootRef.removeEventListener(valueEventListener);
        }
    }




}
