package com.hhu.ireciteword;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.httpservice.translate.Translate;

import static com.hhu.ireciteword.utils.CrudUtils.insert;
import static com.hhu.ireciteword.utils.VoicePlayer.playVoice;

/**
 * 查词、翻译 Activity
 * @author CofJu
 * @date 2020/4/11
 */
public class TranslateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Translate ts = new Translate();
                        //获取输入
                        //String jsonResult=ts.getJSONResult("example");
                        /* 离线测试，次数很少 */
                        String jsonResult = "{\"tSpeakUrl\":\"http://openapi.youdao.com/ttsapi?q=%E4%BE%8B%E5%AD%90&langType=zh-CHS&sign=53DE4780E8BC1A9C98A8F051EC6BEDE2&salt=1586596560219&voice=4&format=mp3&appKey=2a8946073e30bca5\",\"returnPhrase\":[\"example\"],\"web\":[{\"value\":[\"例子\",\"榜样\",\"实例\",\"范例\"],\"key\":\"Example\"},{\"value\":[\"反面教材\",\"反例\",\"反面例子\",\"反面典型\"],\"key\":\"Negative Example\"},{\"value\":[\"根据示例查询\"],\"key\":\"Example queries\"}],\"query\":\"example\",\"translation\":[\"例子\"],\"errorCode\":\"0\",\"dict\":{\"url\":\"yddict://m.youdao.com/dict?le=eng&q=example\"},\"webdict\":{\"url\":\"http://m.youdao.com/dict?le=eng&q=example\"},\"basic\":{\"exam_type\":[\"高中\",\"初中\",\"商务英语\"],\"us-phonetic\":\"ɪɡˈzæmpl\",\"phonetic\":\"ɪɡˈzɑːmpl\",\"uk-phonetic\":\"ɪɡˈzɑːmpl\",\"wfs\":[{\"wf\":{\"name\":\"过去式\",\"value\":\"exampled\"}},{\"wf\":{\"name\":\"过去分词\",\"value\":\"exampled\"}},{\"wf\":{\"name\":\"现在分词\",\"value\":\"exampling\"}},{\"wf\":{\"name\":\"复数\",\"value\":\"examples\"}}],\"uk-speech\":\"http://openapi.youdao.com/ttsapi?q=example&langType=en&sign=173291A4C4933C349D341B6C9112407A&salt=1586596560219&voice=5&format=mp3&appKey=2a8946073e30bca5\",\"explains\":[\"n. 例子；榜样\",\"vt. 作为…的例子；为…做出榜样\",\"vi. 举例\"],\"us-speech\":\"http://openapi.youdao.com/ttsapi?q=example&langType=en&sign=173291A4C4933C349D341B6C9112407A&salt=1586596560219&voice=6&format=mp3&appKey=2a8946073e30bca5\"},\"l\":\"en2zh-CHS\",\"speakUrl\":\"http://openapi.youdao.com/ttsapi?q=example&langType=en&sign=173291A4C4933C349D341B6C9112407A&salt=1586596560219&voice=4&format=mp3&appKey=2a8946073e30bca5\"}";

                        Log.i("jsonResult", jsonResult);
                        Log.i("Query", ts.getQuery(jsonResult));
                        Log.i("Explain", ts.getMeaning(jsonResult));
                        Log.i("Result", ts.getResult(jsonResult));
                        Log.i("Terms", ts.getTerms(jsonResult));
                        Log.i("Voice", ts.getVoiceURL(jsonResult));
                        Log.i("Phonetic", ts.getPhonetic(jsonResult, "uk-"));

                        //将http链接转换为https
                        String url = "https" + ts.getVoiceURL(jsonResult).substring(4);

                        //播放音频
                        playVoice(url);

                        String[] result = {ts.getQuery(jsonResult),
                                ts.getPhonetic(jsonResult, "us-"),
                                ts.getPhonetic(jsonResult, "uk-"),
                                ts.getMeaning(jsonResult),
                                url};
                        //插入查询得到的信息
                        insert(result);
                    }
                }).start();
            }
        });
    }
}
