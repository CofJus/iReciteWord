package com.hhu.ireciteword.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhu.ireciteword.R;

/**
 * 个人资料 Activity
 * @author 石倍瑜,吕志鹏
 * @date 2020/4  2020/5
 */
public class HeaderActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView ivBack;
    private String content;
    private TextView quitAccount;
    private TextView ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        content=getIntent().getStringExtra("content");
        tvTitle=findViewById(R.id.tv_title);
        ivBack=findViewById(R.id.iv_back);
        tvTitle.setText(content);

        quitAccount=findViewById(R.id.setting_account_cancel); //点击退出登录进入登录页面
        quitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HeaderActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
