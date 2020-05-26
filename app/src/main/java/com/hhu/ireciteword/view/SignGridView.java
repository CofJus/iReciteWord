package com.hhu.ireciteword.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 *created by 沈思彤 on 2020/5/20
 *
 */

public class SignGridView extends GridView {
    public SignGridView(Context context) {
        super(context);
    }
    public SignGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public SignGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 3, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
