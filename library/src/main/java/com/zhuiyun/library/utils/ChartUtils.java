package com.github.mikephil.chart.inkbird.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChartUtils {
    static String[] weekDays = {"Sun.", "Mon.", "Tues.", "Wed.", "Thur.", "Fri.", "Sat."};
    public static float CToF(float C) {
        float f = 1.8f * C + 32;
        BigDecimal b = new BigDecimal(f);
        float f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return f1;
    }
    /**
     *
     * @param time
     * @return "yy-MM-dd\nHH:mm"
     */
    public static String timeToString5(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa \n MM/dd",Locale.US);
        return simpleDateFormat.format(time);
    }

    public static String timeToString_week(long time) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int index=calendar.get(Calendar.DAY_OF_WEEK);
        if(index>=0&&index<=7){
            return weekDays[index-1];
        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm aa \n MM/dd",Locale.US);
        return "Sun.";
    }

    public static String timeToString5_2(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm \n MM/dd",Locale.US);
        return simpleDateFormat.format(time);
    }
    public static String timeToString6(long time) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time);
//        String am_pm;
//        if(calendar.get(Calendar.AM_PM) == Calendar.AM){//上午
//            am_pm = "am";
//        }else{
//            am_pm = "pm";
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa \n MM-dd");
        return  simpleDateFormat.format(time).replace(".","");
    }

    public static String timeToString7(long time) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time);
//        String am_pm;
//        if(calendar.get(Calendar.AM_PM) == Calendar.AM){//上午
//            am_pm = "am";
//        }else{
//            am_pm = "pm";
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa");
        return  simpleDateFormat.format(time).replace(".","");
    }
    public static String timeToString8(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa", Locale.US);
        return  simpleDateFormat.format(time).replace(".","");
    }

    public static String timeToString8_12(long time) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd / hh:mm:ss aa",Locale.US);
        return  simpleDateFormat.format(time).replace(".","");
    }

    public static String timeToString8_2(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.US);
        return  simpleDateFormat.format(time).replace(".","");
    }
    public static String timeToString10(long time) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time);
//        String am_pm;
//        if(calendar.get(Calendar.AM_PM) == Calendar.AM){//上午
//            am_pm = "am";
//        }else{
//            am_pm = "pm";
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
        return  simpleDateFormat.format(time);
    }

    public static String timeToString11(long time) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time);
//        String am_pm;
//        if(calendar.get(Calendar.AM_PM) == Calendar.AM){//上午
//            am_pm = "am";
//        }else{
//            am_pm = "pm";
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        return  simpleDateFormat.format(time);
    }


    public static String timeToStringMD(long time) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        return  simpleDateFormat.format(time);
    }

    public static String timeToStringYM(long time) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM");
        return  simpleDateFormat.format(time);
    }
    /**
     *
     * @param time
     * @return "MM-dd HH:mm"
     */
    public static String timeToString3(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "HH:mm MM-dd"
     */
    public static String timeToString1(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm MM-dd");
        return simpleDateFormat.format(time);
    }

    public static String timeToString4(long time) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time);
//        String am_pm;
//        if(calendar.get(Calendar.AM_PM) == Calendar.AM){//上午
//            am_pm = "am";
//        }else{
//            am_pm = "pm";
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa MM-dd");
        return  simpleDateFormat.format(time).replace(".","");
    }
    public static String timeToString4_2(long time) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time);
//        String am_pm;
//        if(calendar.get(Calendar.AM_PM) == Calendar.AM){//上午
//            am_pm = "am";
//        }else{
//            am_pm = "pm";
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa MM-dd",Locale.US);
        return  simpleDateFormat.format(time).replace(".","");
    }

    public static String timeToString9(long time) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time);
//        String am_pm;
//        if(calendar.get(Calendar.AM_PM) == Calendar.AM){//上午
//            am_pm = "am";
//        }else{
//            am_pm = "pm";
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa",Locale.US);
        return  simpleDateFormat.format(time).replace(".","");
    }
}
