package com.li.yu.mvc.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
    private static SimpleDateFormat sdf;
    private static SimpleDateFormat sf = null;

    /* 获取系统时间 格式为："yyyy/MM/dd " */
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }
    /* 获取系统时间 格式为："yyyy/MM/dd HH:mm:ss" */
    public static String getCurrentDateTime() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }
    /* 时间戳转换成字符窜 */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /* 将字符串转为时间戳 */
    public static long getStringToDate(String time) {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }
    /*
     *获取分钟小时时间
     */
    public static String getStringHourMin(long time){
        Date d = new Date(time);
        sf = new SimpleDateFormat("HH:mm");
        return sf.format(d);
    }

    /**
     * 字符串装换成 long 毫秒
     * * @param time
     * @return
     */
    public static long getHourTimeMillseconds(String time){
        sdf = new SimpleDateFormat("HH:MM");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
    /* 将字符串转为中文格式 */
    public static String getStringToCN(String time) {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(date);
    }
    /**
     * 两个日期相差几天
     * @param startTime
     * @param endTime
     * @param format
     * @return
     */
    public static long dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            if (day>=1) {
                return day;
            }else {
                if (day==0) {
                    return 1;
                }else {
                    return 0;
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }
    /**
     * 两个时间相差小时
     * @param startTime
     * @param endTime
     * @param format
     * @return
     */
    public static long timeDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            if (hour>=1) {
                return hour;
            }else {
                if (hour==0) {
                    return hour;
                }else {
                    return hour;
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
            Log.i("dateutils", "convert: "+e.toString());
        }
        return 0;

    }
    /**
     * 比较两个日期大小
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date addWeeks(Date date, int weeks) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+(weeks*7));
        return calendar.getTime();
    }
    public static Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+months);
        return calendar.getTime();
    }

    public static Date addYears(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+years);
        return calendar.getTime();
    }



    public static Date geLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
//        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    public static Date getThisWeekSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, 6);
        return calendar.getTime();
    }
    public static Date getFirstDayofMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static Date getLastDayofMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return cal.getTime();
    }

    public static Date getFirstDayofYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentYear = cal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static Date getLastDayofYear(Date date){
        Calendar currCal= Calendar.getInstance();
        currCal.setTime(date);
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }
}
