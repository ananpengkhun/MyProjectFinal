package com.example.ananpengkhun.myprojectfinal.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProductActivity;
import com.example.ananpengkhun.myprojectfinal.activity.MyDataInventoryActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.InventoryProductViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductEachSize;
import com.example.ananpengkhun.myprojectfinal.dao.ProductTypeDao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class InventoryProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = InventoryProductAdapter.class.getSimpleName();
    private Context mContext;
    private List<ProductDao> productList;
    private List<ProductTypeDao> productTypeList;
    private Button btnConfirm;
    private Button btnCancel;

    public void setProductList(List<ProductDao> productList, List<ProductTypeDao> productTypeList) {
        this.productList = productList;
        this.productTypeList = productTypeList;
    }

    public InventoryProductAdapter(MyDataInventoryActivity myDataInventoryActivity) {
        this.mContext = myDataInventoryActivity;

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
            inventoryProductViewHolder.tvPricePro.setText(productList.get(position).getProdCode());
            
            
            inventoryProductViewHolder.cvGroupView.setOnLongClickListener(new View.OnLongClickListener() {
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

                            //Log.d(TAG, "onClick: "+productTypeList.get(position).ge);
                            //some row deleted
//                            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com");
//                            Query reference = mRootRef.child("productType").orderByChild("provId").equalTo(providerList.get(position).getProvId());
//
//                            Log.d(TAG, "onClick: "+providerList.get(position).getProvId());
//                            providerList.remove(position);
//                            //reference.removeValue();
//
//                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
//                                        appleSnapshot.getRef().removeValue();
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });


                            //notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return true;
                }
            });
            
            inventoryProductViewHolder.cvGroupView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: "+position);
                    Intent intent = new Intent(mContext, DetailOfListProductActivity.class);
                    intent.putExtra("product_object_index",productList.get(position));
                    //Log.d(TAG, "onClick: "+productList.get(position).getProductEachSizes());
                    mContext.startActivity(intent);

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
        return productList.size();
    }

}
