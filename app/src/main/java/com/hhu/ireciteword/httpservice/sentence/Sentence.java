package com.hhu.ireciteword.httpservice.sentence;

import org.json.JSONException;

import static com.hhu.ireciteword.httpservice.sentence.SentenceAPI.request;
import static com.hhu.ireciteword.utils.ParseJSON.parseJSON;

/**
 * Create by Ji Rui on 2020/4/10
 */
public class Sentence {

    final private String API_KEY="de4c207c3be22ce49296580edc03cfc6";
    final private String httpUrl = "https://api.tianapi.com/txapi/everyday/index?key=";
    private String jsonResult = request(httpUrl,API_KEY);

    public String getEnglish() throws JSONException {
        return parseJSON(jsonResult,"content");
    }

    public String getChinese() throws JSONException {
        return parseJSON(jsonResult,"note");
    }

    public String getTTS() throws JSONException {
        return parseJSON(jsonResult,"tts");
    }

    public String getImg() throws JSONException {
        return parseJSON(jsonResult,"imgurl");
    }

    public String getDate() throws JSONException {
        return parseJSON(jsonResult,"date");
    }
}
