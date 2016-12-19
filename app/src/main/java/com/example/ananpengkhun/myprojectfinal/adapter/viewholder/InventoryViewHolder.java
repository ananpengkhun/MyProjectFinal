package com.example.ananpengkhun.myprojectfinal.adapter.viewholder;

import android.support.design.widget.FloatingActionButton;
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

public class InventoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_vp_item_list) public TextView txtVpItemList;


    //public TextView txtVpItemList;

    public InventoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }




}
