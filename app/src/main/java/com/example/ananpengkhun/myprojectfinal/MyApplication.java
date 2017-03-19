package com.example.ananpengkhun.myprojectfinal;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ananpengkhun on 12/28/16.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().setmContext(getApplicationContext());
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Realm.init(getApplicationContext());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

