package com.hhu.ireciteword.data;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * words.db
 * Created by Ji Rui on 2020/4/19
 */

@Database(name = WordsDatabase.NAME, version = WordsDatabase.VERSION)
public class WordsDatabase {

    public static final String NAME = "words";

    public static final int VERSION = 2;
}