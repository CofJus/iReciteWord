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

//处于打卡挑战页面
public class Dakachallenge_back extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dakachallenge);
        //按钮dakachallengeback返回发现页面
        ImageButton dakachallengeback = findViewById(R.id.dakachallengeback);
        dakachallengeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Dakachallenge_back.this, com.hhu.ireciteword.MainActivity.class);
                startActivity(it);
                Toast.makeText(Dakachallenge_back.this, "返回发现页面", Toast.LENGTH_LONG).show();
            }
        });


         Button tochallengeone =(Button) findViewById(R.id.打卡挑战一);
        tochallengeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Dakachallenge_back.this, Challengeone_back.class);
                startActivity(it);
                Toast.makeText(Dakachallenge_back.this,"你进入打卡挑战一",Toast.LENGTH_LONG).show();
            }
        });


        Button tochallengetwo = (Button) findViewById(R.id.打卡挑战二);
        tochallengetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Dakachallenge_back.this, Challengetwo_back.class);
                startActivity(it);
                Toast.makeText(Dakachallenge_back.this,"你进入打卡挑战二",Toast.LENGTH_LONG).show();
            }
        });


        Button tochallengethree = (Button) findViewById(R.id.打卡挑战三);
        tochallengethree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Dakachallenge_back.this, Challengethree_back.class);
                startActivity(it);
                Toast.makeText(Dakachallenge_back.this,"你进入打卡挑战三",Toast.LENGTH_LONG).show();
            }
        });



    }
}
