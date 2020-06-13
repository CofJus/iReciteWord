package com.hhu.ireciteword.ui;

/*
 * Created by 李雪滢 on 2020.4.16
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;
import com.hhu.ireciteword.data.WordDate;

import java.io.Serializable;

public class Word_recite1 extends AppCompatActivity {

    private boolean flag = true;

    private static final String WORD_LIST = "wordList";

    TextView mLabel;
    TextView mLabel2;
    TextView phoneticText;

    ImageButton btnBack;
    ImageButton btnSearch;
    Button btnRemember1;
    Button btnForget1;

    WordDate wordDate;

    @Override
    @SuppressLint({"WrongViewCast", "HandlerLeak"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_recite1);

        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wordDate = (WordDate) getIntent().getSerializableExtra(WORD_LIST);
                sendWord(wordDate);
            }
        }).start();

        //通过按钮back1，跳转到上一个页面，主界面page_main
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //通过按钮search,跳转到查单词界面，search_word
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Word_recite1.this, Search_word.class);
                startActivity(it);
                Toast.makeText(Word_recite1.this, "你进入下一个界面", Toast.LENGTH_LONG).show();
            }
        });

        //通过按钮remember1,跳转到单词详情页word_information
        btnRemember1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Word_recite1.this, Word_information.class);
                it.putExtra("information", (Serializable) wordDate);
                startActivity(it);
            }
        });

        btnForget1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                Message msg = new Message();
                msg.obj = wordDate;
                handler.sendMessage(msg);
            }
        });
    }

    private void initView() {
        mLabel = (TextView) findViewById(R.id.wordview);
        mLabel2 = (TextView) findViewById(R.id.phonetic_sign);
        phoneticText = (TextView) findViewById((R.id.phoneticText));

        btnBack = (ImageButton) findViewById(R.id.back1);
        btnSearch = (ImageButton) findViewById(R.id.search);
        btnRemember1 = (Button) findViewById(R.id.remember1);
        btnForget1 = (Button) findViewById(R.id.forget1);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            WordDate wordDate = (WordDate) msg.obj;
            mLabel.setText(wordDate.getWord());
            mLabel2.setText(wordDate.getPhonetic());
            if (!flag) {
                phoneticText.setText(wordDate.getExample());
            }
        }
    };

    private void sendWord(WordDate wordDate) {
        Message msg = new Message();
        msg.obj = wordDate;
        handler.sendMessage(msg);
    }
}
