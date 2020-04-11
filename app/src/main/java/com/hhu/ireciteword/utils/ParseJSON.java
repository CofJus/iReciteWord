package com.hhu.ireciteword.utils;

import org.json.*;

/**
 * Created by Ji Rui on 2020/4/7
 * 解析JSON
 */
public class ParseJSON {

    /**
     * @param jsonResult jsonResult
     * @param type English,Chinese,TTS,IMG,Date
     * @return String,URL of TTS or IMG
     */
    public static String parseJSON(String jsonResult,String type) {
        try {
            JSONObject jsonObj = new JSONObject(jsonResult);
            JSONArray jsonArray = null;
            jsonArray = jsonObj.getJSONArray("newslist");
            return jsonArray.getJSONObject(0).getString(type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}