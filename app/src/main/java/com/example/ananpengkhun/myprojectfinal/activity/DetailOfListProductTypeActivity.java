package com.example.ananpengkhun.myprojectfinal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.ProductTypeAssociateAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailOfListProductTypeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_detail_of_list_product_type) RelativeLayout activityDetailOfListProductType;
    @BindView(R.id.tv_product_type_name) TextView tvProductTypeName;
    @BindView(R.id.tv_prodct_type_code) TextView tvProdctTypeCode;
    @BindView(R.id.tv_product_type_des) TextView tvProductTypeDes;
    @BindView(R.id.rcv_associate_product) RecyclerView rcvAssociateProduct;

    private ProductTypeAssociateAdapter productTypeAssociateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_list_product_type);
        ButterKnife.bind(this);
        setupView();
        init();
    }

    private void init() {
        productTypeAssociateAdapter = new ProductTypeAssociateAdapter();
        rcvAssociateProduct.setAdapter(productTypeAssociateAdapter);
    }

    private void setupView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(toolbarClicklistener);
    }

    private View.OnClickListener toolbarClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}