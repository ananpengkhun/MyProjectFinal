package com.example.ananpengkhun.myprojectfinal.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 3/7/17.
 */

public class DialogInReportViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_name) public TextView tvName;
    @BindView(R.id.tv_many) public TextView tvMany;

    public DialogInReportViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
