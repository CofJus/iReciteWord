package com.hhu.ireciteword.data;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = WordsDatabase.class)
public class Test extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String result;

    public void setID(int id){
        this.id=id;
    }

    public void setResult(String result){
        this.result=result;
    }
}