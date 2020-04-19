package com.hhu.ireciteword.data;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = WordsDatabase.NAME, version = WordsDatabase.VERSION)
public class WordsDatabase {

    public static final String NAME = "words.db";

    public static final int VERSION = 1;
}