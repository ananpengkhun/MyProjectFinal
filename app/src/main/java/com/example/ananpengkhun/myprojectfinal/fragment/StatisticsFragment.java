package com.example.ananpengkhun.myprojectfinal.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.ReportAdapter;
import com.example.ananpengkhun.myprojectfinal.dao.ReportDao;
import com.example.ananpengkhun.myprojectfinal.dao.TestProductType;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {

    @BindView(R.id.rc_report) RecyclerView rcReport;
    private ReportAdapter reportAdapter;
    private String TAG = StatisticsFragment.class.getSimpleName();
    private Realm realmReport;
    private RealmResults<ReportDao> reportDaos;
    private RealmChangeListener<Realm> DataChangeListener = new RealmChangeListener<Realm>() {
        @Override
        public void onChange(Realm element) {
            loadData();
            reportAdapter.notifyDataSetChanged();
        }
    };

    private StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reportAdapter = new ReportAdapter(getActivity());
        RealmConfiguration realmConfigurationReport = new RealmConfiguration.Builder()
                .name("report.realm")
                .build();
        realmReport = Realm.getInstance(realmConfigurationReport);
        realmReport.addChangeListener(DataChangeListener);
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
        reportAdapter.setData(reportDaos);
        reportAdapter.setDataRealm(realmReport);
        rcReport.setAdapter(reportAdapter);
    }

    public static Fragment newInstant() {
        return new StatisticsFragment();
    }

    private void loadData() {
        reportDaos = realmReport.where(ReportDao.class).findAll();
        reportDaos.distinct("date");

        Log.d(TAG, "loadData: "+reportDaos.size());

//        DateFormat df = new SimpleDateFormat("d/MMM/yyyy");
//        String now = df.format(new Date());
//
//
//
//        Log.d(TAG, "loadData day: "+ now);
    }


}
