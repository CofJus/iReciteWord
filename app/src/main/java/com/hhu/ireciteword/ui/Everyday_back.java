package com.hhu.ireciteword.ui;
/*
 * Created by 张佳豪 on 4.18
 */
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;

//每日一句页面
//TODO merge with SentenceActivity
public class Everyday_back extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discover_meiriyiju);
        ImageButton back = (ImageButton) findViewById(R.id.everydaywordback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}