package com.hhu.ireciteword.ui;
/*
 * Created by 李雪滢 on 2020.4.16
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.MainActivity;
import com.hhu.ireciteword.MyApp;
import com.hhu.ireciteword.R;
import com.hhu.ireciteword.data.WordDate;
import com.hhu.ireciteword.data.dao.Cet4Dao;
import com.hhu.ireciteword.data.vo.Cet4;

import java.io.Serializable;
import java.util.List;

import static com.hhu.ireciteword.data.DaoFactory.getCet4DaoInstance;
import static com.hhu.ireciteword.utils.VoicePlayer.playVoice;

//活动：单词详情页
public class Word_information extends AppCompatActivity {

    TextView wordView;
    TextView phoneticView;
    TextView exampleView;
    TextView meaningView;
    ImageView voice;

    WordDate wordDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_information);

        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wordDate = (WordDate) getIntent().getSerializableExtra("information");
                sendMessage(wordDate);
            }
        }).start();

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        playVoice(wordDate.getUrl());
                    }
                }).start();
            }
        });
        findViewById(R.id.back2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Word_information.this, com.hhu.ireciteword.MainActivity.class);
                startActivity(it);
                Toast.makeText(Word_information.this, "你进入下一个界面", Toast.LENGTH_LONG).show();
            }
        });
        //通过按钮search,跳转到查单词界面，search_word
        findViewById(R.id.search2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Word_information.this, SearchWord.class);
                startActivity(it);
                Toast.makeText(Word_information.this, "你进入下一个界面", Toast.LENGTH_LONG).show();
            }
        });

        //通过按钮"下一个"，进入下一个单词界面,Word_recite1.java
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it;
                SharedPreferences myPreference = getSharedPreferences("iReciteWord", MODE_PRIVATE);
                //TODO 查询下一个单词并发送到背单词Activity，计数并与目标比较
                if (MyApp.cur <= 10) {
                    MyApp.cur++;
                    Cet4Dao cet4Dao = getCet4DaoInstance();
                    List<Cet4> list = cet4Dao.randomQuery(1);
                    it = new Intent(Word_information.this, Word_recite1.class);
                    WordDate wordDate = new WordDate(list.get(0));
                    it.putExtra("wordList", (Serializable) wordDate);
                    startActivity(it);
                } else {
                    MyApp.cur = 0;
                    it = new Intent(Word_information.this, MainActivity.class);
                    startActivity(it);
                }
            }
        });
    }

    private void initView() {
        wordView = findViewById(R.id.word);
        phoneticView = findViewById(R.id.phoneticView);
        exampleView = findViewById(R.id.exampleView);
        meaningView = findViewById(R.id.meaning);
        voice = findViewById(R.id.audio2);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            WordDate wordDate = (WordDate) msg.obj;
            wordView.setText(wordDate.getWord());
            phoneticView.setText(wordDate.getPhonetic());
            meaningView.setText(wordDate.getMeaning());
            exampleView.setText(wordDate.getExample());
        }
    };

    private void sendMessage(WordDate wordDate) {
        Message msg = new Message();
        msg.obj = wordDate;
        handler.sendMessage(msg);
    }
}
