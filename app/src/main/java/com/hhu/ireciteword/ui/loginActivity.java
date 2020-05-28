package com.hhu.ireciteword.ui;
/**
 * created by 吕志鹏
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhu.ireciteword.MainActivity;
import com.hhu.ireciteword.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    private DBOpenHelper mDBOpenHelper;              //数据库
    private TextView TVRegister;                     //注册
    private RelativeLayout RLLoginactivityTop;       //上方导航栏
    private EditText ETUsername;                     //用户名
    private EditText ETPassword;                     //密码
    private LinearLayout LLLoginactivityTwo;         //两个输入框部分
    private Button BTLogin;                          //登录按钮
    private ImageView back;                          //返回

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);
    }

    private void initView() {
        // 初始化控件
        BTLogin = findViewById(R.id.bt_loginactivity_login);
        TVRegister = findViewById(R.id.tv_loginactivity_register);
        RLLoginactivityTop = findViewById(R.id.rl_loginactivity_top);
        ETUsername = findViewById(R.id.et_loginactivity_username);
        ETPassword = findViewById(R.id.et_loginactivity_password);
        LLLoginactivityTwo = findViewById(R.id.ll_loginactivity_two);
        back = findViewById(R.id.iv_loginactivity_back);

        // 设置点击事件监听器
        BTLogin.setOnClickListener(this);
        TVRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            //跳转回原来的界面
            case R.id.iv_loginactivity_back:
                startActivity(new Intent(this, HeaderActivity.class));
                finish();
                break;
            // 跳转到注册界面
            case R.id.tv_loginactivity_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
                //开始登陆判断
            case R.id.bt_loginactivity_login:
                String name = ETUsername.getText().toString().trim();
                String password = ETPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
