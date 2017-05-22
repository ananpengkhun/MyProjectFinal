package com.example.ananpengkhun.myprojectfinal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.adapter.ReportMainAdapter;
import com.example.ananpengkhun.myprojectfinal.customview.NonSwipeViewPager;
import com.example.ananpengkhun.myprojectfinal.fragment.SelectStatisticsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportMainActivity extends AppCompatActivity implements SelectStatisticsFragment.SelectPage {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.vp_pager_fragment) NonSwipeViewPager vpPagerFragment;
    @BindView(R.id.activity_report_manual) RelativeLayout activityReportManual;
    @BindView(R.id.tv_toolbar) TextView tvToolbar;

    private ReportMainAdapter reportMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_manual);
        ButterKnife.bind(this);
        BindWidget();
        init();
    }

    private void init() {
        reportMainAdapter = new ReportMainAdapter(getSupportFragmentManager());
        vpPagerFragment.setAdapter(reportMainAdapter);

    }

    private void BindWidget() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void dayAll() {
        vpPagerFragment.setCurrentItem(1, true);
        tvToolbar.setText("ส่งออกสินค้ารายวัน");
    }

    @Override
    public void dayManual() {
        vpPagerFragment.setCurrentItem(2, true);
        tvToolbar.setText("ส่งออกสินค้าแบบกำหนดเอง");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
