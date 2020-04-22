package com.hhu.ireciteword.data;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * 测试用表
 * Created by Ji Rui on 2020/4/20
 */

@Table(database = WordsDatabase.class)
public class Test extends BaseModel {

    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    private String result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}