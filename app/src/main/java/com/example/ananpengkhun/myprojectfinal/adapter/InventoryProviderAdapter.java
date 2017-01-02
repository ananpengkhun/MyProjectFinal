package com.example.ananpengkhun.myprojectfinal.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProviderActivity;
import com.example.ananpengkhun.myprojectfinal.activity.MyDataInventoryActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.InventoryProviderViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ananpengkhun on 12/21/16.
 */

public class InventoryProviderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = InventoryProductTypeAdapter.class.getSimpleName();

    private Context mContext;
    private List<ProviderDao> providerList;
    private Button btnConfirm;
    private Button btnCancel;

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

            inventoryProviderViewHolder.cvGroupView.setOnLongClickListener(new View.OnLongClickListener() {
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
                            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com");
                            Query reference = mRootRef.child("provider").orderByChild("provId").equalTo(providerList.get(position).getProvId());

                            Log.d(TAG, "onClick: "+providerList.get(position).getProvId());
                            providerList.remove(position);
                            //reference.removeValue();

                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                        appleSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

//                            // position: 2 size : 3
//                            Log.d(TAG, "onClick position: "+position);
//                            Log.d(TAG, "onClick size: "+productTypeList.size());
//                            for (int i = position; i < productTypeList.size(); i++) {
//
//                                HashMap<String, Object> postValues = new HashMap<>();
//                                postValues.put("name", productTypeList.get(position).getProdTypeName());
//                                postValues.put("status", "success");
//                                postValues.put("typeCode", productTypeList.get(position).getProdTypeCode());
//                                postValues.put("typeDes", productTypeList.get(position).getProdTypeDes());
//                                postValues.put("typeId", position + 1);
//
//                                Map<String, Object> childUpdates = new HashMap<>();
//                                childUpdates.put("/productType/" + position, postValues);
//                                mRootRef.updateChildren(childUpdates);
//                            }
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return true;
                }
            });

            inventoryProviderViewHolder.cvGroupView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: "+position);
                    Intent intent = new Intent(mContext, DetailOfListProviderActivity.class);
                    intent.putExtra("provider_object_index",providerList.get(position));
                    //intent.putExtra("position",position);
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
        return providerList.size();
    }
}
