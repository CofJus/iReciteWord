package com.hhu.ireciteword.ui;

/*
 * Created by 李雪滢 on 2020.4.16
 */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;

//当前处于word_recite2界面
public class Word_recite1 extends AppCompatActivity {

    private final static String TAG="Word_recite1";

    @SuppressLint("WrongViewCast")
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_recite1);//当前处于word_recite1界面
        Log.v(TAG,"进入word_recite1");
        //通过按钮back1，跳转到上一个页面，主界面page_main
        ImageButton btnBack=(ImageButton)findViewById(R.id.back1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //通过按钮search,跳转到查单词界面，search_word
        ImageButton btnSearch=(ImageButton)findViewById(R.id.search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_recite1.this,Search_word.class);
                startActivity(it);
                Toast.makeText(Word_recite1.this,"你进入下一个界面",Toast.LENGTH_LONG).show();
            }
        });

        //通过按钮remember1,跳转到单词详情页word_information

        Button btnRemember1=(Button)findViewById(R.id.remember1);
        btnRemember1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_recite1.this,Word_information.class);
                startActivity(it);
                Toast.makeText(Word_recite1.this,"你进入下一个界面",Toast.LENGTH_LONG).show();
            }
        });

        //通过按钮forget1，跳转到下一个提示word_recite2


        Button btnForget1=(Button)findViewById(R.id.forget1);
        btnForget1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_recite1.this,Word_recite2.class);
                startActivity(it);
                Toast.makeText(Word_recite1.this,"你进入提示界面",Toast.LENGTH_LONG).show();
            }
        });






    }
}
