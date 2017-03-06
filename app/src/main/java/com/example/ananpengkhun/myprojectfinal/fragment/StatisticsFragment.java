package com.example.ananpengkhun.myprojectfinal.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.ReportAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {

    @BindView(R.id.rc_report) RecyclerView rcReport;
    private ReportAdapter reportAdapter;

    private StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reportAdapter = new ReportAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistics, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    private void init() {
        loadData();
        rcReport.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcReport.setHasFixedSize(true);
        rcReport.setAdapter(reportAdapter);
    }

    public static Fragment newInstant() {
        return new StatisticsFragment();
    }

    private void loadData() {

    }


}
