package com.example.ananpengkhun.myprojectfinal.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 12/22/16.
 */

public class ProductTypeAssociateViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_namePro) public TextView tvNamePro;
    @BindView(R.id.tv_codePro) public TextView tvCodePro;
    @BindView(R.id.cv_groupView) public CardView cvGroupView;
    public ProductTypeAssociateViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
