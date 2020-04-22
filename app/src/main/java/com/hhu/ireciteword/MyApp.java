package com.hhu.ireciteword;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        //设置日志显示
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }
}
