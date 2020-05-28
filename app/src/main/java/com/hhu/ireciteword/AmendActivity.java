package com.hhu.ireciteword;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.hhu.ireciteword.data.MyDB;
import com.hhu.ireciteword.data.vo.Record;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *created by 沈思彤 on 2020/5/13
 *
 */
public class AmendActivity extends BaseActivity implements View.OnClickListener{

    private final static String TAG = "AmendActivity";

    MyDB myDB;
    private Button btnSave;
    private Button btnBack;
    private TextView amendTime;
    private TextView amendTitle;
    private EditText amendBody;
    private Record noteRecord;
    private AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_amend);
        init();

    }


    @Override
    public void onClick(View v) {
        String body;
        body = amendBody.getText().toString();
        switch (v.getId()){
            case R.id.button_save:
                if (updateFunction(body)){
                    intentStart();
                }
                break;
            case R.id.button_back:
                showDialog(body);
                clearDialog();
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //当返回按键被按下
            if (!isShowIng()){
                showDialog(amendBody.getText().toString());
                clearDialog();
            }
        }
        return false;
    }

    /*
     * 初始化函数
     */
    @SuppressLint("SetTextI18n")
    void init(){
        myDB = new MyDB(this);
        btnBack = findViewById(R.id.button_back);
        btnSave = findViewById(R.id.button_save);
        amendTitle = findViewById(R.id.amend_title);
        amendBody = findViewById(R.id.amend_body);
        amendTime = findViewById(R.id.amend_title_time);


        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);


        Intent intent = this.getIntent();
        if (intent!=null){

            noteRecord = new  Record();

            noteRecord.setId(Integer.valueOf(intent.getStringExtra(MyDB.RECORD_ID)));
            noteRecord.setTitleName(intent.getStringExtra(MyDB.RECORD_TITLE));
            noteRecord.setTextBody(intent.getStringExtra(MyDB.RECORD_BODY));
            noteRecord.setCreateTime(intent.getStringExtra(MyDB.RECORD_TIME));

            amendTitle.setText(noteRecord.getTitleName());
            String str="";

            amendTime.setText(noteRecord.getCreateTime()+str);
            amendBody.setText(noteRecord.getTextBody());
        }
    }



    /*
     * 保存函数
     */
    boolean updateFunction(String body){

        SQLiteDatabase db;
        ContentValues values;

        boolean flag = true;
        if (body.length()>200){
            Toast.makeText(this,"内容过长", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if(flag){
            // update
            db = myDB.getWritableDatabase();
            values = new ContentValues();
            values.put(MyDB.RECORD_BODY,body);
            values.put(MyDB.RECORD_TIME,getNowTime());
            db.update(MyDB.TABLE_NAME_RECORD,values, MyDB.RECORD_ID +"=?",
                    new String[]{noteRecord.getId().toString()});
            Toast.makeText(this,"修改成功", Toast.LENGTH_SHORT).show();
            db.close();
        }
        return flag;
    }

    /*
     * 返回主界面
     */
    void intentStart(){
        Intent intent = new Intent(AmendActivity.this, NoteMainActivity.class);
        startActivity(intent);
        this.finish();
    }
    /*
     * 弹窗函数
     */
    void showDialog(final String body){
        dialog = new AlertDialog.Builder(AmendActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage("是否保存当前编辑内容");
        dialog.setPositiveButton("保存",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateFunction(body);
                intentStart();
                    }
                });

        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intentStart();
                    }
                });
        dialog.show();
    }

    void clearDialog(){
        dialog = null;
    }

    boolean isShowIng(){
        if (dialog!=null){
            return true;
        }else{
            return false;
        }
    }

    /*
     * 得到当前时间
     * @return
     */
    String getNowTime(){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

}
