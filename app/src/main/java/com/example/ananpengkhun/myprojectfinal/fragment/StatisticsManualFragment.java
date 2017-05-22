package com.example.ananpengkhun.myprojectfinal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.StatisticsManualAdapter;
import com.example.ananpengkhun.myprojectfinal.dao.ReportDao;
import com.example.ananpengkhun.myprojectfinal.dao.ReportManDao;
import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class StatisticsManualFragment extends Fragment {

    @BindView(R.id.btn_start_date) Button btnStartDate;
    @BindView(R.id.btn_end_date) Button btnEndDate;
    @BindView(R.id.tv_start_date) TextView tvStartDate;
    @BindView(R.id.tv_end_date) TextView tvEndDate;
    @BindView(R.id.rc_statistics_manual) RecyclerView rcStatisticsManual;

    private DatePickerDialog mDatePicker;
    private StatisticsManualAdapter statisticsManualAdapter;
    private Realm realmReport;
    private RealmResults<ReportDao> reportDaos;

    private Calendar mCalendar;
    private String startDate = "";
    private String endDate = "";
    private String findDateStart;
    private String findDateEnd;
    List<ReportDao> reportDaosL;
    List<ReportManDao> reportManDaos;

    public StatisticsManualFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmConfiguration realmConfigurationReport = new RealmConfiguration.Builder()
                .name("report.realm")
                .build();
        realmReport = Realm.getInstance(realmConfigurationReport);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistics_manual, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        btnEndDate.setEnabled(false);
        mCalendar = Calendar.getInstance();
        mDatePicker = DatePickerDialog.newInstance(onDateSetListener,
                mCalendar.get(Calendar.YEAR),       // ปี
                mCalendar.get(Calendar.MONTH),      // เดือน
                mCalendar.get(Calendar.DAY_OF_MONTH),// วัน (1-31)
                false);

        rcStatisticsManual.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcStatisticsManual.setHasFixedSize(true);
        statisticsManualAdapter = new StatisticsManualAdapter();
        statisticsManualAdapter.setData(reportManDaos);
        rcStatisticsManual.setAdapter(statisticsManualAdapter);


        btnStartDate.setOnClickListener(startDateClicklistener);
        btnEndDate.setOnClickListener(endDateClicklistener);
        tvStartDate.addTextChangedListener(startDateListener);
        tvEndDate.addTextChangedListener(endDateListener);
    }

    public static Fragment newInstant() {
        Fragment fragment = new StatisticsManualFragment();
        return fragment;
    }

    private View.OnClickListener startDateClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mDatePicker.setYearRange(2000, 2020);
            mDatePicker.show(getFragmentManager(), "datePicker");
        }
    };

    private View.OnClickListener endDateClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mDatePicker.setYearRange(2000, 2020);
            mDatePicker.show(getFragmentManager(), "datePicker");
        }
    };

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
            //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
            mCalendar.set(year, month, day);
            Date date = mCalendar.getTime();
            //String textDate = dateFormat.format(date);

            DateFormat df = new SimpleDateFormat("d/MM/yyyy");
            final String now = df.format(date);
            Log.d("date", "onDateSet: " + now);
            if ("".equals(tvStartDate.getText().toString())) {
                findDateStart = now;
                tvStartDate.setText(now);
            } else {
                findDateEnd = now;
                tvEndDate.setText(now);
            }
        }
    };

    private TextWatcher startDateListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            startDate = charSequence.toString();
            if(!"".equals(tvStartDate.getText().toString())){
                btnEndDate.setEnabled(true);
            }
            if (!"".equals(startDate) && !"".equals(endDate)) {
                try {
                    getDataForReport(findDateStart,findDateEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                statisticsManualAdapter.setData(reportManDaos);
                statisticsManualAdapter.notifyDataSetChanged();
                tvStartDate.setText("");
                tvEndDate.setText("");
                btnEndDate.setEnabled(false);
//                findDateStart = "";
//                findDateEnd = "";
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private TextWatcher endDateListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            endDate = charSequence.toString();
            if(!"".equals(startDate) && !"".equals(endDate)){
                try {
                    getDataForReport(findDateStart,findDateEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                statisticsManualAdapter.setData(reportManDaos);
                statisticsManualAdapter.notifyDataSetChanged();
                tvStartDate.setText("");
                tvEndDate.setText("");
                btnEndDate.setEnabled(false);
//                findDateStart = "";
//                findDateEnd = "";

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void getDataForReport(String findDateStart, String findDateEnd) throws ParseException {

        DateFormat df = new SimpleDateFormat("d/MM/yyyy");
        Date dateStart = df.parse(findDateStart);
        Date dateEnd = df.parse(findDateEnd);
        reportDaos = realmReport.where(ReportDao.class).between("date",dateStart,dateEnd).findAll();
        reportDaosL = realmReport.where(ReportDao.class).distinct("prodNameRep");
        reportManDaos = new ArrayList<>();
        List<Integer> sum = new ArrayList<>();
        int a;

        Log.d("reportDaosL", "getDataForReportssss: "+reportDaosL.size());

        for(int i=0;i<reportDaosL.size();i++){
            a=0;
            for(int j=0;j<reportDaos.size();j++){
                if(reportDaosL.get(i).getProdNameRep().equals(reportDaos.get(j).getProdNameRep())) {
                    a = a + reportDaos.get(j).getProdQuantityRep();
                }
            }
            sum.add(a);
        }
        if(reportDaos.size() != 0) {
            for (int i = 0; i < reportDaosL.size(); i++) {
                ReportManDao manDao = new ReportManDao();
                manDao.setProdNameRep(reportDaosL.get(i).getProdNameRep());
                manDao.setProdQuantityRep(sum.get(i));
                reportManDaos.add(manDao);
            }
        }

//
//                if(reportDaosL.get(i).getProdNameRep().equals(reportDaos.get(j).getProdNameRep())){
//                    manDao.setProdQuantityRep(reportDaosL.get(i).getProdQuantityRep()+reportDaos.get(j).getProdQuantityRep());
//                }
//                else{
//                    manDao.setProdQuantityRep(reportDaosL.get(i).getProdQuantityRep());
//                }

        Log.d("date", "getDataForReportssss: "+findDateStart);
        Log.d("date", "getDataForReportssss: "+findDateEnd);
        Log.d("date", "getDataForReportwww: "+dateStart);
        Log.d("date", "getDataForReportwww: "+dateEnd);

        Log.d("date", "getDataForReport: "+reportDaos.size());

    }


}
