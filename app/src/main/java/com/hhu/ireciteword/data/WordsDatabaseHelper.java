package com.hhu.ireciteword.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordsDatabaseHelper extends SQLiteOpenHelper {

    /* 数据库名 */
    public static final String DATABASE_NAME="words.db";

    /* 表名 */
    public static final String TEST_TABLE_NAME="Test";
    public static final String NOTE_TABLE_NAME="Note";

    private static final int DATABASE_VERSION=1;

    public WordsDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TEST_TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + " result TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
