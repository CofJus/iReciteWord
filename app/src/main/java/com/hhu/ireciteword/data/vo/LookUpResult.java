package com.hhu.ireciteword.data.vo;

import com.hhu.ireciteword.data.VocabularyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * TABLE 查词结果
 * Created by Ji Rui on 2020/4/22
 */

@Table(database = VocabularyDatabase.class)
public class LookUpResult extends BaseModel {

    @PrimaryKey
    private String word;

    @Column
    private String usPhonetic;

    @Column
    private String ukPhonetic;

    @Column
    private String meaning;

    @Column
    private String voiceUrl;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getUsPhonetic() {
        return usPhonetic;
    }

    public void setUsPhonetic(String usPhonetic) {
        this.usPhonetic = usPhonetic;
    }

    public String getUkPhonetic() {
        return ukPhonetic;
    }

    public void setUkPhonetic(String ukPhonetic) {
        this.ukPhonetic = ukPhonetic;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }
}
