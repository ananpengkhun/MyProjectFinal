package com.example.ananpengkhun.myprojectfinal.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProductTypeActivity;
import com.example.ananpengkhun.myprojectfinal.activity.MyDataInventoryActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.InventoryProductTypeViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.ProductTypeDao;

import java.util.List;

import butterknife.BindView;


/**
 * Created by ananpengkhun on 12/18/16.
 */

public class InventoryProductTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = InventoryProductTypeAdapter.class.getSimpleName();
    private final MyDataInventoryActivity mContext;


    private List<ProductTypeDao> productTypeList;
    private Button btnConfirm;
    private Button btnCancel;

    public InventoryProductTypeAdapter(MyDataInventoryActivity myDataInventoryActivity, List<ProductTypeDao> productTypeList) {
        this.mContext = myDataInventoryActivity;
        this.productTypeList = productTypeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_type_item, parent, false);
        return new InventoryProductTypeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof InventoryProductTypeViewHolder) {
            InventoryProductTypeViewHolder inventoryProductTypeViewHolder = (InventoryProductTypeViewHolder) holder;
            inventoryProductTypeViewHolder.tvNameProType.setText(productTypeList.get(position).getProdTypeName());

            inventoryProductTypeViewHolder.cvGroupView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Log.d(TAG, "onLongClick: ");
                    final Dialog dialog = new Dialog(mContext);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_confirm_deleted);
                    dialog.setCancelable(true);
                    bindId(dialog);

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d(TAG, "onClick: dismiss");
                            dialog.dismiss();
                        }
                    });

                    btnConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d(TAG, "onClick: row deleted.");
                            //some row deleted
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return true;
                }
            });

            inventoryProductTypeViewHolder.cvGroupView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: "+position);
                    Intent intent = new Intent(mContext, DetailOfListProductTypeActivity.class);
                    intent.putExtra("product_type_object_index",productTypeList.get(position));
                    intent.putExtra("index",position);
//                    intent.putExtra("pro_type_name",productTypeList.get(position).getProdTypeName());
//                    intent.putExtra("pro_type_code",productTypeList.get(position).getProdTypeCode());
//                    intent.putExtra("pro_type_des",productTypeList.get(position).getProdTypeDes());
                    mContext.startActivityForResult(intent,MyDataInventoryActivity.PRODUCT_TYPE);


                }
            });
        }
    }

    private void bindId(Dialog dialog) {
        btnConfirm = (Button) dialog.findViewById(R.id.btn_confirm);
        btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
    }

    @Override
    public int getItemCount() {
        return productTypeList.size();
    }

}
