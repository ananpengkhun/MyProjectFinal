package com.example.ananpengkhun.myprojectfinal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailOfListProviderActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_detail_of_list_provider) RelativeLayout activityDetailOfListProvider;
    @BindView(R.id.ed_provider_name) AppCompatEditText edProviderName;
    @BindView(R.id.tv_provider_name) TextView tvProviderName;
    @BindView(R.id.ed_provider_address) AppCompatEditText edProviderAddress;
    @BindView(R.id.tv_provider_address) TextView tvProviderAddress;
    @BindView(R.id.ed_provider_phone) AppCompatEditText edProviderPhone;
    @BindView(R.id.tv_provider_phone) TextView tvProviderPhone;
    @BindView(R.id.ed_provider_email) AppCompatEditText edProviderEmail;
    @BindView(R.id.tv_provider_email) TextView tvProviderEmail;
    @BindView(R.id.imv_box_for_edit) ImageView imvBoxForEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_list_provider);
        ButterKnife.bind(this);
        setupView();
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
            finish();
        }
    };

    private View.OnClickListener imvClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (imvBoxForEdit.isSelected()) {
                imvBoxForEdit.setSelected(false);
                //textView Visible
                tvProviderName.setVisibility(View.VISIBLE);
                tvProviderAddress.setVisibility(View.VISIBLE);
                tvProviderPhone.setVisibility(View.VISIBLE);
                tvProviderEmail.setVisibility(View.VISIBLE);

                //EditText Gone
                edProviderName.setVisibility(View.GONE);
                edProviderAddress.setVisibility(View.GONE);
                edProviderPhone.setVisibility(View.GONE);
                edProviderEmail.setVisibility(View.GONE);

            } else {
                imvBoxForEdit.setSelected(true);
                //textView Gone
                tvProviderName.setVisibility(View.GONE);
                tvProviderAddress.setVisibility(View.GONE);
                tvProviderPhone.setVisibility(View.GONE);
                tvProviderEmail.setVisibility(View.GONE);

                //EditText Visible
                edProviderName.setVisibility(View.VISIBLE);
                edProviderAddress.setVisibility(View.VISIBLE);
                edProviderPhone.setVisibility(View.VISIBLE);
                edProviderEmail.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}
