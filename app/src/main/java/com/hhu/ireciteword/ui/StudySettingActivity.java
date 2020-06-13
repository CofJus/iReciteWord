package com.hhu.ireciteword.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hhu.ireciteword.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 输入单词目标数 Activity
 * @author 石倍瑜
 * @date 2020/5/26
 */

public class StudySettingActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView ivBack;
    private String content;
    private LastInputEditText study_word;    //使用自定义控件(EditText)
    private LastInputEditText review_word;

    public static final String FILE_NAME = "iReciteWord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_setting);

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

        study_word=(LastInputEditText) findViewById(R.id.ed_1);
        review_word=(LastInputEditText) findViewById(R.id.et_2);



        //监听器
        study_word.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                //获取EditText数字
                int number1 = Integer.parseInt(study_word.getText().toString());
                //保存当前选择 EditTExt id
                put(StudySettingActivity.this,"num1",number1+"");
                study_word.setText(number1+"");

                Toast.makeText(getApplicationContext(),"每日新学单词:"+study_word.getText().toString()+"个",Toast.LENGTH_LONG).show();
                return false;
            }

        });

        review_word.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int number2 = Integer.parseInt(review_word.getText().toString());
                //保存当前选择 EditText id
                put(StudySettingActivity.this,"num2",number2+"");
                review_word.setText(number2+"");

                Toast.makeText(getApplicationContext(),"每日复习单词:"+review_word.getText().toString()+"个",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        //将保存的数据转化为整型，默认50和20个
        int studyWordChange = Integer.parseInt((String) get(StudySettingActivity.this, "num1", "50")) ;
        int reviewWordChange = Integer.parseInt((String) get(StudySettingActivity.this, "num2", "20"));
//        String num1 = (String) get(this, "num1", "");
//        String num2 = (String) get(this, "num2", "");

        review_word.setText(reviewWordChange+"");
        study_word.setText(studyWordChange+"");

    }


    //保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
    public static void put(Context context, String key, Object object) {

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }


    //得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }


    //创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        //反射查找apply的方法
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        //如果找到则使用apply执行，否则使用commit
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}
