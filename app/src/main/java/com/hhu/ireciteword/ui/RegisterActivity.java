package com.hhu.ireciteword.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hhu.ireciteword.MainActivity;
import com.hhu.ireciteword.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by 吕志鹏
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DBOpenHelper mDBOpenHelper;
    private Button BTRegister;                          //注册按钮
    private RelativeLayout RLRegisteractivityTop;       //顶部导航栏
    private ImageView IVBack;                           //返回
    private LinearLayout LLRegisteractivityBody;        //主体部分
    private EditText ETUsername;                        //用户名
    private EditText ETPassword1;                       //密码1
    private EditText ETPassword2;                       //密码2
    private RelativeLayout RLRegisteractivityBottom;    //底部协议部分

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);
    }

    private void initView(){
        BTRegister = findViewById(R.id.bt_registeractivity_register);
        RLRegisteractivityTop = findViewById(R.id.rl_registeractivity_top);
        IVBack = findViewById(R.id.iv_registeractivity_back);
        LLRegisteractivityBody = findViewById(R.id.ll_registeractivity_body);
        ETUsername = findViewById(R.id.et_registeractivity_username);
        ETPassword1 = findViewById(R.id.et_registeractivity_password1);
        ETPassword2 = findViewById(R.id.et_registeractivity_password2);
        RLRegisteractivityBottom = findViewById(R.id.rl_registeractivity_bottom);

        IVBack.setOnClickListener(this);
        BTRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_registeractivity_back: //返回登录页面
                Intent intent1 = new Intent(this, loginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.bt_registeractivity_register:    //注册按钮
                //获取用户输入的用户名、密码
                String username = ETUsername.getText().toString().trim();
                String password1 = ETPassword1.getText().toString().trim();
                String password2 = ETPassword2.getText().toString().trim();
                //注册验证
                if (!TextUtils.isEmpty(username)
                        && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2)) {
                        //将用户名和密码加入到数据库中
                    if(password1.equals(password2)) {
                        mDBOpenHelper.add(username, password1);
                        Intent intent2 = new Intent(this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this,"两次密码输入不同，请重新确认",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}