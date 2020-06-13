package com.hhu.ireciteword.data;

import java.io.Serializable;

/**
 * @author Ji Rui
 */

public class WordDate implements Serializable {
    private String word;
    private String phonetic;
    private String example;

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
}