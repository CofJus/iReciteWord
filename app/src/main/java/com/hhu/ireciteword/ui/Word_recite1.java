package com.hhu.ireciteword.ui;

/*
 * Created by 李雪滢 on 2020.4.16
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.hhu.ireciteword.data.vo.Cet4;

import java.io.Serializable;

public class Word_recite1 extends AppCompatActivity {

    private static final String WORD_BOOK = "book";
    private static final String CET4 = "四级";
    private static final String CET6 = "六级";
    private static final String WORD_LIST = "wordList";

    TextView mLabel;
    TextView mLabel2;
    TextView phoneticText;

    ImageButton btnBack;
    ImageButton btnSearch;
    Button btnRemember1;
    Button btnForget1;

    Cet4 word;
    WordDate wordDate;

    @Override
    @SuppressLint({"WrongViewCast", "HandlerLeak"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_recite1);

        initView();

        SharedPreferences myPreference = getSharedPreferences("preference", MODE_PRIVATE);
        if (CET4.equals(myPreference.getString(WORD_BOOK, ""))) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    word = (Cet4)getIntent().getSerializableExtra(WORD_LIST);
                    wordDate = new WordDate(word.getWord(),word.getPhonogram());
                    sendWord(wordDate);
                }
            }).start();
        }

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
                wordDate.setExample(word.getExample());
                it.putExtra("information", (Serializable)wordDate);
                startActivity(it);
            }
        });

        btnForget1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordDate.setExample(word.getExample());
                Message msg=new Message();
                msg.obj=wordDate;
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
            mLabel.setText(wordDate.word);
            mLabel2.setText(wordDate.phonetic);
            phoneticText.setText(wordDate.example);
        }
    };

    private void sendWord(WordDate wordDate) {
        Message msg = new Message();
        msg.obj = wordDate;
        handler.sendMessage(msg);
    }
}
