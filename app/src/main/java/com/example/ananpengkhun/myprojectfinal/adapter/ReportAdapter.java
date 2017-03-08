package com.example.ananpengkhun.myprojectfinal.adapter;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProviderActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.ReportViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.ReportDao;


import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ananpengkhun on 3/6/17.
 */

public class ReportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private RealmResults<ReportDao> data;
    private RecyclerView rcDialogReport;
    private DialogInReportAdapter dialogInReportAdapter;
    private Realm dataRealm;

    public ReportAdapter(FragmentActivity activity) {
        this.context = activity;
        dialogInReportAdapter = new DialogInReportAdapter();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_report, parent, false);
        return new ReportViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        if(holder instanceof ReportViewHolder){
            final ReportViewHolder reportViewHolder = (ReportViewHolder) holder;
            reportViewHolder.textView.setText(data.get(position).getDate());
            reportViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            //reportViewHolder.tvData.setText(data.get(position).getProdNameRep() +"--------"+data.get(position).getProdQuantityRep());

            reportViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_report_list);
                    dialog.setCancelable(true);
                    bindId(dialog);
                    RealmResults<ReportDao> reportDaos = dataRealm.where(ReportDao.class).equalTo("date",data.get(position).getDate()).findAll();
                    rcDialogReport.setLayoutManager(new LinearLayoutManager(context));
                    rcDialogReport.setHasFixedSize(true);
                    Log.d("report", "onClick: "+reportDaos.size());
                    Log.d("report", "onClick: "+reportDaos.toString());
                    dialogInReportAdapter.setData(reportDaos);
                    rcDialogReport.setAdapter(dialogInReportAdapter);
                    dialog.show();
                }
            });
        }
    }

    private void bindId(Dialog dialog) {
        rcDialogReport = (RecyclerView) dialog.findViewById(R.id.rc_dialog_report);
    }


    @Override
    public int getItemCount() {
        if(data.size() == 0){
            return 0;
        }
        return data.size();
    }

    public void setData(RealmResults<ReportDao> data) {
        this.data = data;
    }

    public void setDataRealm(Realm dataRealm) {
        this.dataRealm = dataRealm;
    }
}
