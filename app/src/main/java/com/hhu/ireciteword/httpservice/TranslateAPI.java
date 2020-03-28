package com.hhu.ireciteword.httpservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TranslateAPI{

    /* API接口文档 https://www.tianapi.com/apiview/49 */

    public static String request(String httpUrl,String tar) {
        BufferedReader reader = null;
        String result = null;
        StringBuilder sbf = new StringBuilder();
        httpUrl = httpUrl+"&word="+tar;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}