package com.hhu.ireciteword.ui;
/*
 * Created by 李雪滢 on 2020.4.16
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;

//活动：用户点了不认识之后，弹出来例句提示
public class Word_recite2 extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_recite2);

        //通过按钮back4，跳转到上一个页面，主界面page_main
        ImageButton btnBack4=(ImageButton)findViewById(R.id.back4);
        btnBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_recite2.this, com.hhu.ireciteword.MainActivity.class);
                startActivity(it);
                Toast.makeText(Word_recite2.this,"你进入下一个界面",Toast.LENGTH_LONG).show();
            }

        });

        //通过按钮search4,跳转到查单词界面，search_word
        ImageButton btnSearch4=(ImageButton)findViewById(R.id.search4);
        btnSearch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_recite2.this,Search_word.class);
                startActivity(it);
                Toast.makeText(Word_recite2.this,"你进入下一个界面",Toast.LENGTH_LONG).show();
            }
        });

        //通过按钮"想起来了"remember2进入单词详情页
        Button btnRemember2=(Button)findViewById(R.id.remember2);
        btnRemember2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_recite2.this,Word_information.class);
                startActivity(it);
                Toast.makeText(Word_recite2.this,"你进入下一个界面",Toast.LENGTH_LONG).show();
            }
        });

        //通过按钮“没想起来”forget2进入单词详情页
        Button btnForget2=(Button)findViewById(R.id.forget2);
        btnForget2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Word_recite2.this,Word_information.class);
                startActivity(it);
                Toast.makeText(Word_recite2.this,"你进入单词详情页",Toast.LENGTH_LONG).show();
            }
        });

    }
}
