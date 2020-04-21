package com.hhu.ireciteword.ui;

/*
 * Created by 李雪滢 on 2020.4.16
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//单词列表界面

public class ListwordBackHome extends AppCompatActivity {
    private final static String TAG="Listword_backmain";
    private ImageButton btnBack2;

    String[]Words={
            "resent","corrupt","rotten","concise","pencil","abandon","remind","dread","surround","globe","insurance","waiter"
    };
    String[]Chinese={
            "憎恨的","腐败的","腐烂的","简洁的","铅笔","废弃","提醒","害怕的","周围","全球的","保险","服务员"
    };

    private List<Map<String,Object>> lists;
    @SuppressLint("WrongViewCast")
    @Override
    //返回上一个活动
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordlist);
        Log.v(TAG,"进入wordlist");

        //实现单词列表
        //单词和翻译一对一
        ListView listview=(ListView)findViewById(R.id.ListView01);
        lists = new ArrayList<>();
        for(int i = 0;i < Words.length;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("words",Words[i]);
            map.put("Chinese",Chinese[i]);
            lists.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,lists,R.layout.listview_word,
                new String[]{"words","Chinese"},
                new int[]{R.id.textview1,R.id.textview2});
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListwordBackHome.this,"选择："+Words[position],Toast.LENGTH_SHORT).show();
            }
        });

        //返回上一个活动
        btnBack2= (ImageButton) findViewById(R.id.imagebutton1);
        btnBack2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
