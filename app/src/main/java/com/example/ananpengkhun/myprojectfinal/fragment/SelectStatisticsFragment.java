package com.example.ananpengkhun.myprojectfinal.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ananpengkhun.myprojectfinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectStatisticsFragment extends Fragment {


    @BindView(R.id.btn_only_day) Button btnOnlyDay;
    @BindView(R.id.btn_manual_day) Button btnManualDay;

    SelectPage selectPage;

    public interface SelectPage {
        void dayAll();

        void dayManual();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        selectPage = (SelectPage) context;
    }

    public SelectStatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_select_statistics, container, false);
        ButterKnife.bind(this, v);
//        btnOnlyDay = (Button) v.findViewById(R.id.btn_only_day);
//        btnManualDay = (Button) v.findViewById(R.id.btn_manual_day);
        return v;
    }

    public static Fragment newInstant() {
        Fragment fragment = new SelectStatisticsFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        btnOnlyDay.setOnClickListener(DayAllClicklistener);
        btnManualDay.setOnClickListener(DayManualClicklistener);
    }

    private View.OnClickListener DayAllClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            selectPage.dayAll();
        }
    };

    private View.OnClickListener DayManualClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            selectPage.dayManual();
        }
    };
}
