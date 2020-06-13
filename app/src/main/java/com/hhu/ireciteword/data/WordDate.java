package com.hhu.ireciteword.data;

import java.io.Serializable;

/**
 * @author Ji Rui
 */

public class WordDate implements Serializable {
    public String word;
    public String phonetic;

    public void setExample(String example) {
        this.example = example;
    }

    public String example;

    public WordDate(String word, String phonetic) {
        this.word = word;
        this.phonetic = phonetic;
    }
}