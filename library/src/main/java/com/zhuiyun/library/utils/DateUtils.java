package com.inkbird.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtils {
    private static final long ONE_DAY_MS = 24 * 60 * 60 * 1000;

    public static List<String> get24Date() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 24; i++) {
            if(i % 2 == 0) {
                if(i < 10){
                    list.add("0"+i + ":00");
                }else {
                    list.add(i + ":00");
                }
            }
        }
        return list;
    }

    public static Calendar get24EndTime(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c;
    }

    public static long getDataBeginTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static long getThreeMonthsBeginTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -92);
        //c.add(Calendar.MONTH, -6);
        return c.getTimeInMillis();
    }

    public static long getSixMonthsBeginTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -184);
        //c.add(Calendar.MONTH, -6);
        return c.getTimeInMillis();
    }

    public static long getDataBeginTime(Calendar c) {
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static long getDataEndTime(Calendar c) {
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static Calendar getDataBeginCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    public static long getTimeOfWeekStart() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek());
        return ca.getTimeInMillis();
    }

    public static long getTimeOfMonthStart() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return ca.getTimeInMillis();
    }

    public static long getTimeOfYearStart() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_YEAR, 1);
        return ca.getTimeInMillis();
    }

    /**
     * 获取一个月前的日期
     *
     * @return
     */
    public static long getMonthAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取一个星期前的日期
     *
     * @return
     */
    public static long getWeekAgo() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -7);
        String monthAgo = simpleDateFormat.format(calendar.getTime());
        return calendar.getTimeInMillis();
    }

    /**
     *
     * @param calendar
     * @return "yyyy-MM-dd"
     */
    public static String timeToString1(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     *
     * @param time
     * @return "yyyy-M-d"
     */
    public static String timeToString1(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @return "yyyy-MM"
     */
    public static String timeToString2(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param calendar
     * @return "yyyyMMdd"
     */
    public static String timeToString3(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(calendar.getTime());
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
     * @return "HH:mm      MM-dd-yyyy"
     */
    public static String timeToString4(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm      MM-dd-yyyy");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "HH:mm      MM-dd-yyyy"
     */
    public static String timeToString4_1(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa   MM-dd-yyyy");
        return simpleDateFormat.format(time).replace(".","");
    }

    /**
     *
     * @param time
     * @return "yy-MM-dd\nHH:mm"
     */
    public static String timeToString5(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm \n MM/dd");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "dd"
     */
    public static String timeToString6(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "HH:mm\ndd-MM-yyyy"
     */
    public static String timeToString7(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm\ndd-MM-yyyy");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "yyyy-MM-dd HH:mm"
     */
    public static String timeToString8(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "MM dd yyyy 'at' hh:mm a"
     */
    public static String timeToString9(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy 'at' hh:mm a", Locale.ENGLISH);
        return sdf.format(time);
    }

    /**
     *
     * @param time
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String timeToString10(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "HH:mm MM-dd"
     */
    public static String timeToString11(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm MM-dd");
        return simpleDateFormat.format(time);
    }
    public static String timeToString11_2(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm MM/dd");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "MM dd yyyy 'at' HH:mm"
     */
    public static String timeToString12(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy 'at' HH:mm");
        return sdf.format(time);
    }

    /**
     *
     * @param time
     * @return "HH:mm MM-dd"
     */
    public static String timeToString13(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "MM-dd HH:mm"
     */
    public static String timeToString14(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd\nHH:mm");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "HH:mm MM-dd"
     */
    public static String timeToString15(long time) {
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

    public static String timeToString15_2(long time) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time);
//        String am_pm;
//        if(calendar.get(Calendar.AM_PM) == Calendar.AM){//上午
//            am_pm = "am";
//        }else{
//            am_pm = "pm";
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa MM/dd");
        return  simpleDateFormat.format(time).replace(".","");
    }
    /**
     *
     * @param time
     * @return "yyyy/MM/dd HH:mm"
     */
    public static String timeToString16(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "HH:mm"
     */
    public static String timeToStringIvc(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "yyyy-MM"
     */
    public static String timeToStringIvc2(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "HH:mm:ss"
     */
    public static String timeToStringIvc3(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "yyyy-MM-dd"
     */
    public static String timeToStringIvc4(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "MM/dd, HH:mm:ss"
     */
    public static String timeToStringIvc5(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd, HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "yyyy-MM-dd"
     */
    public static String timeToStringIvc6(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return hh:mm aa
     */
    public static String timeToStringIvc7(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "yyyy"
     */
    public static String timeToYear(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "MM"
     */
    public static String timeToYear2(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "MM/dd"
     */
    public static String timeToMMDD(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return "MM/dd/yyyy"
     */
    public static String timeToMMDDYYYY(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(time);
    }

    public static String timeToMMYYYY(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        return simpleDateFormat.format(time);
    }
    public static String timeToHHMMDDYYYY(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm MM/dd");
        return simpleDateFormat.format(time);
    }

    public static String timeToMMDDYYYY2(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        return simpleDateFormat.format(time);
    }

    public static String timeToDDYYYY(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd yyyy");
        return simpleDateFormat.format(time);
    }

    public static String timeToDD(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        return simpleDateFormat.format(time);
    }

    /**
     * 获取当前月第一天
     * @return
     */
    public static Calendar getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        // 获取某月最小天数
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
//        calendar.set(Calendar.DAY_OF_MONTH, firstDay);
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1,0,0,0);
        return calendar;
    }

    public static Calendar getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 设置月份
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),23,59,59);
        return calendar;
    }

    public static Calendar getSunday() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        //当前时间，貌似多余，其实是为了所有可能的系统一致
        c.setTimeInMillis(System.currentTimeMillis());
//        System.out.println("当前时间:"+format.format(c.getTime()));
//        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        System.out.println("周一时间:"+format.format(c.getTime()));
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//        System.out.println("周一时间:"+format.format(c.getTime()));
        return c;
    }

    public static Calendar getThisWeekMonday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 获取指定周的最后一天
     *
     * @return
     */
    public static long getLastDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        // 设置年份
//        cal.set(Calendar.YEAR, year);
//        // 设置周
//        cal.set(Calendar.WEEK_OF_YEAR, week);
        // 设置该周第一天为星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 设置最后一天是星期日
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 5); // Sunday
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTimeInMillis();
    }

    public static List<String> getBetweenDates(long start, long end) {
        List<String> result = new ArrayList<>();

        Calendar tempStart = Calendar.getInstance();
        tempStart.setTimeInMillis(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTimeInMillis(end);

        while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
            result.add(getCustonFormatTime(tempStart.getTimeInMillis()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }

//        Collections.reverse(result);
        return result;
    }

    /**
     * 格式化传入的时间
     *
     * @param time 需要格式化的时间
     * @return
     */
    public static String getCustonFormatTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        Date d1 = new Date(time);
        return format.format(d1);
    }

    public static long getStrToLongStart(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date().getTime();
    }

    public static long getStrToLongEnd(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date().getTime();
    }
}