package com.example.ananpengkhun.myprojectfinal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ananpengkhun.myprojectfinal.fragment.SelectStatisticsFragment;
import com.example.ananpengkhun.myprojectfinal.fragment.StatisticsFragment;
import com.example.ananpengkhun.myprojectfinal.fragment.StatisticsManualFragment;

/**
 * Created by ananpengkhun on 5/21/17.
 */

public class ReportMainAdapter extends FragmentStatePagerAdapter{

    public ReportMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0){
            fragment = SelectStatisticsFragment.newInstant();
        }else if(position == 1){
            fragment = StatisticsFragment.newInstant();
        }else if(position == 2){
            fragment = StatisticsManualFragment.newInstant();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
