package com.hhu.ireciteword.ui;

/*
 * Created by 李雪滢 on 2020.4.16
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;
//TODO merge with TranslateActivity
public class Search_word extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_word);

        //返回上个界面
        ImageButton btnBack = (ImageButton) findViewById(R.id.back3);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //进入查单词界面
            ImageButton btnSearch = (ImageButton) findViewById(R.id.search_word);
            final EditText txtWord =(EditText)findViewById(R.id.wordInput);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(txtWord.getText().toString().equals("tony")){  //这里是查单词功能的（是错的
                        Intent it =new Intent(Search_word.this,SearchWordInformation.class);
                       startActivity(it);
                        Toast.makeText(Search_word.this, "单词找到啦", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent it = new Intent(Search_word.this,Search_word.class);//还在这个界面
                        Toast.makeText(Search_word.this, "单词没找到哦,检查一下输入吧~", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
}