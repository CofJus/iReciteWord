package com.hhu.ireciteword.ui;

/*
 * Created by 李雪滢 on 2020.4.16
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;
import com.hhu.ireciteword.data.WordDate;
import com.hhu.ireciteword.data.dao.LookUpResultDao;
import com.hhu.ireciteword.data.vo.LookUpResult;
import com.hhu.ireciteword.httpservice.translate.Translate;

import java.io.Serializable;

import static com.hhu.ireciteword.data.DaoFactory.getLookUpResultDaoInstance;

public class SearchWord extends AppCompatActivity {

    WordDate wordDate;
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
        ImageButton btnSearch = findViewById(R.id.search_word);
        final EditText txtWord = findViewById(R.id.wordInput);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchWord.this,SearchWordInformation.class);
                final String targetWord = txtWord.getText().toString();
                LookUpResultDao lookUpResultDao=getLookUpResultDaoInstance();
                LookUpResult historyResult = lookUpResultDao.queryByHistory(targetWord);
                if(historyResult!=null) {
                    historyResult.setVoiceUrl("https" + historyResult.getVoiceUrl().substring(4));
                    wordDate = new WordDate(historyResult);
                }
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Looper.prepare();
                            Translate translate = new Translate();
                            //包含了所有查询得到的信息
                            LookUpResult lookUpResult = translate.getResult(targetWord, "");
                            //将http链接转换为https
                            lookUpResult.setVoiceUrl("https" + lookUpResult.getVoiceUrl().substring(4));
                            wordDate = new WordDate(lookUpResult);
                            //插入查询得到的信息
                            LookUpResultDao lookUpResultDao=getLookUpResultDaoInstance();
                            lookUpResultDao.insert(lookUpResult);
                        }
                    }).start();
                }
                intent.putExtra("result", (Serializable)wordDate);
                startActivity(intent);
            }
        });
    }
}