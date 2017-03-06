package com.example.ananpengkhun.myprojectfinal.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.ReportViewHolder;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ananpengkhun on 3/6/17.
 */

public class ReportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    public ReportAdapter(FragmentActivity activity) {
        this.context = activity;
        for(int i=0;i<3;i++){
            expandState.append(i,false);
        }
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
            reportViewHolder.setIsRecyclable(false);
            reportViewHolder.textView.setText("my text:"+position);
            reportViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            reportViewHolder.expandable.setInRecyclerView(true);
            reportViewHolder.expandable.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
            reportViewHolder.expandable.setInterpolator(Utils.createInterpolator(Utils.ACCELERATE_DECELERATE_INTERPOLATOR));
            reportViewHolder.expandable.setExpanded(expandState.get(position));
            reportViewHolder.expandable.setListener(new ExpandableLayoutListenerAdapter() {
                @Override
                public void onPreClose() {
                    createRotateAnimator(reportViewHolder.buttonLayout, 180f, 0f).start();
                    expandState.put(position, false);
                }

                @Override
                public void onPreOpen() {
                    createRotateAnimator(reportViewHolder.buttonLayout, 0f, 180f).start();
                    expandState.put(position, true);
                }
            });
            reportViewHolder.buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
            reportViewHolder.buttonLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickButton(reportViewHolder.expandable);
                }
            });
        }
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
