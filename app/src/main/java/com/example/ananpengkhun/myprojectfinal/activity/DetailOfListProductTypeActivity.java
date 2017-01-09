package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.ProductTypeAssociateAdapter;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    //    private DataDao dataDao;
    private List<DataDao.ProductTypeBean> productTypeDaos;
    private String name;
    private String code;
    private String des;
    private boolean swap = true;

    private int index;
    private List<ProductDao> productDaoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_list_product_type);
        ButterKnife.bind(this);
        setupView();
        init();
    }

    private void init() {
        productDaoList = new ArrayList<>();
        if (getIntent().getParcelableArrayListExtra("dataDao_item_product") != null) {
            Intent intent = getIntent();
            index = intent.getIntExtra("index", -1);
            productTypeDaos = intent.getParcelableArrayListExtra("dataDao_item_product");
            if (productTypeDaos.get(index).getData() != null) {
                for (int i = 0; i < productTypeDaos.get(index).getData().size(); i++) {
                    ProductDao productDao = new ProductDao();
                    productDao.setProdName(productTypeDaos.get(index).getData().get(i).getNameItem());
                    productDao.setProdCode(productTypeDaos.get(index).getData().get(i).getNameCode());
                    productDaoList.add(productDao);

                    //Log.d("raiwa", "init: "+dataDao.getProductType().get(index).getData().get(i).getNameItem());
                }
            }

            //productTypeDao = intent.getParcelableExtra("product_type_object_index");
            setTextView(productTypeDaos.get(index).getName(),
                    productTypeDaos.get(index).getTypeCode(),
                    productTypeDaos.get(index).getTypeDes());
        }

        rcvAssociateProduct.setLayoutManager(new LinearLayoutManager(DetailOfListProductTypeActivity.this));
        rcvAssociateProduct.setAdapter(new ProductTypeAssociateAdapter(DetailOfListProductTypeActivity.this, productDaoList));


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
            intent.putExtra("index", index);
            setResult(MyDataInventoryActivity.PRODUCT_TYPE, intent);
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

                setTextView(name, code, des);

                // save data
                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                mRootRef.child("/productType/" + index + "/name").setValue(name);
                mRootRef.child("/productType/" + index + "/typeCode").setValue(code);
                mRootRef.child("/productType/" + index + "/typeDes").setValue(des);


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

                if (swap) {
                    swap = false;
                    setTextEdit(productTypeDaos.get(index).getName(),
                            productTypeDaos.get(index).getTypeCode(),
                            productTypeDaos.get(index).getTypeDes());
                } else {
                    setTextEdit(name, code, des);
                }

            }
        }
    };

    private void saveData(String name, String code, String des) {
//        productTypeDao.setProdTypeName(name);
//        productTypeDao.setProdTypeCode(code);
//        productTypeDao.setProdTypeDes(des);

    }

    private void setTextView(String name, String typeCode, String des) {
        tvProductTypeName.setText(name);
        tvProdctTypeCode.setText(typeCode);
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
        intent.putExtra("index", index);
        setResult(MyDataInventoryActivity.PRODUCT_TYPE, intent);
        finish();
    }
}
