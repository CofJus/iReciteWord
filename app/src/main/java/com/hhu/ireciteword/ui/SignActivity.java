package com.hhu.ireciteword.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;
import com.hhu.ireciteword.data.sign.DateAdapter;
import com.hhu.ireciteword.utils.DateUtil;
import com.hhu.ireciteword.view.MonPickerDialog;
import com.hhu.ireciteword.view.SignView;

import java.util.Calendar;

/**
 *created by 沈思彤 on 2020/5/20
 */
public class SignActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity--->>>";

    private SignView signDate;

    private TextView tvYear;

    private Button sign;

    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ImageButton btnBack5=(ImageButton)findViewById(R.id.back5);
        btnBack5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        sign = findViewById(R.id.btn_sign);

        signDate = findViewById(R.id.signDate);

        tvYear = signDate.findViewById(R.id.tvYear);

        signDate.init();

        if(signDate.isSign()) {
            sign.setBackgroundColor(Color.GRAY);
            sign.setText("今日已签到");
            sign.setClickable(false);
        }else {
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handle();
                }
            });
        }

        tvYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMonPicker();
            }
        });
    }

    private void handle() {
        signDate.signIn(new DateAdapter.OnSignListener() {
            @Override
            public void OnSignedSucceed() {
                showToast("签到成功");
                sign.setBackgroundColor(Color.GRAY);
                sign.setText("今日已签到");
                sign.setClickable(false);
            }
            @Override
            public void OnSignedFail() {
                showToast("签到失败");
            }
        });
    }


    private void showMonPicker() {
        final Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(DateUtil.strToDate("yyyy-MM", tvYear.getText().toString()));
        MonPickerDialog dialog = new MonPickerDialog(context,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                localCalendar.set(Calendar.YEAR, year);
                localCalendar.set(Calendar.MONTH, monthOfYear);
                localCalendar.set(Calendar.DATE, dayOfMonth);
            }
        }, localCalendar.get(Calendar.YEAR), localCalendar.get(Calendar.MONTH),localCalendar.get(Calendar.DATE));

        dialog.show();

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvYear.setText(DateUtil.calendarToDateTime(localCalendar, "yyyy-MM"));
                refresh(localCalendar.get(Calendar.YEAR),localCalendar.get(Calendar.MONTH)+1);
            }
        });
    }

    private void refresh(int year,int month) {
        if(year!=DateUtil.YEAR || month!=DateUtil.MONTH){
            signDate.init(year,month);
            sign.setBackgroundColor(getResources().getColor(R.color.sky_blue));
            sign.setText("返回当前日期");
            sign.setClickable(true);
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initView();
                }
            });
        }
    }

    private void showToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
