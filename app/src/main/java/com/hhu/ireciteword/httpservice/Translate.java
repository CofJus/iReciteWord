package com.hhu.ireciteword.httpservice;


import org.json.JSONException;

import static com.hhu.ireciteword.httpservice.TranslateAPI.request;
import static com.hhu.ireciteword.utils.ParseJSON.parseJSON;

public class Translate {
    public static String translateResult(String tar) throws JSONException {

        /* APIKEY */
        String httpUrl = "https://api.tianapi.com/txapi/enwords/index?key=de4c207c3be22ce49296580edc03cfc6";
        String jsonResult=request(httpUrl,tar);

        /* JSON解析实例,用于离线测试,不要浪费次数>_< */
        //String jsonResult="{\"code\":200,\"msg\":\"success\",\"newslist\":[{\"word\":\"ass\",\"content\":\"n:驴,驴子,傻瓜,傻子,蠢人,笨蛋,屁股,蠢驴\"}]}";

        return parseJSON(jsonResult);
    }
}
