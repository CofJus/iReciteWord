package com.hhu.ireciteword;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *created by 沈思彤 on 2020/5/20
 *
 */

public class DateUtil {

    private static final String TAG = "DateUtil--->>>";

    public static int YEAR;
    public static int MONTH;
    public static int DAY;
    public static String CURRENT;


    static {
        Calendar a = Calendar.getInstance();
        YEAR = a.get(Calendar.YEAR);
        MONTH = a.get(Calendar.MONTH) + 1;
        DAY = a.get(Calendar.DAY_OF_MONTH);

        CURRENT = getDate(new Date(),"YYYY-MM-dd");
    }


    public static String getDate(Date source, String style) {
        SimpleDateFormat mdhm = new SimpleDateFormat(style, Locale.getDefault());//年 月 日
        return mdhm.format(source);
    }


    public static Date getDate(int y, int m, int d) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(y,m-1,d); //月减一
        return calendar.getTime();
    }

    public static String getDate(Date source) {
        SimpleDateFormat mdhm = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());//年 月 日
        return mdhm.format(source);
    }

    public static int getCurrentMonthLastDay(int year,int month) {
        Calendar a = Calendar.getInstance();
        a.set(year,month-1,1);//把日期设置为当月第一天 月减一
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return a.get(Calendar.DATE);
    }

    public static int getFirstDayOfMonth(int year,int month){
        Calendar a = Calendar.getInstance();
        a.set(year,month-1,1); //月要减一
        a.set(Calendar.DAY_OF_MONTH,1);//设为第一天
        return a.get(Calendar.DAY_OF_WEEK);
    }


    // 字符串类型日期转化成date类型
    public static Date strToDate(String style, String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(style, Locale.getDefault());
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }


    public static String calendarToDateTime(Calendar calendar, String style) {
        Date time = calendar.getTime();
        return getDate(time,style);
    }

    public static int[] getYMD(String formatDateString) {
        int[] YMD = new int[3];
        YMD[0] = Integer.parseInt(formatDateString.substring(0, 4));
        YMD[1] = Integer.parseInt(formatDateString.substring(formatDateString.indexOf("-") + 1, formatDateString.lastIndexOf("-")));
        YMD[2] = Integer.parseInt(formatDateString.substring(formatDateString.lastIndexOf("-") + 1, formatDateString.length()));
        return YMD;
    }


    public static List<Boolean> dateConvert(int year, int month, List<String> source, List<Boolean> record, int dif) {
        for (String s : source) {
            int[] YMD = getYMD(s);
            if (year == YMD[0] && month == YMD[1]) {//年月相同
                    record.set(YMD[2] + dif, true);
            }
        }
        return record;
    }

}
