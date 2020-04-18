package com.hhu.ireciteword.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class WordsContract {

    private WordsContract(){}

    //为Note表定义列常量
    public static final class TestEntry implements BaseColumns{
        /**
         * table name
         */
        public static final String COLUMN_NAME = "Test";

        public static final String COLUMN_ID= "_id";
        public static final String COLUMN_RESULT = "result";
        public static final String CONTENT_AUTHORITY = "com.hhu.irecitewoed";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final String PATH_NOTE = "Test";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTE);

        /**
         * Possible values for the gender
         */

    }

}
