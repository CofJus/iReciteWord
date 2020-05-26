package com.hhu.ireciteword.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;

public class WordBookActivity extends AppCompatActivity {

    private final String cet4="四级";
    private final String cet6="六级";

    private TextView tvTitle;
    private ImageView ivBack;
    private String content;
    private RadioGroup mRg1;
    private RadioButton mRb_1, mRb_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_book);

        initView();

        //ADD by Ji Rui 2020/5/26
        SharedPreferences myPreference = getSharedPreferences("preference", Context.MODE_PRIVATE);
        String wordBook = myPreference.getString("book", "");
        //初始化RadioGroup
        if (cet4.equals(wordBook)) {
            mRg1.check(R.id.rb_1);
        } else if (cet6.equals(wordBook)) {
            mRg1.check(R.id.rb_2);
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);

                //获取SharedPreference，更新值
                SharedPreferences preference = getSharedPreferences("preference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                editor.putString("book", (String) radioButton.getText());
                editor.apply();

                Toast.makeText(WordBookActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {

        mRg1 = findViewById(R.id.rg_1);
        mRb_1 = findViewById(R.id.rb_1);
        mRb_2 = findViewById(R.id.rb_2);

        content = getIntent().getStringExtra("content");
        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_back);
        tvTitle.setText(content);
    }
}