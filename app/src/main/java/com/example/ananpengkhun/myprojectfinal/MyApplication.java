package com.example.ananpengkhun.myprojectfinal;

import android.app.Application;
import android.content.Context;

/**
 * Created by ananpengkhun on 12/28/16.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().setmContext(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

