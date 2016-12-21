package com.example.ananpengkhun.myprojectfinal.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class InventoryProviderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_providerName) public TextView tvProviderName;
    @BindView(R.id.cv_groupView) public CardView cvGroupView;

    public InventoryProviderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
