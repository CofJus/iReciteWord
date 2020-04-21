package com.hhu.ireciteword;

import android.app.Application;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        Log.i("SUCCESS","INITIAL");
    }
}
