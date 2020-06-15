package com.hhu.ireciteword.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;

/**
 * 输入单词目标数 Activity
 * @author 石倍瑜
 * @date 2020/5/26
 */

public class StudySettingActivity extends AppCompatActivity {

    private ImageView ivBack;
    private LastInputEditText studyWord;
    private LastInputEditText reviewWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_setting);

        initView();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //监听器
        studyWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //获取EditText数字
                int number1 = Integer.parseInt(studyWord.getText().toString());
                //保存当前选择 EditTExt id
                SharedPreferences preference = getSharedPreferences("preference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                editor.putInt("studyTarget",number1);
                editor.apply();
                studyWord.setText(String.valueOf(number1));
                Toast.makeText(getApplicationContext(),"每日新学单词:"+ studyWord.getText().toString()+"个",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        reviewWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int number2 = Integer.parseInt(reviewWord.getText().toString());
                //保存当前选择 EditText id
                SharedPreferences preference = getSharedPreferences("preference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                editor.putInt("reviewTarget",number2);
                editor.apply();
                reviewWord.setText(String.valueOf(number2));
                Toast.makeText(getApplicationContext(),"每日复习单词:"+ reviewWord.getText().toString()+"个",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    private void initView() {
        ivBack=findViewById(R.id.iv_back);

        studyWord = findViewById(R.id.ed_1);
        reviewWord = findViewById(R.id.et_2);

        SharedPreferences myPreference = getSharedPreferences("preference", Context.MODE_PRIVATE);
        studyWord.setText(String.valueOf(myPreference.getInt("studyTarget", 50)));
        reviewWord.setText(String.valueOf(myPreference.getInt("reviewTarget", 20)));
    }
}