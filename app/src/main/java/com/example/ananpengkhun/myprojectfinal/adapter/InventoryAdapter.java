package com.example.ananpengkhun.myprojectfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProductActivity;
import com.example.ananpengkhun.myprojectfinal.activity.MyDataInventoryActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.InventoryViewHolder;


/**
 * Created by ananpengkhun on 12/18/16.
 */

public class InventoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = InventoryAdapter.class.getSimpleName();
    private Context mContext;

    public InventoryAdapter(MyDataInventoryActivity myDataInventoryActivity) {
        this.mContext = myDataInventoryActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new InventoryViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof InventoryViewHolder){
            InventoryViewHolder inventoryViewHolder = (InventoryViewHolder) holder;
            inventoryViewHolder.txtVpItemList.setText("men na ja "+position);

            inventoryViewHolder.txtVpItemList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: "+position);
                    Intent intent = new Intent(mContext, DetailOfListProductActivity.class);
                    intent.putExtra("position",position);
                    mContext.startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }



}
