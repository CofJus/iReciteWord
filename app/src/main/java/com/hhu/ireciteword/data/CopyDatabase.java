package com.hhu.ireciteword.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.hhu.ireciteword.R;

/**
 * Created by Ji Rui on 2020/4/12
 */
public class CopyDatabase {
    /* Git Push Test */
    private final int BUFFER_SIZE = 400000;
    private static final String DB_NAME = "words.db"; //保存的数据库文件名
    private static final String PACKAGE_NAME = "com.hhu.ireciteword";//包名
    private static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME + "/databases";  //存放数据库的位置

    private SQLiteDatabase database;
    private Context context;

    public CopyDatabase(Context context){
        this.context = context;
    }
    public void openDatabase() {
        File dFile=new File(DB_PATH);//判断路径是否存在，不存在则创建路径
        if (!dFile.exists()) {
            dFile.mkdir();
        }
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    /* 导入已有的数据库 */
    private SQLiteDatabase openDatabase(String dbfile) {
        try {
            Log.i("Start","start copy database");
            if (!(new File(dbfile).exists())) {
                InputStream is = this.context.getResources().openRawResource(
                        R.raw.words);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            Log.i("End","end");
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
            return db;
        }catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }
    public void closeDatabase() {
        this.database.close();
    }
}
