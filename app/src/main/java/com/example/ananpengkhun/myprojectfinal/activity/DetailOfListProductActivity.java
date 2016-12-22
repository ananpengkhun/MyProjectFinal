package com.example.ananpengkhun.myprojectfinal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
                spinner.setVisibility(View.GONE);
                tvChooseSpinner.setVisibility(View.VISIBLE);
                if(!"SEKECCTTT".equals(spinner.getSelectedItem().toString())){
                    tvChooseSpinner.setText(spinner.getSelectedItem().toString());
                }
            } else {
                imvBoxForEdit.setSelected(true);
                tvChooseSpinner.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}
