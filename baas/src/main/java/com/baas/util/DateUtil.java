package com.baas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期小工具类
 * 
 * @author anhong.wang
 * @version $Id: DateUtil.java, v 0.1 2013-1-18 下午3:23:50 anhong.wang Exp $
 */
public final class DateUtil {

    private static final Logger  logger                = LoggerFactory.getLogger(DateUtil.class);
    public static final String   DEFAULT_FORMAT        = "yyyy-MM-dd HH:mm:ss";
    public static final String   REPAIR_FORMAT         = "yyyyMMddHHmm";
    public static final String   LIGHTPACKAGE_FORMAT   = "yyyyMMddHHmmss";
    public static final String   YYYYMMDD_HHMM         = "yyyy-MM-dd HH:mm";
    public static final String   YYYYMMDD_HH           = "yyyy-MM-dd HH";
    public static final String   YYYY_MM_DD            = "yyyy-MM-dd";
    public static final String   YY年MM月DD日             = "yyyy年MM月dd日";
    public static final String   YYYYMMDD_hh_mm_ss     = "yyyy-MM-dd_HH:mm:ss";
    public static final String   YYYYMMDD_hh_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss,SSS";
    public static final String   YYYYMMDDHHMMSS        = "yyyyMMddHHmmss";
    public static final String   hh_mm_ss              = "HH:mm:ss";
    public static final String   HH_MM                 = "HH:mm";
    public static final String   YYMMDD                = "yyMMdd";
    public static final String   yyyyMMdd              = "yyyyMMdd";
    public static final String   MMDD                  = "MM.dd";
    public static final long     DAY                   = 24 * 60 * 60 * 1000;
    public static final long     MIN                   = 60 * 1000;
    public static final long     ONE_DAY_SECONDS       = 86400;

    public static final String[] DAY_OF_WEEK_MAP       = new String[] { "一", "二", "三", "四", "五",
            "六", "日"                                  };

    /**
     * 格式化日期
     * 
     * @param date
     * @param format
     * @return java.lang.String
     */
    public static String format(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        return sdf.format(date);
    }



    /**
     * 格式化日期
     * 
     * @param time
     * @param format
     * @return
     */
    public static String format(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        return sdf.format(new Date(time));
    }
/**
 * 
 * @param time
 * @return
 */
    public static String format(long time) {
        return format(time, DEFAULT_FORMAT);
    }

    /**
     * 字符串日期格式转换
     * 
     * @param dateTime 日期
     * @param sourceFormat 原格式
     * @param targetFormat 目标格式
     * @return
     */
    public static String format(String dateTime, String sourceFormat, String targetFormat) {
        SimpleDateFormat source = new SimpleDateFormat(sourceFormat, Locale.CHINESE);
        SimpleDateFormat target = new SimpleDateFormat(targetFormat, Locale.CHINESE);
        Date d;
        try {
            d = source.parse(dateTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException(dateTime);
        }
        return target.format(d);
    }

    /**
     * 格式化日期, 按照默认样式. 默认格式: yyyy-mm-dd hh:MM:ss
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

    /**
     * 解析日期 默认格式: yyyy-mm-dd hh:MM:ss
     * 
     * @param dateString
     * @return java.util.Date
     */
    public static Date parseDate(String dateString) {
        return parseDate(dateString, DEFAULT_FORMAT);
    }

    /**
     * 这个方法用来反解二逼的toString后的数据
     * 
     * @param dateString
     * @return
     */
    public static Date parseFromDateString(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",
            Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(dateString);
        }
        return date;
    }

    /**
     * 解析日期, 按照默认样式.
     * 
     * @param dateString
     * @param style
     * @return java.util.Date
     */
    public static Date parseDate(String dateString, String style) {
        SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.CHINESE);
        try {
            return sdf.parse(dateString);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取当前时间
     *
     * @return java.util.Date
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 获取位于 今天 00:00:00 时间点的日期
     * 
     * @return java.util.Date
     */
    public static Date getToday() {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }

    /**
     * 获取位于 明天00:00:00 时间点的日期
     * 
     * @return java.util.Date
     */
    public static Date getTomorrow() {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(Calendar.DATE, rightNow.get(Calendar.DATE) + 1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }

    /**
     * 根据当前时间，获取位于 明天00:00:00 时间点的日期
     * 
     * @return java.util.Date
     */
    public static Date getTomorrow(Date startDate) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startDate);
        rightNow.set(Calendar.DATE, rightNow.get(Calendar.DATE) + 1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }

    /**
     * 获取该日期的 昨天00:00:00
     * 
     * @return
     */
    public static Date getYesterday() {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(Calendar.DATE, rightNow.get(Calendar.DATE) - 1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);

        return rightNow.getTime();
    }
    /**
     * 
     * @return
     */
    public static Date getMorning() {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        dateCalendar.set(Calendar.MINUTE, 0);
        dateCalendar.set(Calendar.SECOND, 0);
        dateCalendar.set(Calendar.MILLISECOND, 0);

        return dateCalendar.getTime();
    }

    /**
     * 获取凌晨
     * 
     * @param date
     * @return
     */
    public static Date getMorning(Date date) {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        dateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        dateCalendar.set(Calendar.MINUTE, 0);
        dateCalendar.set(Calendar.SECOND, 0);
        dateCalendar.set(Calendar.MILLISECOND, 0);

        return dateCalendar.getTime();
    }
   /**
    * 
    * @param time
    * @return
    */
    public static long getMorning(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获得 23：59
     * 
     * @param date
     * @return
     */
    public static Date getNight(Date date) {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        dateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        dateCalendar.set(Calendar.MINUTE, 59);
        dateCalendar.set(Calendar.SECOND, 0);
        dateCalendar.set(Calendar.MILLISECOND, 0);

        return dateCalendar.getTime();
    }
    /**
     * 
     * @param date
     * @return
     */
    public static long getNight(long date) {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTimeInMillis(date);
        dateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        dateCalendar.set(Calendar.MINUTE, 59);
        dateCalendar.set(Calendar.SECOND, 0);
        dateCalendar.set(Calendar.MILLISECOND, 0);
        return dateCalendar.getTimeInMillis();
    }

    /**
     * 获取该日期的昨天
     * 
     * @param date
     * @return
     */
    public static Date getYesterday(Date date) {
        long time = date.getTime() - DAY;
        return new Date(time);
    }

    /**
     * 取得当前周报默认的时间是上周五到周四
     * 
     * @param date
     * @return
     * @author chuan.zhou
     */
    public static List<String> getCurrentWeekly(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);

        /**
         * day：1-星期日 2-星期一 依次类推 五 六 日 一 二 三 四 6 7 1 2 3 4 5 如果day=1 星期日 time -
         * day * 2 如果day=2 星期一 time - day * 3 如果day=3 星期二 time - day * 4 如果day=4
         * 星期三 time - day * 5 如果day=5 星期四 time - day * 6 如果day=6 星期五 time
         */
        long time = date.getTime();
        long lone = time - DAY * ((day + 1) % 7);
        long ltwo = lone + DAY * 1;
        long lthree = lone + DAY * 2;
        long lfour = lone + DAY * 3;
        long lfive = lone + DAY * 4;
        long lsix = lone + DAY * 5;
        long lseven = lone + DAY * 6;

        if (logger.isInfoEnabled()) {
            logger.info(date.toString());
        }

        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD, Locale.CHINESE);
        String sone = sdf.format(new Date(lone));
        String stwo = sdf.format(new Date(ltwo));
        String sthree = sdf.format(new Date(lthree));
        String sfour = sdf.format(new Date(lfour));
        String sfive = sdf.format(new Date(lfive));
        String ssix = sdf.format(new Date(lsix));
        String sseven = sdf.format(new Date(lseven));

        List<String> list = new ArrayList<String>();
        list.add(sone);
        list.add(stwo);
        list.add(sthree);
        list.add(sfour);
        list.add(sfive);
        list.add(ssix);
        list.add(sseven);

        return list;
    }

    /**
     * 获取日期是周几
     * 
     * @param date
     * @return java.util.Date
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 获取日期是周几
     * 
     * @return java.util.Date
     */
    public static int getDayOfWeek() {
        return getDayOfWeek(new Date());
    }

    /**
     * 取得上前周报默认的时间是上上周五到下周四
     * 
     * @param date
     * @return
     */
    public static List<String> getLastWeekly(Date date) {
        long time = date.getTime() - DAY * 7;
        return getCurrentWeekly(new Date(time));
    }

    /**
     * 设置日期
     * 
     * @return
     */
    public static Date getFirstDayofYear() {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(Calendar.MONTH, Calendar.JANUARY);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }

    /**
     * 设置日期
     * 
     * @return
     */
    public static Date getFirstDayofNextYear() {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(Calendar.YEAR, rightNow.get(Calendar.YEAR) + 1);
        rightNow.set(Calendar.MONTH, Calendar.JANUARY);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }

    /**
     * 设置日期
     * 
     * @return
     */
    public static Date getFirstDayofNextYear(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.YEAR, rightNow.get(Calendar.YEAR) + 1);
        rightNow.set(Calendar.MONTH, Calendar.JANUARY);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }
     /**
      * 
      * @param date
      * @return
      */
    public static Date getFirstDayofYear(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }

    /**
     * 获得当期日期的本周周一开始时间
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayofWeek(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }

    /**
     * 获得当期日期的本月一号开始开始时间
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayofMonth(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }

    /**
     * 获得当期日期的本月一号开始开始时间
     * 
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getFirstDayofQuarter(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        int month = date.getMonth() / 3 * 3;
        rightNow.set(Calendar.MONTH, month);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        return rightNow.getTime();
    }

    /***
     * 根据给定的日期，确定指定时间（len天)之后的时间
     * 
     * @param date
     * @param len
     * @return java.util.Date
     */
    public static Date getIntervalEndDate(Date date, int len) {
        long time = date.getTime() + DAY * len;
        return new Date(time);
    }
    
    
    /***
     * 根据给定的日期，确定指定时间（len分钟)之后的时间
     * 
     * @param date
     * @param len
     * @return java.util.Date
     */
    public static Date getIntervalEndMiniute(Date date, int len) {
        long time = date.getTime() + MIN * len;
        return new Date(time);
    }
    

    /***
     * 根据给定的日期，确定指定时间（len天)之前的时间
     * 
     * @param date
     * @param len
     * @return
     */

    public static Date getPreLenDate(Date date, int len) {
        long time = date.getTime() - DAY * len;
        return new Date(time);
    }

    /**
     * 获取两个日期相隔的天数
     * 
     * @param startDay
     * @param endDay
     * @return
     */
    public static int getDifferentDays(Date startDay, Date endDay) {
        long differentTime = endDay.getTime() - startDay.getTime();
        long dayTime = 60 * 60 * 24 * 1000;
        int differentDay = (int) (differentTime / dayTime);
        if (differentTime % dayTime > 0) {
            differentDay = differentDay + 1;
        }
        return differentDay;
    }
   /**
    * 
    * @param date
    * @param secs
    * @return
    */
    public static Date addSeconds(Date date, long secs) {
        return new Date(date.getTime() + (secs * 1000));
    }
   /**
    * 
    * @param date
    * @param days
    * @return
    */
    public static Date addDays(Date date, long days) {
        return addSeconds(date, days * ONE_DAY_SECONDS);
    }
   /**
    * 
    * @param date
    * @param amount
    * @return
    */
    public static Date addMonths(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, amount);
        return c.getTime();
    }

    /**
     * 从小时往下都设为0
     * 
     * @param date
     * @return
     */
    public static Date setHoursToEmpty(Date date) {
        Calendar calendar = Calendar.getInstance();
        int timeTypes[] = { Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND,
                Calendar.MILLISECOND };
        calendar.setTime(date);
        for (int i = 0; i < timeTypes.length; i++) {
            calendar.set(timeTypes[i], 0);
        }
        return calendar.getTime();
    }

    /**
     * @param date
     * @return
     * @throws java.text.ParseException
     */
    public static String[] getStringDate(String date) throws ParseException {
        String[] weeks = new String[7];//返回的这周的日期
        String[] a = date.split("-");
        int week = getDayOfWeek(a[0], a[1], a[2]);//获取周几
        int minWeek = 0;
        int maxWeek = 7;

        if (week == 1) {//如果是周日（老外是从周日开始算一周，所以有点恶心）
            weeks[6] = date;
            for (int i = 5; i >= 0; i--) {
                weeks[i] = getFormatDateAdd(getStrToDate(date, YYYY_MM_DD), -1, YYYY_MM_DD);
                date = weeks[i];
            }
        } else {
            int temp = week - 2;
            weeks[temp] = date;
            for (int i = temp - 1; i >= minWeek; i--) {
                weeks[i] = getFormatDateAdd(getStrToDate(date, YYYY_MM_DD), -1, YYYY_MM_DD);
                date = weeks[i];
            }
            date = weeks[temp];
            for (int i = temp + 1; i < maxWeek; i++) {
                weeks[i] = getFormatDateAdd(getStrToDate(date, YYYY_MM_DD), 1, YYYY_MM_DD);
                date = weeks[i];
            }
        }

        return weeks;
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date 给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @param format 输出格式.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static String getFormatDateAdd(Date date, int amount, String format) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return getFormatDateTime(cal.getTime(), format);
    }

    /**
     * 根据给定的格式与时间(Date类型的)，返回时间字符串。最为通用。<br>
     *
     * @param date 指定的日期
     * @param format 日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 返回制定日期字符串的date格式
     *
     * @param date
     * @param format
     * @return
     * @throws java.text.ParseException
     */
    public static Date getStrToDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     * 
     * @param year
     * @param month month是从1开始的12结束
     * @param day
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(String year, String month, String day) {
        Calendar cal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1,
            Integer.parseInt(day));
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 用于检查一个日期是否在今天morning之前 如果date为null 直接返回true
     * 
     * @param date
     * @return
     */
    public static boolean isBeforeMorning(Date date) {
        if (date == null) {
            //直接认为true
            return true;
        }
        return date.before(getMorning());
    }

    /**
     *  将时间（单位：秒）格式化成日期
     * 
     * @param time，单位：秒
     * @param format
     * @return
     */
    public static String formatSecTime(String time, String format) {
        try {
            long mills = Long.parseLong(time) * 1000;
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(new Date(mills));
        } catch (Exception e) {
            logger.error(
                "DateUtil formatSecTime error, [time:" + time + ", format:" + format + "]", e);
            return "";
        }

    }
}
