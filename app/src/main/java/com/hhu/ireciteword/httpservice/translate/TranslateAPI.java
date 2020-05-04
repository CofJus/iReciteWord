package com.hhu.ireciteword.httpservice.translate;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.hhu.ireciteword.httpservice.translate.HttpGet.get;

/**
 * @author Ji Rui
 */

public class TranslateAPI extends AppCompatActivity {

    private final String url="https://openapi.youdao.com/api?";
    private final String appId ="2a8946073e30bca5";
    private final String password="TMZsZ2QyP0lRUGzHl5ytHACKDjUUlarO";
    /**
     * 随机数
     */
    private int salt = 2;

    private String query;
    //签名  加密后的字符串
    private String sign;

    /**
     * 加密前的原文
     */
    private String src;

    private String from="EN";

    private String to="zh-CHS";

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    //获取要翻译的内容
    public void setQuery(String query) {
        this.query = query;
        src= appId +query+salt+password;
    }

    /****
     * 获取json返回的数据
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String Result() throws NoSuchAlgorithmException {
        String url=requestUrl();
        com.hhu.ireciteword.httpservice.translate.HttpGet httpGet=new com.hhu.ireciteword.httpservice.translate.HttpGet();
        String result=get(url,null);
        assert result != null;
        Log.d("获取结果：",result);
        //JsonToString(result);
        return result;
    }


    /***
     * 获得完整的请求url
     * @return 完整的请求url
     * @throws NoSuchAlgorithmException
     */
    public String requestUrl() throws NoSuchAlgorithmException {

        String sign=stringToMD5(src);
        Log.d("MD5检验：",src);
        String requesturl=url+"q="+query+"&from="+from+"&to="+to+"&appKey="+ appId +"&salt="+salt+"&sign="+sign;
        return requesturl;
    }

    /***
     * 获取加密后的字符串
     * @param string 加密前的原文
     * @return  加密后的字符串
     */
    public static String stringToMD5(String string) {//MD5加密
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }

    /***
     * 解析JSON  获取翻译结果
     * @return   翻译后的结果
     */
    public String JsonToString(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray array = obj.getJSONArray("trans_result");
            obj = array.getJSONObject(0);
            String word = obj.getString("dst");
            Log.d("翻译结果",word);
            return word;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
