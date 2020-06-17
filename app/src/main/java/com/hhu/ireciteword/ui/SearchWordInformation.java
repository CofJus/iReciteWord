package com.hhu.ireciteword.ui;

/*
 * Created by 李雪滢 on 2020.4.16
 */
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;
import com.hhu.ireciteword.data.WordDate;

import static com.hhu.ireciteword.utils.VoicePlayer.playVoice;

public class SearchWordInformation extends AppCompatActivity {

    TextView wordView;
    TextView phoneticView;
    TextView exampleView;
    ImageView voice;

    WordDate wordDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_word_information);

        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wordDate = (WordDate) getIntent().getSerializableExtra("result");
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
                        System.out.println(wordDate.getUrl());
                        playVoice(wordDate.getUrl());
                    }
                }).start();
            }
        });
    }

    private void initView() {
        wordView = findViewById(R.id.word2);
        phoneticView = findViewById(R.id.phoneticView2);
        exampleView = findViewById(R.id.exampleView2);
        voice = findViewById(R.id.audio3);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            WordDate wordDate = (WordDate) msg.obj;
            //wordView.setText(wordDate.getWord());
            //phoneticView.setText(wordDate.getPhonetic());
            //exampleView.setText(wordDate.getExample());
        }
    };

    private void sendMessage(WordDate wordDate) {
        Message msg = new Message();
        msg.obj = wordDate;
        handler.sendMessage(msg);
    }
}