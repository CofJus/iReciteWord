package com.hhu.ireciteword.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhu.ireciteword.R;

public class HeaderActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView ivBack;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

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
    }
}
