package com.hhu.ireciteword.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhu.ireciteword.R;

/**
 * 帮助 Activity
 * @author 石倍瑜
 * @date 2020/4
 */
public class HelpActivity extends AppCompatActivity {

    private TextView tv;
    private TextView tvTitle;
    private ImageView ivBack;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        content=getIntent().getStringExtra("content");
        tv=findViewById(R.id.tv);
        tvTitle=findViewById(R.id.tv_title);
        ivBack=findViewById(R.id.iv_back);
        tvTitle.setText(content);
        tv.setText("这是"+content+"界面");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
