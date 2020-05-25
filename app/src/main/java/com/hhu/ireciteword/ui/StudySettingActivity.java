package com.hhu.ireciteword.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hhu.ireciteword.MyApp;
import com.hhu.ireciteword.R;

public class StudySettingActivity extends AppCompatActivity {

    private SharedPreferences sp ;
    private TextView tvTitle;
    private ImageView ivBack;
    private String content;
    private EditText study_word;
    private EditText review_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_setting);

        content=getIntent().getStringExtra("content");
        tvTitle=findViewById(R.id.tv_title);
        ivBack=findViewById(R.id.iv_back);
        tvTitle.setText(content);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        study_word=findViewById(R.id.ed_1);


        review_word=findViewById(R.id.et_2);


        final MyApp myApp = new MyApp();

        study_word.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int number1 = Integer.parseInt(study_word.getText().toString());


//                sp = getSharedPreferences("User", Context.MODE_PRIVATE);
//                //获取到edit对象
//                SharedPreferences.Editor edit = sp.edit();
//                //通过editor对象写入数据
//                edit.putString("Value",study_word.getText().toString().trim());
//                //提交数据存入到xml文件中
//                edit.commit();
//
//                String value = sp.getString("Value","Null");
//                study_word.setText(value);

                myApp.setTarget(number1);
                Toast.makeText(getApplicationContext(),"每日新学单词:"+study_word.getText().toString()+"个",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        review_word.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int number2 = Integer.parseInt(review_word.getText().toString());
                myApp.setTarget(number2);
                Toast.makeText(getApplicationContext(),"每日复习单词:"+review_word.getText().toString()+"个",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
