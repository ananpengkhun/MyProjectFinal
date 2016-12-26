package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.ProductTypeAssociateAdapter;
import com.example.ananpengkhun.myprojectfinal.dao.ProductTypeDao;
import com.example.ananpengkhun.myprojectfinal.dao.TestValueDao;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailOfListProductTypeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_detail_of_list_product_type) RelativeLayout activityDetailOfListProductType;
    @BindView(R.id.tv_product_type_name) TextView tvProductTypeName;
    @BindView(R.id.tv_prodct_type_code) TextView tvProdctTypeCode;
    @BindView(R.id.tv_product_type_des) TextView tvProductTypeDes;
    @BindView(R.id.rcv_associate_product) RecyclerView rcvAssociateProduct;
    @BindView(R.id.ed_product_type_name) AppCompatEditText edProductTypeName;
    @BindView(R.id.ed_prodct_type_code) AppCompatEditText edProdctTypeCode;
    @BindView(R.id.ed_product_type_des) AppCompatEditText edProductTypeDes;
    @BindView(R.id.imv_box_for_edit) ImageView imvBoxForEdit;

    private ProductTypeAssociateAdapter productTypeAssociateAdapter;
    private ProductTypeDao productTypeDao;

    private String name;
    private String code;
    private String des;
    private boolean swap = true;

    private int index;

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

        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            index = intent.getIntExtra("index",-1);
            productTypeDao = intent.getParcelableExtra("product_type_object_index");
            setTextView(productTypeDao.getProdTypeName(),
                    productTypeDao.getProdTypeCode(),
                    productTypeDao.getProdTypeDes());
        }

    }

    private void setupView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(toolbarClicklistener);
        imvBoxForEdit.setOnClickListener(imvClicklistener);
    }

    private View.OnClickListener toolbarClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            setResult(MyDataInventoryActivity.PRODUCT_TYPE,intent);
            finish();
        }
    };

    private View.OnClickListener imvClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (imvBoxForEdit.isSelected()) {
                imvBoxForEdit.setSelected(false);
                //textView Visible
                tvProductTypeName.setVisibility(View.VISIBLE);
                tvProdctTypeCode.setVisibility(View.VISIBLE);
                tvProductTypeDes.setVisibility(View.VISIBLE);
                //EditText Gone
                edProductTypeName.setVisibility(View.GONE);
                edProdctTypeCode.setVisibility(View.GONE);
                edProductTypeDes.setVisibility(View.GONE);

                name = edProductTypeName.getText().toString();
                code = edProdctTypeCode.getText().toString();
                des = edProductTypeDes.getText().toString();

                setTextView(name,code,des);
                // save data



            } else {
                // edit data
                imvBoxForEdit.setSelected(true);
                //textView Gone
                tvProductTypeName.setVisibility(View.GONE);
                tvProdctTypeCode.setVisibility(View.GONE);
                tvProductTypeDes.setVisibility(View.GONE);
                //EditText Visible
                edProductTypeName.setVisibility(View.VISIBLE);
                edProdctTypeCode.setVisibility(View.VISIBLE);
                edProductTypeDes.setVisibility(View.VISIBLE);

                if(swap){
                    swap = false;
                    setTextEdit(productTypeDao.getProdTypeName(),
                            productTypeDao.getProdTypeCode(),
                            productTypeDao.getProdTypeDes());
                }else{
                    setTextEdit(name,code,des);
                }

            }
        }
    };

    private void saveData(String name, String code, String des) {
        TestValueDao.getInstance().setName("asdasdasfsdfasfadf");
//        productTypeDao.setProdTypeName(name);
//        productTypeDao.setProdTypeCode(code);
//        productTypeDao.setProdTypeDes(des);

    }

    private void setTextView(String name, String code, String des) {
        tvProductTypeName.setText(name);
        tvProdctTypeCode.setText(code);
        tvProductTypeDes.setText(des);
    }

    private void setTextEdit(String name, String code, String des) {
        edProductTypeName.setText(name);
        edProdctTypeCode.setText(code);
        edProductTypeDes.setText(des);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("index",index);
        setResult(MyDataInventoryActivity.PRODUCT_TYPE,intent);
        finish();
    }
}
