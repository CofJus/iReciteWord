package com.hhu.ireciteword.data;

import com.hhu.ireciteword.data.vo.Cet4;
import com.hhu.ireciteword.data.vo.Cet6;
import com.hhu.ireciteword.data.vo.LookUpResult;

import java.io.Serializable;

/**
 * @author Ji Rui
 */

public class WordDate implements Serializable {
    private String word;
    private String phonetic;
    private String meaning;
    private String example;
    private String url;

    public WordDate(Cet4 cet4) {
        this.word=cet4.getWord();
        this.phonetic=cet4.getPhonogram();
        this.meaning = cet4.getInterpretation();
        this.example=cet4.getExample();
    }

    public WordDate(Cet6 cet6) {
        this.word=cet6.getWord();
        this.phonetic=cet6.getPhonogram();
        this.meaning=cet6.getInterpretation();
        this.example=cet6.getExample();
    }

    public WordDate(LookUpResult lookUpResult) {
        this.word = lookUpResult.getWord();
        this.phonetic = lookUpResult.getUkPhonetic();
        this.meaning=lookUpResult.getMeaning();
        this.url = lookUpResult.getVoiceUrl();
    }
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}