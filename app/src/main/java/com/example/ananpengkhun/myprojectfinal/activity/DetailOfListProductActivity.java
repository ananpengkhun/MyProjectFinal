package com.example.ananpengkhun.myprojectfinal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailOfListProductActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_detail_of_list) RelativeLayout activityDetailOfList;
    @BindView(R.id.tv_namePro) TextView tvNamePro;
    @BindView(R.id.tv_pricePro) TextView tvPricePro;
    @BindView(R.id.imv_box_for_edit) ImageView imvBoxForEdit;
    @BindView(R.id.spinner) SearchableSpinner spinner;
    @BindView(R.id.tv_chooseSpinner) TextView tvChooseSpinner;
    @BindView(R.id.ed_code_prod) AppCompatEditText edCodeProd;
    @BindView(R.id.tv_code_prod) TextView tvCodeProd;
    @BindView(R.id.ed_name_prod) AppCompatEditText edNameProd;
    @BindView(R.id.ed_price_prod) AppCompatEditText edPriceProd;
    @BindView(R.id.ed_amount_prod) AppCompatEditText edAmountProd;
    @BindView(R.id.tv_amount_prod) TextView tvAmountProd;
    @BindView(R.id.ed_unit_prod) AppCompatEditText edUnitProd;
    @BindView(R.id.tv_unit_prod) TextView tvUnitProd;
    @BindView(R.id.ed_provider_prod) AppCompatEditText edProviderProd;
    @BindView(R.id.tv_provider_prod) TextView tvProviderProd;
    @BindView(R.id.ed_alert_prod) AppCompatEditText edAlertProd;
    @BindView(R.id.tv_alert_prod) TextView tvAlertProd;


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
        imvBoxForEdit.setOnClickListener(imvClicklistener);
    }

    private void init() {
        if (getIntent().getExtras() != null) {
            String namePro = getIntent().getStringExtra("namePro");
            String pricePro = getIntent().getStringExtra("pricePro");

            tvNamePro.setText(namePro);
            tvPricePro.setText(pricePro);
        }
        spinnerOfTypeProduct();
    }

    private void spinnerOfTypeProduct() {
        String colors[] = {"Red", "Blue", "White", "Yellow", "Black", "Green", "Purple", "Orange", "Grey"};

        //butt = (Button) findViewById(R.id.asd);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colors);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);


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
            if (imvBoxForEdit.isSelected()) {
                imvBoxForEdit.setSelected(false);

                //textView Visible
                tvChooseSpinner.setVisibility(View.VISIBLE);
                tvCodeProd.setVisibility(View.VISIBLE);
                tvNamePro.setVisibility(View.VISIBLE);
                tvPricePro.setVisibility(View.VISIBLE);
                tvAmountProd.setVisibility(View.VISIBLE);
                tvUnitProd.setVisibility(View.VISIBLE);
                tvProviderProd.setVisibility(View.VISIBLE);
                tvAlertProd.setVisibility(View.VISIBLE);

                //EditText Gone
                edAlertProd.setVisibility(View.GONE);
                edProviderProd.setVisibility(View.GONE);
                edUnitProd.setVisibility(View.GONE);
                edAmountProd.setVisibility(View.GONE);
                edPriceProd.setVisibility(View.GONE);
                edNameProd.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                edCodeProd.setVisibility(View.GONE);


                if (spinner.getSelectedItem() == null) {
                    Log.d("s", "onClick: ");
                } else {
                    tvChooseSpinner.setText(spinner.getSelectedItem().toString());
                }
            } else {
                imvBoxForEdit.setSelected(true);
                //textView Gone
                tvAlertProd.setVisibility(View.GONE);
                tvProviderProd.setVisibility(View.GONE);
                tvUnitProd.setVisibility(View.GONE);
                tvAmountProd.setVisibility(View.GONE);
                tvPricePro.setVisibility(View.GONE);
                tvNamePro.setVisibility(View.GONE);
                tvChooseSpinner.setVisibility(View.GONE);
                tvCodeProd.setVisibility(View.GONE);
                //EditText Visible
                edAlertProd.setVisibility(View.VISIBLE);
                edProviderProd.setVisibility(View.VISIBLE);
                edUnitProd.setVisibility(View.VISIBLE);
                edAmountProd.setVisibility(View.VISIBLE);
                edPriceProd.setVisibility(View.VISIBLE);
                edNameProd.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                edCodeProd.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}
