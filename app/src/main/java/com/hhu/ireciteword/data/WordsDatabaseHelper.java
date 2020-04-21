package com.hhu.ireciteword.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.structure.database.DatabaseHelperListener;
import com.raizlabs.android.dbflow.structure.database.FlowSQLiteOpenHelper;

public class WordsDatabaseHelper extends FlowSQLiteOpenHelper {

    /* 数据库名 */
    public static final String DATABASE_NAME="words.db";

    /* 表名 */
    public static final String TEST_TABLE_NAME="Test";
    public static final String NOTE_TABLE_NAME="Note";

    private static final int DATABASE_VERSION=1;


    public WordsDatabaseHelper(@NonNull DatabaseDefinition databaseDefinition, @NonNull DatabaseHelperListener listener) {
        super(databaseDefinition, listener);
    }
}
