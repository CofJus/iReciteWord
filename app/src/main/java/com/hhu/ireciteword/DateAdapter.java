package com.hhu.ireciteword;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhu.ireciteword.sign.SignInHelper;

import java.util.ArrayList;
import java.util.List;

/**
 *created by 沈思彤 on 2020/5/20
 *
 */

public class DateAdapter extends BaseAdapter {

    private static final String TAG = "DateAdapter--->>>";

    private Context context;
    //日历坐标数据 根据布尔值设置是否签到
    private List<Integer> days = new ArrayList<>();
    //签到状态，用来判断坐标中哪个位置是已经签到的
    private List<Boolean> status = new ArrayList<>();

    private List<String> signIns;//数据库查到的签到记录
    private  int maxDay,firstDay,dif; //

    private int current_year, current_mon;

    private SignInHelper helper;

    public interface OnSignListener {
        void OnSignedSucceed();
        void OnSignedFail();
    }

    //签到成功的回调方法，相应的可自行添加签到失败时的回调方法

    public DateAdapter(Context context, int year, int month) {
        this.context = context;

        current_year = year;
        current_mon = month;

         helper = new SignInHelper(context);

        signIns = helper.query(year,month);


        Log.i(TAG, "查到的数据:"+signIns);

        maxDay = DateUtil.getCurrentMonthLastDay(year,month);//获取当月天数

        //firstDay(1-7)  获取当月第一天是星期几，星期日是第一天, 数字为1，代表这个月的第一天是星期天，依次类推
        firstDay = DateUtil.getFirstDayOfMonth(year,month);

        //Log.i(TAG, "year:"+year+" mon:"+month+" firstDay:"+firstDay+" maxDay:"+maxDay);

        //dif,实际定义的status坐标与日期是有差异的，经过实验和对比后，发现在显示已经签到的日期格子时，
        //显示的位置与实际位置总是有差别，即有时候在签到日期是8号，但是打勾的是6号日期的格子。经过查询规律发现，
        //这个偏差是与firstDay变量有关的，而这个偏差的值为firstDay -2, firstDay取值范围为1-7，这样的话
        //dif取值范围即为(-1,5) 当该月的第一天为星期一时，此时firstDay取值为2，dif为0，此时才不存在偏差 否则
        //其他情况都存在偏差（例如Calendar中默认星期天为第一天，此时的Calendar.DAY_OF_WEEK取值为 1 但
        // gridView默认坐标从0开始，此时需要加上偏差 这时的偏差dif= 1-2=-1）
        dif = firstDay -2;

        for (int i = 0; i < firstDay - 1; i++) {
            days.add(0);
            //0代表需要隐藏的item
            status.add(false);
            //false代表为签到状态
        }

        for (int i = 0; i < maxDay; i++) {
            days.add(i+1);
            //初始化日历数据
            status.add(false);
            //初始化日历签到状态
        }
        status = DateUtil.dateConvert(current_year, current_mon,signIns,status,dif);
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int i) {
        return days.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_week,null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv = view.findViewById(R.id.tvWeek);
        viewHolder.rlItem = view.findViewById(R.id.rlItem);
        viewHolder.ivStatus = view.findViewById(R.id.ivStatus);
        viewHolder.tv.setText(days.get(i)+"");
        if(days.get(i)==0){  //接着上个月的残留日期
            viewHolder.rlItem.setVisibility(View.GONE);
        }
        if(status.get(i)){
            viewHolder.tv.setTextColor(Color.parseColor("#FD0000"));
            viewHolder.ivStatus.setVisibility(View.VISIBLE);
        }else{
            viewHolder.tv.setTextColor(Color.parseColor("#666666"));
            viewHolder.ivStatus.setVisibility(View.GONE);
        }
        return view;
    }

    class ViewHolder{
        RelativeLayout rlItem;
        TextView tv;
        ImageView ivStatus;
    }

    public void signIn(OnSignListener listener){
        helper.insert(DateUtil.CURRENT);
        notifyDataSetChanged();
        listener.OnSignedSucceed();
    }


    public boolean isSign(){
        return status.get(DateUtil.DAY+dif);
    }

}
