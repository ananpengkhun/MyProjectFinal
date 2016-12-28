package com.example.ananpengkhun.myprojectfinal.httpmanager;

import android.content.Context;

import com.example.ananpengkhun.myprojectfinal.Contextor;
import com.example.ananpengkhun.myprojectfinal.httpmanager.httpinterface.DataInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ananpengkhun on 12/28/16.
 */
public class DataHttpManager {
    private static DataHttpManager ourInstance;
    private Context mContext;
    private DataInterface dataInterface;
    public static DataHttpManager getInstance() {
        if(ourInstance == null){
            ourInstance = new DataHttpManager();
        }
        return ourInstance;
    }

    private DataHttpManager() {
        mContext = Contextor.getInstance().getmContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.106:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataInterface = retrofit.create(DataInterface.class);

    }

    public DataInterface getDataInterface() {
        return dataInterface;
    }
}
