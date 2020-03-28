package com.hhu.ireciteword.utils;

import org.json.*;

public class ParseJSON {

    /* 解析JSON */
    public static String parseJSON(String jsonResult) throws JSONException {
        JSONObject jsonObj = new JSONObject(jsonResult);
        JSONArray jsonArray = jsonObj.getJSONArray("newslist");

        /* 取消注释可获取错误码 */
        //int code=jsonObj.getInt("code");

        return jsonArray.getJSONObject(0).getString("content");
    }
}