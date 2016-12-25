package com.example.ananpengkhun.myprojectfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProductActivity;
import com.example.ananpengkhun.myprojectfinal.activity.MyDataInventoryActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.InventoryProductViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class InventoryProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = InventoryProductAdapter.class.getSimpleName();
    private Context mContext;
    private List<ProductDao> productList;

    public InventoryProductAdapter(MyDataInventoryActivity myDataInventoryActivity, List<ProductDao> productList) {
        this.mContext = myDataInventoryActivity;
        this.productList = productList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_item, parent, false);
        return new InventoryProductViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof InventoryProductViewHolder){
            InventoryProductViewHolder inventoryProductViewHolder = (InventoryProductViewHolder) holder;
            inventoryProductViewHolder.tvNamePro.setText(productList.get(position).getProdName());
            inventoryProductViewHolder.tvPricePro.setText(productList.get(position).getPrice());

            inventoryProductViewHolder.cvGroupView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: "+position);
                    Intent intent = new Intent(mContext, DetailOfListProductActivity.class);
                    intent.putExtra("product_object_index",productList.get(position));
//                    intent.putExtra("namePro",productList.get(position).getProdName());
//                    intent.putExtra("pricePro",productList.get(position).getPrice());

                    mContext.startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
