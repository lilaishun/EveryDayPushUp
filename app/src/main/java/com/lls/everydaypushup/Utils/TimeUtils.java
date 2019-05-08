package com.lls.everydaypushup.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class TimeUtils {
    private static StringBuilder mFormatBuilder;
    private static Formatter mFormatter;
    public static final int MIN_YEAR = 1970;
    public static final int MAX_YEAR = 2099;

    /**
     * Wheel TYPE Year
     */
    public static final int TYPE_YEAR   = 1;
    /**
     * Wheel TYPE Month
     */
    public static final int TYPE_MONTH  = 2;
    /**
     * Wheel TYPE Day
     */
    public static final int TYPE_DAY    = 3;
    /**
     * Wheel TYPE Hour
     */
    public static final int TYPE_HOUR   = 4;
    /**
     * Wheel TYPE Minute
     */
    public static final int TYPE_MINUTE = 5;

    private TimeUtils() {
    }

    /**
     * 创建年份数据
     *
     * @return
     */

    public static List<String> getItemList(int type) {
        if (type == TYPE_YEAR) {
            return createdYear();
        } else if (type == TYPE_MONTH) {
            return createdMonth();
        } else if (type == TYPE_DAY) {
            return createdDay();
        } else if (type == TYPE_HOUR) {
            return createdHour();
        } else if (type == TYPE_MINUTE) {
            return createdMniter();
        } else {
            throw new IllegalArgumentException("type is illegal");
        }
    }

    private static List<String> createdYear() {
        List<String> wheelString = new ArrayList<>();
        for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
            wheelString.add(Integer.toString(i));
        }
        return wheelString;
    }

    /**
     * 创建月份数据
     *
     * @return
     */
    private static List<String> createdMonth() {
        List<String> wheelString = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }


    /**
     * 创建日期数据
     *
     * @return
     */
    private static List<String> createdDay() {
        List<String> wheelString = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }

    /**
     * 根据年 月创建日期数据
     *
     * @return
     */
    public static List<String> createdDay(int year, int month) {
        List<String> wheelString = new ArrayList<>();
        int          size        = 0;
        if (isLeapMonth(month)) {
            size = 31;
        } else if (month == 2) {
            if (isLeapYear(year)) {
                size = 29;
            } else {
                size = 28;
            }
        } else {
            size = 30;
        }

        for (int i = 1; i <= size; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }


    /**
     * 创建小时数据
     *
     * @return
     */
    private static List<String> createdHour() {
        List<String> wheelString = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }

    /**
     * 创建分钟数据
     *
     * @return
     */
    private static List<String> createdMniter() {
        List<String> wheelString = new ArrayList<>();
        for (int i = 0; i <= 59; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }

    /**
     * 计算大小月
     *
     * @param month
     * @return
     */
    private static boolean isLeapMonth(int month) {
        return month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10 || month == 12;
    }

    /**
     * 计算闰年
     *
     * @param year
     * @return
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * 把毫秒转换成：01：20：30这样的形式
     * @param timeMs
     * @return
     */
    public static String stringForTime(int timeMs){
//转换成字符串的时间
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        int seconds = timeMs % 60;
        int minutes = (timeMs/60)%60;
        int hours = timeMs/3600;
        mFormatBuilder.setLength(0);
        //  if(hours>0){
        return mFormatter.format("%02d:%02d:%02d",hours,minutes,seconds).toString();
//        } else {
//            return mFormatter.format("%02d:%02d",minutes,seconds).toString();
//        }
    }
    public static String ForData(){
        Calendar c = Calendar.getInstance();//
        int  mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        int mWay = c.get(Calendar.DAY_OF_WEEK);// 获取当前日期的星期
        int  mHour = c.get(Calendar.HOUR_OF_DAY);//时
        int  mMinute = c.get(Calendar.MINUTE);//分
        return  mYear+"/"+mMonth+"/"+mDay+"/"+" "+mHour+":"+mMinute;
    }
}
