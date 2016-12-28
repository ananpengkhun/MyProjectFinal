package com.example.ananpengkhun.myprojectfinal.adapter.viewholder;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 12/27/16.
 */

public class EachItemSizeViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.ed_code_prod) public AppCompatEditText edCodeProd;
    @BindView(R.id.tv_code_prod) public TextView tvCodeProd;
    @BindView(R.id.ed_name_prod) public AppCompatEditText edNameProd;
    @BindView(R.id.tv_namePro) public TextView tvNamePro;
    @BindView(R.id.ed_price_prod) public AppCompatEditText edPriceProd;
    @BindView(R.id.tv_pricePro) public TextView tvPricePro;
    @BindView(R.id.ed_amount_prod) public AppCompatEditText edAmountProd;
    @BindView(R.id.tv_amount_prod) public TextView tvAmountProd;
    @BindView(R.id.ed_unit_prod) public AppCompatEditText edUnitProd;
    @BindView(R.id.tv_unit_prod) public TextView tvUnitProd;
    @BindView(R.id.spinner) public SearchableSpinner spinner;
    @BindView(R.id.tv_chooseSpinner) public TextView tvChooseSpinner;
    @BindView(R.id.spinner_provider) public SearchableSpinner spinnerProvider;
    @BindView(R.id.tv_provider_prod) public TextView tvProviderProd;
    @BindView(R.id.ed_alert_prod) public AppCompatEditText edAlertProd;
    @BindView(R.id.tv_alert_prod) public TextView tvAlertProd;
    @BindView(R.id.imv_box_for_edit) public ImageView imvBoxForEdit;


    public EachItemSizeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
