package com.example.ananpengkhun.myprojectfinal.adapter.viewholder;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 12/18/16.
 */

public class InventoryProductTypeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_nameProType) public TextView tvNameProType;
    @BindView(R.id.cv_groupView) public CardView cvGroupView;

    public InventoryProductTypeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }




}
