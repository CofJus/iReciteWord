package com.hhu.ireciteword.utils;

import com.hhu.ireciteword.data.Test;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * 基本CRUD
 * Created by Ji Rui on 2020/4/22
 */

//TODO basic CRUD
public class CRUDUtils {

    public static void insert(){
        /* 插入方式
         * SQLite.insert(Test.class)
                .columns(Test_Table.id,Test_Table.result)
                .values(133,"FUCK")
                .execute();
         */
        Test test=new Test();
        test.setId(987);
        test.setResult("SUCCESS");
        test.save();
    }

    public static void query(){
        /* 查询 */
        List<Test> tests = SQLite.select().
                from(Test.class).queryList();
    }

}
