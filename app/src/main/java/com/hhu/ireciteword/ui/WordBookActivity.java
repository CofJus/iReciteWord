package com.hhu.ireciteword.ui;

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

    private TextView tvTitle;
    private ImageView ivBack;
    private String content;
    private RadioGroup mRg1;
    private RadioButton mRb_1, mRb_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_book);

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

        mRg1=findViewById(R.id.rg_1);
        mRb_1=findViewById(R.id.rb_1);
        mRb_2=findViewById(R.id.rb_2);

        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {   //监听事件
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                Toast.makeText(WordBookActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}