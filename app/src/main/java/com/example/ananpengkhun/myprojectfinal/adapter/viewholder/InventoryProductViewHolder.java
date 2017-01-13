package com.example.ananpengkhun.myprojectfinal.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class InventoryProductViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_namePro) public TextView tvNamePro;
    @BindView(R.id.tv_pricePro) public TextView tvPricePro;
    @BindView(R.id.txt_vp_item_list) TextView txtVpItemList;
    @BindView(R.id.cv_groupView) public CardView cvGroupView;
    @BindView(R.id.imv_product) public ImageView imvProduct;


    public InventoryProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
