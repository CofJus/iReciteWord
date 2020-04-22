package com.hhu.ireciteword.data;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
/**
 * TABLE 四级词库
 * Created by Ji Rui on 2020/4/22
 */

@Table(database = WordsDatabase.class)
public class Note extends BaseModel {

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    @PrimaryKey
    public String words;

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Column
    public String meaning;

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    @Column
    public String lx;
}
