package com.hhu.ireciteword.ui;
/*
* Created by 张佳豪 on 4.18
*/
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;

public class Challengeone_back extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_one);
        ImageButton challengeoneback = findViewById(R.id.challengeoneback);
        challengeoneback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Challengeone_back.this, Dakachallenge_back.class);
                startActivity(it);
                Toast.makeText(Challengeone_back.this, "你返回打卡挑战页面", Toast.LENGTH_LONG).show();
            }
        });

        Button challengeone=findViewById(R.id.challengone);
        challengeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Challengeone_back.this, com.hhu.ireciteword.MainActivity.class);
                startActivity(it);
                Toast.makeText(Challengeone_back.this, "开始打卡", Toast.LENGTH_LONG).show();
            }
        });
    }
}