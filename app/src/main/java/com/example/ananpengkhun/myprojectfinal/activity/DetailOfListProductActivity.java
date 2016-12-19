package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ananpengkhun.myprojectfinal.R;

public class DetailOfListProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_list);
        init();
    }

    private void init() {

        int s = getIntent().getIntExtra("position",0);

        Toast.makeText(DetailOfListProductActivity.this,"position is :"+ String.valueOf(s),Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
