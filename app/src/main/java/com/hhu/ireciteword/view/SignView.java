package com.hhu.ireciteword.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhu.ireciteword.DateAdapter;
import com.hhu.ireciteword.DateUtil;
import com.hhu.ireciteword.R;

/**
 * 签到控件
 */
public class SignView extends LinearLayout {

    private TextView tvYear;
    private SignGridView gvDate;
    private DateAdapter adapterDate;

    public SignView(Context context) {
        super(context);
        init();
    }

    public SignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SignView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){
        init(DateUtil.YEAR,DateUtil.MONTH);
    }

    public void init(int year,int month){
        View view = View.inflate(getContext(), R.layout.layout_signdate,this);
        tvYear = view.findViewById(R.id.tvYear);
        gvDate = view.findViewById(R.id.gvDate);
        tvYear.setText(year+"-"+month);
        adapterDate = new DateAdapter(getContext(),year,month);
        gvDate.setAdapter(adapterDate);

    }

    public void signIn(DateAdapter.OnSignListener onSignedSuccess){
        adapterDate.signIn(onSignedSuccess);
    }

    public boolean isSign(){
        return adapterDate.isSign();
    }


}
