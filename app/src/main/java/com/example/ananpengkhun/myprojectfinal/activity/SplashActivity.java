package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class SplashActivity extends AppCompatActivity {

    private DatabaseReference mRootRef;
    private DataDao dataDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRootRef = FirebaseDatabase.getInstance().getReference();
                mRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("start", "onDataChange: " + dataSnapshot.toString());

                        dataDao = dataSnapshot.getValue(DataDao.class);
                        Intent intent = new Intent(SplashActivity.this,StartAppActivity.class);
                        Log.d("getInfoFirebase", "run: "+dataDao.getProductType().size());
                        intent.putExtra("data",dataDao);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("start", "onCancelled: " + databaseError.getMessage());
                    }
                });
            }
        },2000);




    }

}
