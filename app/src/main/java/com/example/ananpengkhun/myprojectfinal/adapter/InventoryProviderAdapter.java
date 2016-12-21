package com.example.ananpengkhun.myprojectfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProviderActivity;
import com.example.ananpengkhun.myprojectfinal.activity.MyDataInventoryActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.InventoryProviderViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class InventoryProviderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = InventoryProductTypeAdapter.class.getSimpleName();

    private Context mContext;
    private List<ProviderDao> providerList;

    public InventoryProviderAdapter(MyDataInventoryActivity myDataInventoryActivity, List<ProviderDao> providerList) {
        this.mContext = myDataInventoryActivity;
        this.providerList = providerList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_provider_item, parent, false);
        return new InventoryProviderViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof InventoryProviderViewHolder) {
            InventoryProviderViewHolder inventoryProviderViewHolder = (InventoryProviderViewHolder) holder;
            inventoryProviderViewHolder.tvProviderName.setText(providerList.get(position).getProvName());

            inventoryProviderViewHolder.cvGroupView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: "+position);
                    Intent intent = new Intent(mContext, DetailOfListProviderActivity.class);
                    //intent.putExtra("position",position);
                    mContext.startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return providerList.size();
    }
}
