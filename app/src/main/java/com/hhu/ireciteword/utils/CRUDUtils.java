package com.hhu.ireciteword.utils;

import android.util.Log;

import com.hhu.ireciteword.data.LookUpResult;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * 基本CRUD
 * @author Ji Rui
 */

//TODO basic CRUD
public class CRUDUtils {

    /**
     * 查词结果insert
     * @param result 依次为原词，美音标，英音标，释义，发音地址
     */
    public static void insert(String[] result){

        LookUpResult lookUpResult=new LookUpResult();
        Log.i("SUCCESS","CREATE TABLE_LOOKUPRESULT.");
        lookUpResult.setWord(result[0]);
        lookUpResult.setUsPhonetic(result[1]);
        lookUpResult.setUkPhonetic(result[2]);
        lookUpResult.setMeaning(result[3]);
        lookUpResult.setVoiceURL(result[4]);
        lookUpResult.save();
    }

    /**
     * 优先从已查询的单词中获取信息
     * @return 列表
     */
    public static List<LookUpResult> query(){
        List<LookUpResult> lookUpResults = SQLite.select().
                from(LookUpResult.class).queryList();
        return lookUpResults;
    }

    /**
     * 随机弹出一行
     */
    public static void randomQuery(){
        // SELECT * FROM Note order by RANDOM() limit 1;
    }
}