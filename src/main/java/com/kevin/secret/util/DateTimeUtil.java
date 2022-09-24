package com.kevin.secret.util;

import cn.hutool.core.lang.Assert;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateTimeUtil {

    public static final String TIME_PATTERN_01 = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTERN_02 = "yyyy-MM-dd";

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式
     */


    public static LocalDateTime stringToLocalDateTime(String stringTime, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern); // yyyy-MM-dd HH:mm:ss
        return LocalDateTime.parse(stringTime, df);
    }

    public static LocalDateTime stringToLocalDateTime(String stringTime) {
        return stringToLocalDateTime(stringTime, TIME_PATTERN_01);
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }





    public static LocalTime stringToLocalTime(String stringTime, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalTime.parse(stringTime, df);
    }

    public static LocalTime stringToLocalTime(String stringTime) {
        return stringToLocalTime(stringTime, "HH:mm:ss");
    }

    public static LocalDateTime plusDays(LocalDateTime start, Long days) {
        Assert.notNull(days, "days is null");
        Assert.notNull(start, "start is null");
        return start.plusDays(days);
    }

    public static LocalDateTime minusDays(LocalDateTime start, Long days) {
        Assert.notNull(days, "days is null");
        Assert.notNull(start, "start is null");
        return start.minusDays(days);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId)
                .toLocalDateTime();
    }

    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId)
                .toLocalDate();
    }

    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId)
                .toLocalTime();
    }

    /**
     * 获取年龄方法
     *
     * @param birth 生日
     */
    public static int getAge(String birth) {
        LocalDate birthLocalDate = LocalDate.parse(birth, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate nowLocalDate = LocalDate.now();
        return birthLocalDate.until(nowLocalDate).getYears();
    }


    /**
     * 获取两个日期相差多少天
     *
     * @param nowDate 开始时间
     * @param endDate 截止时间
     * @return
     */
    public static Long getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        System.out.println("++getDatePoor+++" + day);
        return day;
    }

    public static DayOfWeek weekValueToDayOfWeek(int weekValue) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (weekValue == dayOfWeek.getValue()) {
                return dayOfWeek;
            }
        }
        throw new IllegalArgumentException("weekValue is error.(1,2,3,4,5,6,7)");
    }

   /**
    * 日期减年，月，日
    *
    * @param date 日期
    * @param flag 分类 year,month,day
    * @param v 减的数量
    * @param date 返回的日期格式
    * @return
    * */
    public  static String dateSubtract(Date date,String flag,int v,String pattern){
        Calendar calendar = Calendar.getInstance(); //创建Calendar 的实例
        if(flag.equals("year")){
            calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间
        }else if(flag.equals("month")){
            calendar.add(Calendar.MONTH, -1);//当前时间减去一个月，即一个月前的时间
        }
        else if(flag.equals("day")){
            calendar.add(Calendar.DAY_OF_MONTH,-1); //当前时间减去一天，即一天前的时间
        }
        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        String newDate = fmt.format(calendar.getTime());
        return  newDate;
    }


    /**
     * 获取当前系统时间
     * yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime() {
        String result = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN_01);
        result = simpleDateFormat.format(calendar.getTime());
        return result;
    }

    /**
     * 得到当前时间年月
     *
     * @return
     */
    public static String getCurrentYM() {
        String result = null;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String month = String.format("%0$02d", calendar.get(Calendar.MONTH) + 1);
        result = year + month;
        return result;
    }

    /**
     * 获取本月第一天和最后一天
     * @return
     */
    public static List<String> getThisMonthFirstDayAndLastDay(){
        List<String> times = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN_02);
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH,1);

        times.add(simpleDateFormat.format(calendar.getTime()));

        calendar.roll(Calendar.DAY_OF_MONTH,-1);
        times.add(simpleDateFormat.format(calendar.getTime()));
        return times;
    }


    /**
     * 获取今天天和明天
     * @return
     */
    public static List<String> getTodayAndTomorrow(){
        List<String> times = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN_02);

        times.add(simpleDateFormat.format(calendar.getTime()));
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE) + 1);
        times.add(simpleDateFormat.format(calendar.getTime()));
        return times;
    }

    /**
     * 获取当前往后推30天的时间
     * @return
     */
    public static String getDayTimeAfterAMonth(){
        String result = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN_01);

        calendar.add(Calendar.DATE,30);

        result = simpleDateFormat.format(calendar.getTime());

        return result;

    }

    /**
     * 获取该时间往后推N天的时间
     * @return
     */
    public static String getDayTimeAfterADays(String startTime, Integer days){
        String result = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN_01);
        Date date = null;
        try {
            date = simpleDateFormat.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(date);

        calendar.add(Calendar.DATE,days);

        result = simpleDateFormat.format(calendar.getTime());

        return result;

    }
}
