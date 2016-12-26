package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartAppActivity extends AppCompatActivity {

    @BindView(R.id.tv_countdown) TextView tvCountdown;
    @BindView(R.id.activity_start_app) RelativeLayout activityStartApp;

    int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartAppActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }, 3000);


    }


}
