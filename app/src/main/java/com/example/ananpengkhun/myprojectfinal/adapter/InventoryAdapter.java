package com.example.ananpengkhun.myprojectfinal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.InventoryViewHolder;


/**
 * Created by ananpengkhun on 12/18/16.
 */

public class InventoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new InventoryViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof InventoryViewHolder){
            InventoryViewHolder inventoryViewHolder = (InventoryViewHolder) holder;
            inventoryViewHolder.txtVpItemList.setText("men na ja "+position);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }



}
