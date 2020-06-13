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
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;
import com.hhu.ireciteword.data.WordDate;

//活动：单词详情页
public class Word_information extends AppCompatActivity {

    TextView wordView;
    TextView phoneticView;
    TextView exampleView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_information);

        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                WordDate wordDate=(WordDate)getIntent().getSerializableExtra("information");
                sendMessage(wordDate);
            }
        }).start();

        findViewById(R.id.back2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_information.this, com.hhu.ireciteword.MainActivity.class);
                startActivity(it);
                Toast.makeText(Word_information.this,"你进入下一个界面",Toast.LENGTH_LONG).show();
            }
        });
        //通过按钮search,跳转到查单词界面，search_word
        findViewById(R.id.search2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_information.this,Search_word.class);
                startActivity(it);
                Toast.makeText(Word_information.this,"你进入下一个界面",Toast.LENGTH_LONG).show();
            }
        });

        //通过按钮"下一个"，进入下一个单词界面,Word_recite1.java
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_information.this, Word_recite1.class);
                SharedPreferences myPreference = getSharedPreferences("iReciteWord", MODE_PRIVATE);
                //TODO 查询下一个单词并发送到背单词Activity，计数并与目标比较
                startActivity(it);
                Toast.makeText(Word_information.this,"你进入下一个界面",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView(){
        wordView=findViewById(R.id.word);
        phoneticView=findViewById(R.id.phoneticView);
        exampleView=findViewById(R.id.exampleView);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            WordDate wordDate = (WordDate) msg.obj;
            Log.i("word",wordDate.word);
            wordView.setText(wordDate.word);
            phoneticView.setText(wordDate.phonetic);
            exampleView.setText(wordDate.example);
        }
    };

    private void sendMessage(WordDate wordDate) {
        Message msg=new Message();
        msg.obj=wordDate;
        handler.sendMessage(msg);
    }
}
