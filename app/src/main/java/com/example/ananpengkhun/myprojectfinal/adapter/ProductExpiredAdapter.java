package com.example.ananpengkhun.myprojectfinal.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.ProductExpiredViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 1/15/17.
 */

public class ProductExpiredAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ProductDao> data;
    private Context context;

    public ProductExpiredAdapter(FragmentActivity activity) {
        this.context = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_expire, parent, false);
        return new ProductExpiredViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ProductExpiredViewHolder){
            ProductExpiredViewHolder productExpiredViewHolder = (ProductExpiredViewHolder) holder;
            productExpiredViewHolder.tvNamePro.setText(data.get(position).getProdName());
            productExpiredViewHolder.tvPricePro.setText(data.get(position).getProdCode());
            if(!"".equals(data.get(position).getProductImg())){
                Glide.with(context).load(data.get(position).getProductImg()).placeholder(ContextCompat.getDrawable(context,R.drawable.folder)).into(productExpiredViewHolder.imvProduct);
            }

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ProductDao> data) {
        this.data = data;
    }
}
