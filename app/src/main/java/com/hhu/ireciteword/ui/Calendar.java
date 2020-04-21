package com.hhu.ireciteword.ui;

/*
 * Created by 李雪滢 on 2020.4.16
 */
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;

//当前处于日历界面
public class Calendar extends AppCompatActivity {
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        //按钮back5返回主界面
        ImageButton btnBack5=(ImageButton)findViewById(R.id.back5);
        btnBack5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
