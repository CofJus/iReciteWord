package com.hhu.ireciteword.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhu.ireciteword.R;

/**
 * 锁屏设置(部分) Activity
 * @author 石倍瑜
 * @date 2020/3/27
 */
public class LockScreenWordsActivity extends AppCompatActivity {

    private TextView tv;
    private TextView tvTitle;
    private ImageView ivBack;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_word);

        content=getIntent().getStringExtra("content");
        tv=findViewById(R.id.tv);
        tvTitle=findViewById(R.id.tv_title);
        ivBack=findViewById(R.id.iv_back);
        tvTitle.setText(content);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
