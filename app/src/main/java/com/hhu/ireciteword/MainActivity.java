package com.hhu.ireciteword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hhu.ireciteword.data.CopyDatabase;
import com.hhu.ireciteword.data.WordsContract;
import com.hhu.ireciteword.data.WordsProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 初始化数据库,可以放在背单词界面启动 */
        CopyDatabase cp=new CopyDatabase(this);
        cp.openDatabase();
        cp.closeDatabase();

        /* Start TranslateActivity */
        findViewById(R.id.Translate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, TranslateActivity.class));
            }
        });

        /* Start SentenceActivity */
        findViewById(R.id.Sentence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SentenceActivity.class));
            }
        });
    }

}
