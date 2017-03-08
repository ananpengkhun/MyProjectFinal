package com.example.ananpengkhun.myprojectfinal.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.DialogInReportViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.ReportDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by ananpengkhun on 3/7/17.
 */

public class DialogInReportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private RealmResults<ReportDao> data;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_report_list_item, parent, false);
        return new DialogInReportViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DialogInReportViewHolder){
            DialogInReportViewHolder dialogInReportViewHolder = (DialogInReportViewHolder) holder;
            dialogInReportViewHolder.tvName.setText(data.get(position).getProdNameRep());
//            Log.d("dialog", "onBindViewHolder: "+data.toString());
            dialogInReportViewHolder.tvMany.setText(String.valueOf(data.get(position).getProdQuantityRep()));
        }
    }

    @Override
    public int getItemCount() {
        if(data.size() ==0){
            return 0;
        }
        return data.size();
    }

    public void setData(RealmResults<ReportDao> data) {
        this.data = data;
    }

}
