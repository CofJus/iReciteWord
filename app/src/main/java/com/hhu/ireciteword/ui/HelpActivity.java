package com.hhu.ireciteword.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    private  final static String TAG ="callsample";
    //授权请求编码
    private static final int PERMISSIO_REQUEST_CODE=9;
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

        Button buttonDial =(Button) findViewById(R.id.call);
        buttonDial.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.i(TAG,"ACTION_DIAL");
                Uri telUri = Uri.parse("tel:1008611");
                Intent it = new Intent(Intent.ACTION_DIAL,telUri);
                startActivity(it);
            }
        });


    }
}
