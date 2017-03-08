package com.example.ananpengkhun.myprojectfinal.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;


import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ananpengkhun on 3/6/17.
 */

public class ReportViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textView) public TextView textView;

    public ReportViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
