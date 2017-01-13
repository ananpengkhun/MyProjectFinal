package com.example.ananpengkhun.myprojectfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.EachItemSizeAdapter;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailOfListProductActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_detail_of_list) RelativeLayout activityDetailOfList;
    @BindView(R.id.rc_size_item) RecyclerView rcSizeItem;
    @BindView(R.id.ed_code_prod) AppCompatEditText edCodeProd;
    @BindView(R.id.tv_code_prod) TextView tvCodeProd;
    @BindView(R.id.ed_name_prod) AppCompatEditText edNameProd;
    @BindView(R.id.tv_namePro) TextView tvNamePro;
    @BindView(R.id.tv_chooseSpinner) TextView tvChooseSpinner;
    @BindView(R.id.spinner_provider) SearchableSpinner spinnerProvider;
    @BindView(R.id.tv_provider_prod) TextView tvProviderProd;
    @BindView(R.id.imv_box_for_edit) ImageView imvBoxForEdit;
    @BindView(R.id.img_product) ImageView imgProduct;

    private ProductDao productDao;
    private boolean swap = true;

    private String prodCode;
    private String prodName;
    private String prodPrice;
    private int prodAmount;
    private String prodUnit;
    private int prodAlert;
    private List<String> listProdType;
    private List<String> listProvider;

    private EachItemSizeAdapter eachItemSizeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_list);
        ButterKnife.bind(this);
        setupView();
        init();
    }

    private void setupView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(toolbarClicklistener);
        //  imvBoxForEdit.setOnClickListener(imvClicklistener);
    }

    private void init() {
        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            productDao = intent.getParcelableExtra("product_object_index");

            tvNamePro.setText(productDao.getProdName());
            tvCodeProd.setText(productDao.getProdCode());
            if(productDao.getProductImg() != null){
                Glide.with(DetailOfListProductActivity.this).load(productDao.getProductImg()).placeholder(ContextCompat.getDrawable(DetailOfListProductActivity.this,R.drawable.folder)).into(imgProduct);
            }

            rcSizeItem.setHasFixedSize(true);
            rcSizeItem.setLayoutManager(new LinearLayoutManager(DetailOfListProductActivity.this));
            eachItemSizeAdapter = new EachItemSizeAdapter(DetailOfListProductActivity.this, productDao.getProductEachSizes());
            rcSizeItem.setAdapter(eachItemSizeAdapter);
        }


        spinnerOfTypeProduct();


    }

    private void spinnerOfTypeProduct() {
//        if (productDao.getProdType() != null) {
//            listProdType = new ArrayList<>();
//            for (int i = 0; i < productDao.getProdType().size(); i++) {
//                listProdType.add(productDao.getProdType().get(i).getProdTypeName());
//            }
//        } else {
//
//        }
//        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listProdType);
//        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//        spinner.setAdapter(spinnerArrayAdapter);
//
//
//        if (productDao.getProdProvider() != null) {
//            listProvider = new ArrayList<>();
//            for (int i = 0; i < productDao.getProdProvider().size(); i++) {
//                listProvider.add(productDao.getProdProvider().get(i).getProvName());
//            }
//        } else {
//
//        }
//        ArrayAdapter<String> spinnerArrayAdapterProvider = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listProvider);
//        spinnerArrayAdapterProvider.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//        spinnerProvider.setAdapter(spinnerArrayAdapterProvider);

    }

    private View.OnClickListener toolbarClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private View.OnClickListener imvClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

//    private void setTextView(String prodCode, String prodName, String prodPrice, int prodAmount, String prodUnit, int prodAlert) {
//        tvCodeProd.setText(prodCode);
//        tvNamePro.setText(prodName);
//        tvPricePro.setText(prodPrice);
//        tvAmountProd.setText(String.valueOf(prodAmount));
//        tvUnitProd.setText(prodUnit);
//        tvAlertProd.setText(String.valueOf(prodAlert));
//    }

//    private void setTextEdit(String prodCode, String prodName, String prodPrice, int prodAmount, String prodUnit, int prodAlert) {
//        edCodeProd.setText(prodCode);
//        edNameProd.setText(prodName);
//        edPriceProd.setText(prodPrice);
//        edAmountProd.setText(String.valueOf(prodAmount));
//        edUnitProd.setText(prodUnit);
//        edAlertProd.setText(String.valueOf(prodAlert));
//    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
