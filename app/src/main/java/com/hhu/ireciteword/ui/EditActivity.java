package com.hhu.ireciteword.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.hhu.ireciteword.Dateutil.DateFormatType;
import com.hhu.ireciteword.Dateutil.MyTimeGetter;
import com.hhu.ireciteword.R;
import com.hhu.ireciteword.data.MyDB;

import java.util.Date;

import static com.hhu.ireciteword.Dateutil.MyFormat.getTimeStr;
import static com.hhu.ireciteword.Dateutil.MyFormat.myDateFormat;

/**
 *created by 沈思彤 on 2020/5/13
 *
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class EditActivity extends BaseActivity implements View.OnClickListener
       {
    private final static String TAG = "EditActivity";

    MyDB myDB;
    private Button btnSave;
    private Button btnBack;
    private TextView editTime;
    private EditText editTitle;
    private EditText editBody;
    private AlertDialog.Builder dialog;


    private DatePickerDialog dialogDate;
    private TimePickerDialog dialogTime;

    private String createDate;//完整的创建时间，插入数据库
    private String dispCreateDate;//创建时间-显示变量可能会去除年份

    private Integer year;
    private Integer month;
    private Integer dayOfMonth;
    private Integer hour;
    private Integer minute;
    private boolean timeSetTag;

    MyTimeGetter myTimeGetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        init();
        if (editTime.getText().length()==0)
            editTime.setText(dispCreateDate);
    }

    /*
     * 初始化函数
     */
    void init(){
        myDB = new MyDB(this);
        btnBack = findViewById(R.id.button_back);
        btnSave = findViewById(R.id.button_save);
        editTitle = findViewById(R.id.edit_title);
        editBody = findViewById(R.id.edit_body);
        editTime = findViewById(R.id.edit_title_time);


        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);


        Date date = new Date(System.currentTimeMillis());
        createDate = myDateFormat(date, DateFormatType.NORMAL_TIME);
        dispCreateDate = getTimeStr(date);

        dialogDate = null;
        dialogTime = null;
        hour = 0;
        minute = 0;
        year = 0;
        month = 0;
        dayOfMonth = 0;
        timeSetTag = false;
    }

    /*
     *  返回键监听，消除误操作BUG
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            String title;
            String body;
            String createDate;
            title = editTitle.getText().toString();
            body = editBody.getText().toString();
            createDate = editTime.getText().toString();
            //当返回按键被按下
            if (!isShowIng()){
                if (!"".equals(title)||!"".equals(body)){
                    showDialog(title,body,createDate);
                    clearDialog();
                } else {
                    intentStart();
                }
            }
        }
        return false;
    }

    /*
     *  按钮点击事件监听
     *
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        String title;
        String body;
        title = editTitle.getText().toString();
        body = editBody.getText().toString();
        switch (v.getId()){
            case R.id.button_save:
                if (saveFunction(title,body,createDate)){
                    intentStart();
                }
                break;
            case R.id.button_back:
                if (!"".equals(title)||!"".equals(body)){
                    showDialog(title,body,createDate);
                    clearDialog();
                } else {
                    intentStart();
                }
                break;
            default:
                break;
        }
    }

    /*
     * 返回主界面
     */
    void intentStart(){
        Intent intent = new Intent(EditActivity.this, NoteMainActivity.class);
        startActivity(intent);
        this.finish();
    }

    /*
     * APP保存函数
     */
    boolean saveFunction(String title, String body, String createDate){

        boolean flag = true;
        if ("".equals(title)){
            Toast.makeText(this,"标题不能为空", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if (title.length()>10){
            Toast.makeText(this,"标题过长", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if (body.length()>200){
            Toast.makeText(this,"内容过长", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if ("".equals(createDate)){
            Toast.makeText(this,"时间格式错误", Toast.LENGTH_SHORT).show();
            flag = false;
        }

        if(flag){
            SQLiteDatabase db;
            ContentValues values;
            //  存储单词信息
            db = myDB.getWritableDatabase();
            values = new ContentValues();
            values.put(MyDB.RECORD_TITLE,title);
            values.put(MyDB.RECORD_BODY,body);
            values.put(MyDB.RECORD_TIME,createDate);

            db.insert(MyDB.TABLE_NAME_RECORD,null,values);
            Toast.makeText(this,"保存成功", Toast.LENGTH_SHORT).show();
            db.close();
        }
        return flag;
    }

    /*
     * 弹窗函数
     */
    void showDialog(final String title, final String body, final String createDate){
        dialog = new AlertDialog.Builder(EditActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage("是否保存当前编辑内容");
        dialog.setPositiveButton("保存",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveFunction(title, body, createDate);
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

    /*
     *  清空弹窗
     */
    void clearDialog(){
        dialog = null;
    }

    /*
     *  判断是否弹窗是否显示
     */
    boolean isShowIng(){
        if (dialog!=null){
            return true;
        }else{
            return false;
        }
    }


}
