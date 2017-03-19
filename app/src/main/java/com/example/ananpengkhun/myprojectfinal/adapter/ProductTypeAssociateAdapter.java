package com.example.ananpengkhun.myprojectfinal.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProductTypeActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.ProductTypeAssociateViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 12/22/16.
 */

public class ProductTypeAssociateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private DetailOfListProductTypeActivity detailOfListProductTypeActivity;
    private List<ProductDao> productDaoList;

    public ProductTypeAssociateAdapter(DetailOfListProductTypeActivity detailOfListProductTypeActivity, List<ProductDao> productDaoList) {
        this.detailOfListProductTypeActivity = detailOfListProductTypeActivity;
        this.productDaoList = productDaoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_associate_item, parent, false);
        return new ProductTypeAssociateViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ProductTypeAssociateViewHolder){
            ProductTypeAssociateViewHolder productTypeAssociateViewHolder = (ProductTypeAssociateViewHolder) holder;
            productTypeAssociateViewHolder.tvNamePro.setText(productDaoList.get(position).getProdName());
            productTypeAssociateViewHolder.tvCodePro.setText(productDaoList.get(position).getProdCode());
            if(!"".equals(productDaoList.get(position).getProductImg())){
                Glide.with(detailOfListProductTypeActivity).load(productDaoList.get(position).getProductImg()).placeholder(ContextCompat.getDrawable(detailOfListProductTypeActivity, R.drawable.folder)).into(productTypeAssociateViewHolder.imvProduct);
            }
        }

    }

    @Override
    public int getItemCount() {
        return productDaoList.size();
    }

}
