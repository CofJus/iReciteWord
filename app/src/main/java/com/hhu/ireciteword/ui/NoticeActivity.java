package com.hhu.ireciteword.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhu.ireciteword.R;

/*
 * Created by 石倍瑜 on 2020.4.16：
 * 返回上一界面、顶部菜单栏
 * （其余页面同）
 */

public class NoticeActivity extends Activity {
    private TextView tv;
    private TextView tvTitle;
    private ImageView ivBack;
    private String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
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
