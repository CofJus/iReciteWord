package com.hhu.ireciteword;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.data.dao.LookUpResultDao;
import com.hhu.ireciteword.data.vo.LookUpResult;
import com.hhu.ireciteword.httpservice.translate.Translate;

import static com.hhu.ireciteword.data.DaoFactory.getLookUpResultDaoInstance;
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
                        Translate translate = new Translate();
                        //包含了所有查询得到的信息
                        LookUpResult lookUpResult = translate.getResult("example", "");
                        //将http链接转换为https
                        lookUpResult.setVoiceUrl("https" + lookUpResult.getVoiceUrl().substring(4));
                        //播放音频
                        playVoice(lookUpResult.getVoiceUrl());
                        //插入查询得到的信息
                        LookUpResultDao lookUpResultDao=getLookUpResultDaoInstance();
                        lookUpResultDao.insert(lookUpResult);
                    }
                }).start();
            }
        });
    }
}
