package com.hhu.ireciteword.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;
import com.hhu.ireciteword.data.dao.RecordDao;
import com.hhu.ireciteword.data.vo.Record;

import static com.hhu.ireciteword.data.DaoFactory.getRecordDaoInstance;

/**
 * 设置 Activity
 * @author 石倍瑜
 * @date 2020/4
 */

public class SettingActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView ivBack;
    private String content;

    private LinearLayout llStudysetting;
    private LinearLayout llOthersetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

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

        llStudysetting = findViewById(R.id.ll_study_setting);
        llStudysetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SettingActivity.this,StudySettingActivity.class);
                intent.putExtra("content","学习设置");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnStartSentece).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Record record = new Record();
                record.setTitleName("example");
                record.setTextBody("例子，example");
                RecordDao recordDao=getRecordDaoInstance();
                recordDao.insert(record);
            }
        });

        //待新建
        llOthersetting = findViewById(R.id.ll_other_setting);
    }
}
