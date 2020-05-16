package com.hhu.ireciteword.data;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name= VocabularyDatabase.NAME,version = VocabularyDatabase.VERSION)
public class VocabularyDatabase {
    public static final String NAME = "vocabulary";

    public static final int VERSION = 1;
}
