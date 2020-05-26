package com.hhu.ireciteword.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

/**
 *created by 沈思彤 on 2020/5/20
 *
 */

public class MonPickerDialog extends DatePickerDialog {

    private OnDateSetListener listener;
    public MonPickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, android.R.style.Theme_Holo_Light_Panel, callBack,year, monthOfYear, dayOfMonth);
        this.setTitle(year + "年" + (monthOfYear + 1) + "月");
        listener = callBack;
        ((ViewGroup) ((ViewGroup) this.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(2).
                setVisibility(View.GONE);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
        this.setTitle(year + "年" + (month + 1) + "月");
        listener.onDateSet(view,year,month,day);
    }
}

