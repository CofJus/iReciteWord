package com.hhu.ireciteword.httpservice.translate;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ji Rui on 2020/4/11
 */

public class Translate {
    private String jsonResult;

    private void setjsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }
    
    //获取原词
    public String getQuery(String json) {
        try {
            JSONObject obj=new JSONObject(json);
            return obj.getString("query");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取翻译结果
    public String getResult(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray array = obj.getJSONArray("translation");
            String result="";
            for (int i=0;i<array.length();i++){
                String tran=array.getString(i);
                result="\n"+tran;
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //获取释义
    public String getMeaning(String json) {
        try {
            JSONObject obj=new JSONObject(json);
            JSONObject obj1=obj.getJSONObject("basic");
            JSONArray array=obj1.getJSONArray("explains");
            StringBuilder ttt= new StringBuilder();
            for(int i=0;i<array.length();i++){
                ttt.insert(0, array.getString(i) + "\n");
            }
            return ttt.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTerms(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray array = obj.getJSONArray("web");
            StringBuilder lll= new StringBuilder();
            for (int i=0;i<array.length();i++){
                obj = array.getJSONObject(i);
                String word = obj.getString("value");
                String key=obj.getString("key");
                lll.insert(0, key + " : " + word + "\n");
            }
            return lll.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //获取发音URL
    public String getVoiceURL(String json) {
        try {
            JSONObject obj=new JSONObject(json);
            return obj.getString("speakUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param json jsonResult
     * @param type type="-us"(美音),type="-uk"(英音),type=""(默认)
     * @return uk-phonetic or us-phonetic
     */
    public String getPhonetic(String json,String type) {
        try {
            type=type+"phonetic";
            JSONObject obj=new JSONObject(json);
            JSONObject jsonData = obj.getJSONObject("basic");
            return jsonData.getString(type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getJSONResult(String word){

        TranslateAPI api = new TranslateAPI();
        word = "example";

        /*
         * 过滤换行符，正则
         * 暂时用不上，读取用户输入时使用
         */
        Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");
        Matcher m = CRLF.matcher(word);
        if (m.find()) {
            word = m.replaceAll("");
        }

        //要翻译的内容
        api.setQuery(word);
        String URLResult = "";
        try {
            URLResult = api.requestUrl();
            Log.d("请求的完整url：", URLResult);
            String jsonResult = api.Result();
            setjsonResult(jsonResult);
            Log.d("返回的json：", jsonResult);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
