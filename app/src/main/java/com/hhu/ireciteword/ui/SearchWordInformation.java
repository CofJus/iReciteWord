package com.hhu.ireciteword.ui;

/*
 * Created by 李雪滢 on 2020.4.16
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;
//TODO merge with TranslateActivity
//当前处于search_word_information界面
public class SearchWordInformation extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_word_information);

        //返回按钮返回主界面
        ImageButton btnBack = (ImageButton) findViewById(R.id.back6);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //查找按钮search3继续进入查找界面
        ImageButton btnSearch = (ImageButton)findViewById(R.id.search3);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SearchWordInformation.this, Search_word.class);
                startActivity(it);
                Toast.makeText(SearchWordInformation.this, "你进入下一个界面", Toast.LENGTH_LONG).show();
            }
        });



    }
}