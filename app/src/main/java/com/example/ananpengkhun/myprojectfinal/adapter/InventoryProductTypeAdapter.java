package com.example.ananpengkhun.myprojectfinal.adapter;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProductTypeActivity;
import com.example.ananpengkhun.myprojectfinal.activity.MyDataInventoryActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.InventoryProductTypeViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductTypeDao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ananpengkhun on 12/18/16.
 */

public class InventoryProductTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = InventoryProductTypeAdapter.class.getSimpleName();
    private final MyDataInventoryActivity mContext;
    private DatabaseReference mRootRef;
    private DatabaseReference mType;

    private List<ProductTypeDao> productTypeList;
    //private DataDao dataDao;
    private List<DataDao.ProductTypeBean> productTypeDaos;
    private Button btnConfirm;
    private Button btnCancel;

    public InventoryProductTypeAdapter(MyDataInventoryActivity myDataInventoryActivity, List<ProductTypeDao> productTypeList, List<DataDao.ProductTypeBean> dataDao) {
        this.mContext = myDataInventoryActivity;
        this.productTypeList = productTypeList;
        this.productTypeDaos = dataDao;
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
                            mRootRef = FirebaseDatabase.getInstance().getReference();
                            mType = mRootRef.child("/productType/" + position);
                            productTypeList.remove(position);
                            mType.removeValue();

                            // position: 2 size : 3
                            Log.d(TAG, "onClick position: "+position);
                            Log.d(TAG, "onClick size: "+productTypeList.size());
                            for (int i = position; i < productTypeList.size(); i++) {

                                HashMap<String, Object> postValues = new HashMap<>();
                                postValues.put("name", productTypeList.get(position).getProdTypeName());
                                postValues.put("status", "success");
                                postValues.put("typeCode", productTypeList.get(position).getProdTypeCode());
                                postValues.put("typeDes", productTypeList.get(position).getProdTypeDes());
                                postValues.put("typeId", position + 1);

                                Map<String, Object> childUpdates = new HashMap<>();
                                childUpdates.put("/productType/" + position, postValues);
                                mRootRef.updateChildren(childUpdates);
                            }


                            notifyDataSetChanged();
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
                    Log.d(TAG, "onClick: " + position);
                    Intent intent = new Intent(mContext, DetailOfListProductTypeActivity.class);
                    intent.putParcelableArrayListExtra("dataDao_item_product",(ArrayList<DataDao.ProductTypeBean>) productTypeDaos);
                    //intent.putExtra("product_type_object_index",productTypeList.get(position).g);
                    intent.putExtra("index", position);
                    Log.d(TAG, "onClick: " + position);
//                    intent.putExtra("pro_type_name",productTypeList.get(position).getProdTypeName());
//                    intent.putExtra("pro_type_code",productTypeList.get(position).getProdTypeCode());
//                    intent.putExtra("pro_type_des",productTypeList.get(position).getProdTypeDes());
                    mContext.startActivityForResult(intent, MyDataInventoryActivity.PRODUCT_TYPE);


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
