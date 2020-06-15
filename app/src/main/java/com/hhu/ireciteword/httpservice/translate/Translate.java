package com.hhu.ireciteword.httpservice.translate;


import android.util.Log;

import com.hhu.ireciteword.data.vo.LookUpResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ji Rui
 * @date 2020/4/11
 */

public class Translate {
    private String jsonResult;

    private void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    private static final Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");

    public LookUpResult getResult(String word,String type) {
        LookUpResult lookUpResult=new LookUpResult();
        /* 离线测试 */
        //String jsonResult = "{\"tSpeakUrl\":\"http://openapi.youdao.com/ttsapi?q=%E4%BE%8B%E5%AD%90&langType=zh-CHS&sign=53DE4780E8BC1A9C98A8F051EC6BEDE2&salt=1586596560219&voice=4&format=mp3&appKey=2a8946073e30bca5\",\"returnPhrase\":[\"example\"],\"web\":[{\"value\":[\"例子\",\"榜样\",\"实例\",\"范例\"],\"key\":\"Example\"},{\"value\":[\"反面教材\",\"反例\",\"反面例子\",\"反面典型\"],\"key\":\"Negative Example\"},{\"value\":[\"根据示例查询\"],\"key\":\"Example queries\"}],\"query\":\"example\",\"translation\":[\"例子\"],\"errorCode\":\"0\",\"dict\":{\"url\":\"yddict://m.youdao.com/dict?le=eng&q=example\"},\"webdict\":{\"url\":\"http://m.youdao.com/dict?le=eng&q=example\"},\"basic\":{\"exam_type\":[\"高中\",\"初中\",\"商务英语\"],\"us-phonetic\":\"ɪɡˈzæmpl\",\"phonetic\":\"ɪɡˈzɑːmpl\",\"uk-phonetic\":\"ɪɡˈzɑːmpl\",\"wfs\":[{\"wf\":{\"name\":\"过去式\",\"value\":\"exampled\"}},{\"wf\":{\"name\":\"过去分词\",\"value\":\"exampled\"}},{\"wf\":{\"name\":\"现在分词\",\"value\":\"exampling\"}},{\"wf\":{\"name\":\"复数\",\"value\":\"examples\"}}],\"uk-speech\":\"http://openapi.youdao.com/ttsapi?q=example&langType=en&sign=173291A4C4933C349D341B6C9112407A&salt=1586596560219&voice=5&format=mp3&appKey=2a8946073e30bca5\",\"explains\":[\"n. 例子；榜样\",\"vt. 作为…的例子；为…做出榜样\",\"vi. 举例\"],\"us-speech\":\"http://openapi.youdao.com/ttsapi?q=example&langType=en&sign=173291A4C4933C349D341B6C9112407A&salt=1586596560219&voice=6&format=mp3&appKey=2a8946073e30bca5\"},\"l\":\"en2zh-CHS\",\"speakUrl\":\"http://openapi.youdao.com/ttsapi?q=example&langType=en&sign=173291A4C4933C349D341B6C9112407A&salt=1586596560219&voice=4&format=mp3&appKey=2a8946073e30bca5\"}";
        String jsonResult=this.getJsonResult(word);
        lookUpResult.setWord(this.getQuery(jsonResult));
        lookUpResult.setMeaning(this.getMeaning(jsonResult));
        lookUpResult.setUkPhonetic(this.getPhonetic(jsonResult, type));
        lookUpResult.setUsPhonetic(this.getPhonetic(jsonResult, type));
        lookUpResult.setVoiceUrl(this.getVoiceUrl(jsonResult));
        return lookUpResult;
    }

    private String getQuery(String json) {
        try {
            JSONObject obj=new JSONObject(json);
            return obj.getString("query");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getResult(String json) {
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

    private String getMeaning(String json) {
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

    private String getVoiceUrl(String json) {
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
    private String getPhonetic(String json,String type) {
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

    private String getJsonResult(String word){

        TranslateAPI api = new TranslateAPI();

        /* 过滤换行符，正则 */
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
            setJsonResult(jsonResult);
            Log.d("返回的json：", jsonResult);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
