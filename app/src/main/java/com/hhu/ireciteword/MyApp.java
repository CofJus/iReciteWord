package com.hhu.ireciteword;

import android.app.Application;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MyApp extends Application {

    /** 每日目标 */
    private static int target;

    /** 当前选择的词汇书 */
    private static String wordBook;

    public String getWordBook() {
        return wordBook;
    }

    public void setWordBook(String wordBook) {
        wordBook = wordBook;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        target = target;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //复制assets目录下的数据库文件到应用数据库中
        try {
            copyDataBase("vocabulary.db");
        } catch (Exception e) {
            Log.e("Application", e.getMessage());
        }

        FlowManager.init(new FlowConfig.Builder(this).build());
        //设置日志显示
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }

    private void copyDataBase(String dbname) throws IOException {
        // Open your local db as the input stream
        InputStream myInput = this.getAssets().open(dbname);
        // Path to the just created empty db
        File outFileName = this.getDatabasePath(dbname);

        if (!outFileName.exists()) {
            outFileName.getParentFile().mkdirs();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
    }
}