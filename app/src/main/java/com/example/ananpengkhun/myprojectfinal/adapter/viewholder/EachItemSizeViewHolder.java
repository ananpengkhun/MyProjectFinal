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

    @BindView(R.id.ed_name_prod_size) public AppCompatEditText edNameProdSize;
    @BindView(R.id.tv_name_prod_size) public TextView tvNameProdSize;
    @BindView(R.id.ed_unit_prod) public AppCompatEditText edUnitProd;
    @BindView(R.id.tv_unit_prod) public TextView tvUnitProd;
    @BindView(R.id.tv_price_class_one) public TextView tvPriceClassOne;
    @BindView(R.id.tv_price_class_two) public TextView tvPriceClassTwo;
    @BindView(R.id.tv_price_class_three) public TextView tvPriceClassThree;
    @BindView(R.id.tv_price_class_five) public TextView tvPriceClassFive;
    @BindView(R.id.tv_price_class_eight_five) public TextView tvPriceClassEightFive;
    @BindView(R.id.tv_price_class_one_three_five) public TextView tvPriceClassOneThreeFive;
    @BindView(R.id.tv_price_per_kilo) public TextView tvPricePerKilo;
    @BindView(R.id.tv_price_per_meter) public TextView tvPricePerMeter;
    @BindView(R.id.tv_price_per_piece) public TextView tvPricePerPiece;
    @BindView(R.id.tv_price_per_wrap) public TextView tvPricePerWrap;
    @BindView(R.id.ed_amount_prod) public AppCompatEditText edAmountProd;
    @BindView(R.id.tv_efford) public TextView tvEfford;
    @BindView(R.id.spinner) public SearchableSpinner spinner;
    @BindView(R.id.tv_amount_per_wrap) public  TextView tvAmountPerWrap;
    @BindView(R.id.ed_alert_prod) public AppCompatEditText edAlertProd;
    @BindView(R.id.tv_alert_prod) public TextView tvAlertProd;
    @BindView(R.id.imv_box_for_edit) public ImageView imvBoxForEdit;


    public EachItemSizeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
