package com.example.ananpengkhun.myprojectfinal;

import android.content.Context;

/**
 * Created by ananpengkhun on 12/28/16.
 */
public class Contextor {
    private static Contextor ourInstance;

    private Context mContext;
    public static Contextor getInstance() {
        if(ourInstance == null){
            ourInstance = new Contextor();
        }
        return ourInstance;
    }

    private Contextor() {
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
