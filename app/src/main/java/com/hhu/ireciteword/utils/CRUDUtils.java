package com.hhu.ireciteword.utils;

import com.hhu.ireciteword.data.Test;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class CRUDUtils {
    public static void insert(){
        Test test = new Test();
        test.setID(1203);
        test.setResult("SUCCESS");
        test.save();
    }
    public static void select(){
        List<Test> products = SQLite.select()
                .from(Test.class)
                .where()
                .queryList();
    }
}
