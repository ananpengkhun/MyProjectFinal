package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductTypeDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;
import com.example.ananpengkhun.myprojectfinal.httpmanager.DataHttpManager;
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        ButterKnife.bind(this);

        mRootRef = FirebaseDatabase.getInstance().getReference();

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataDao = dataSnapshot.getValue(DataDao.class);
                //Log.d("start", "onDataChange: " + dataDao.getProductType().get(0).getData().get(0).getDataItem().get(0).getContrainUPiecePerBox());
                Intent intent = new Intent(StartAppActivity.this, MainActivity.class);
                intent.putExtra("data", dataDao);
                startActivity(intent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("start", "onCancelled: "+databaseError.getMessage());
            }
        };
        mRootRef.addValueEventListener(valueEventListener);








//        DataHttpManager.getInstance().getDataInterface().listRepos().enqueue(new Callback<DataDao>() {
//            @Override
//            public void onResponse(Call<DataDao> call, Response<DataDao> response) {
//                //Log.d("start", "onResponse: "+response.body().getProductType().get(0).getName());
//                dataDao = response.body();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(StartAppActivity.this,MainActivity.class);
//                        intent.putExtra("data",dataDao);
//                        startActivity(intent);
//                    }
//                }, 3000);
//            }
//
//            @Override
//            public void onFailure(Call<DataDao> call, Throwable t) {
//                Toast.makeText(StartAppActivity.this,"Start App Fail:"+t.toString(),Toast.LENGTH_LONG).show();
//            }
//        });


    }

    @Override
    protected void onStop() {
        super.onStop();

        if (valueEventListener != null) {
            mRootRef.removeEventListener(valueEventListener);
        }
    }

}
