package com.zzwdkj.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期计算
 *
 * @author yinminjie 2016/6/23.
 */
public class DateUtil {


    /**
     * 时间格式化
     *
     * @param date
     * @return
     */
    public static String dateFormat(Date date, String formatStr) {
        java.text.DateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    private static final SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");

    public static String format2Mint(Date date) {
        return formatter.format(date);
    }

    public static String format3Mint(Date date) {
        return formatter3.format(date);
    }
    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static Date getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return c.getTime();
    }

    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return formatter2.format(c.getTime());
    }

    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return formatter2.format(c.getTime());
    }

    public static String getFirstDayOfMonth(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return formatter2.format(c.getTime());
    }
    /**
     * 通过数目、类型计算出日期
     *
     * @param date
     * @param count 正数或负数，正数表示向将来推，负数表示向过去推
     * @param type  类型
     * @return 新日期
     */
    protected static Date add(Date date, int count, int type) {
        if (date == null) {
            return null;
        }
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);
        c2.add(type, count);
        Date newDate = c2.getTime();
        return newDate;
    }

    /**
     * 通过数目计算出日期
     *
     * @param date
     * @param yearCount 年数。正数或负数，正数表示向将来推，负数表示向过去推
     * @return
     */
    public static Date addYear(Date date, int yearCount) {
        return add(date, yearCount, Calendar.YEAR);
    }

    /**
     * 通过数目计算出日期
     *
     * @param date
     * @param monthCount 月数。正数或负数，正数表示向将来推，负数表示向过去推
     * @return
     */
    public static Date addMonth(Date date, int monthCount) {
        return add(date, monthCount, Calendar.MONTH);
    }

    /**
     * 通过数目计算出日期
     *
     * @param date
     * @param weekCount 星期数。正数或负数，正数表示向将来推，负数表示向过去推
     * @return
     */
    public static Date addWeek(Date date, int weekCount) {
        return add(date, weekCount, Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取日期（前N天或者后N天）
     *
     * @param date
     * @param dayCount 天数。正数或负数，正数表示向将来推，负数表示向过去推
     * @return
     */
    public static Date addDay(Date date, int dayCount) {
        return add(date, dayCount, Calendar.DATE);
    }

    /**
     * 获取日期（前N天或者后N天）
     *
     * @param date
     * @param hourCount 小时数。正数或负数，正数表示向将来推，负数表示向过去推
     * @return
     */
    public static Date addHour(Date date, int hourCount) {
        return add(date, hourCount, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取年份
     *
     * @param date 时间
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        System.out.println(year);
        return year;
    }

    /**
     * 获取月份
     *
     * @param date 时间
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH) + 1;
        System.out.println(month);
        return month;
    }

    /**
     * 获取小时
     *
     * @param date 时间
     * @return
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.HOUR_OF_DAY);
        System.out.println(day);
        return day;
    }

    /**
     * 获取日
     *
     * @param date 时间
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        System.out.println(day);
        return day;
    }


    /**
     * 时间差（小时）
     *
     * @param endDate   结束时间
     * @param stateDate 开始时间
     * @return
     */
    public static long hourDifference(Date endDate, Date stateDate) {
        return (minuteDifference(endDate,stateDate) / 60);
    }

    /**
     * 时间差（秒）
     *
     * @param endDate   结束时间
     * @param stateDate 开始时间
     * @return
     */
    public static long secondDifference(Date endDate, Date stateDate) {
        long timeCount = endDate.getTime() - stateDate.getTime();
        return (timeCount / 1000);
    }

    /**
     * 时间差（分钟）
     *
     * @param endDate   结束时间
     * @param stateDate 开始时间
     * @return
     */
    public static long minuteDifference(Date endDate, Date stateDate) {
        return (secondDifference(endDate,stateDate) / 60 );
    }

    /**
     * 时间差（天数）
     *
     * @param endDate   结束时间
     * @param stateDate 开始时间
     * @return
     */
    public static long daysDifference(Date endDate, Date stateDate) {
        return (hourDifference(endDate,stateDate) / 24);
    }

    public static void main(String[] args) {
        System.out.println(getMondayOfThisWeek());
    }
}
