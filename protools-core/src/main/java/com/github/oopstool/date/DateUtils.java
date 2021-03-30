package com.github.oopstool.date;

import com.github.oopstool.string.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 基于Java8的时间工具类
 *
 * @author HouGY
 * @since 1.0.0
 */
public class DateUtils {


    /**
     * 将Long类型时间戳(毫秒)值转换成String时间格式
     *
     * @param time    时间毫秒值
     * @param pattern 格式
     * @return 返回字符串时间
     */
    public static String convertTimeToString(Long time, String pattern) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(pattern);
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    /**
     * 将Long类型的时间戳(秒级)转换成String 类型的时间格式
     *
     * @param time    时间秒值
     * @param pattern 格式
     * @return 返回字符串时间
     */
    public static String convertSecTimeToString(Long time, String pattern) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(pattern);
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time * 1000), ZoneId.systemDefault()));
    }

    /**
     * 获取当前时间年
     *
     * @return 年
     */
    public static int getYear() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear();
    }

    /**
     * 获取当前时间月份
     *
     * @return 月份
     */
    public static int getMonth() {
        LocalDate localDate = LocalDate.now();
        return localDate.getMonth().getValue();
    }

    /**
     * 获取当前月的第几天
     *
     * @return 几号
     */
    public static int getDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        return localDate.getDayOfMonth();
    }

    /**
     * 格式化日期为字符串
     * Date 类型过于久远，建议使用LocalDateTime
     *
     * @param date    date
     * @param pattern 格式
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化日期为字符串
     *
     * @param localDateTime localDateTime
     * @param pattern       格式
     * @return 日期字符串
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 解析字符串日期为LocalDateTime类型
     * 如需要date类型请调用localDateTime2Date转换
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return LocalDateTime
     */
    public static LocalDateTime parseByPattern(String dateStr, String pattern) {
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            localDateTime = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern)).atStartOfDay();
        }
        return localDateTime;
    }

    /**
     * 解析字符串日期为Date类型
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return LocalDateTime
     */
    public static Date parseDateByPattern(String dateStr, String pattern) {
        LocalDateTime localDateTime = parseByPattern(dateStr, pattern);
        return localDateTime2Date(localDateTime);
    }


    /**
     * 为Date增加分钟,减传负数
     *
     * @param date        日期
     * @param plusMinutes 要增加的分钟数
     * @return 新的日期
     */
    public static Date addMinutes(Date date, Long plusMinutes) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime newDateTime = dateTime.plusMinutes(plusMinutes);
        return localDateTime2Date(newDateTime);
    }


    /**
     * 增加小时,减传负数
     *
     * @param date date
     * @param hour 要增加的小时数
     * @return 新的date
     */
    public static Date addHour(Date date, Long hour) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime newDateTime = dateTime.plusHours(hour);
        return localDateTime2Date(newDateTime);
    }

    /**
     * 加/减月份
     *
     * @param date  date
     * @param month 月份,如果为负数，则为减少的月份
     * @return Date
     */
    public static Date addMonth(Date date, long month) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime newDateTime = dateTime.plusMonths(month);
        return localDateTime2Date(newDateTime);
    }


    /**
     * 加/减天
     *
     * @param day 天数,如果为负数，则为减少的天数
     * @return Date
     */
    public static Date addDay(Date date, long day) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDate = dateTime.plusDays(day);
        return localDateTime2Date(localDate);
    }

    /**
     * 加/减星期
     *
     * @param week 星期,如果为负数，则为减少的星期
     * @return Date
     */
    public static Date addWeek(long week) {
        LocalDate localDate = LocalDate.now().plusWeeks(week);

        return localDate2Date(localDate);
    }

    /**
     * 当前时间加/减月份
     *
     * @param month 月份,如果为负数，则为减少的月份
     * @return Date
     */
    public static Date addMonth(long month) {
        LocalDate localDate = LocalDate.now().plusMonths(month);

        return localDate2Date(localDate);
    }

    /**
     * @return 返回当天的起始时间
     */
    public static Date getStartTime() {
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        return localDateTime2Date(now);
    }


    /**
     * @return 返回当天的结束时间
     */
    public static Date getEndTime() {
        LocalDateTime now = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
        return localDateTime2Date(now);
    }

    /**
     * LocalDate类型转为Date
     *
     * @param localDate LocalDate object
     * @return Date object
     */
    public static Date localDate2Date(LocalDate localDate) {

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());

        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime类型转为Date
     *
     * @param localDateTime LocalDateTime object
     * @return Date object
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 查询当前年的第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getFirstDayOfCurrentYear(String pattern) {
        LocalDate localDate = LocalDate.now().withMonth(1).withDayOfMonth(1);

        if (StringUtils.isBlank(pattern)) {
            pattern = DatePattern.PURE_DATE_PATTERN;
        }

        return format(localDate2Date(localDate), pattern);
    }

    /**
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getLastMonthFirstDayOfPreviousYear(String pattern) {
        LocalDate localDate = LocalDate.now().minusYears(1L).withMonth(12).withDayOfMonth(1);

        if (StringUtils.isBlank(pattern)) {
            pattern = DatePattern.PURE_DATE_PATTERN;
        }

        return format(localDate2Date(localDate), pattern);
    }

    /**
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getLastMonthLastDayOfPreviousYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).with(TemporalAdjusters.lastDayOfYear());

        if (StringUtils.isBlank(pattern)) {
            pattern = DatePattern.PURE_DATE_PATTERN;
        }

        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询给定月份的第一天
     *
     * @param date    时间
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 返回字符串日期
     */
    public static String getFirstDayOfMonth(Date date, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                .withDayOfMonth(1);

        if (StringUtils.isBlank(pattern)) {
            pattern = DatePattern.PURE_DATE_PATTERN;
        }

        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询当前月份的最后一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 返回字符串日期
     */
    public static String getLastDayOfMonth(Date date, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime lastDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
        if (StringUtils.isBlank(pattern)) {
            pattern = DatePattern.PURE_DATE_PATTERN;
        }
        return format(localDateTime2Date(lastDay), pattern);
    }


    /**
     * 获取当前日期字符串格式
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getCurrentDay(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();

        if (StringUtils.isBlank(pattern)) {
            pattern = DatePattern.PURE_DATE_PATTERN;
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 获取当前毫秒值
     *
     * @return 当前时间毫秒值
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 将字符串转日期成Long类型的时间戳(毫秒级)，格式为：pattern
     *
     * @param time    日期
     * @param pattern 格式
     * @return 返回Long类型的时间戳(毫秒级)
     */
    public static Long convertTimeToLong(String time, String pattern) throws ParseException {
        DateFormat format = new SimpleDateFormat(pattern);
        Calendar calBegin = new GregorianCalendar();
        calBegin.setTime(format.parse(time));
        return calBegin.getTimeInMillis();
    }

    /**
     * 将字符串转日期成Long类型的时间戳(秒级)，格式为：pattern
     *
     * @param time    日期
     * @param pattern 格式
     * @return 返回Long类型的时间戳(秒级)
     */
    public static Long convertTimeToSecLong(String time, String pattern) throws ParseException {
        DateFormat format = new SimpleDateFormat(pattern);
        Calendar calBegin = new GregorianCalendar();
        calBegin.setTime(format.parse(time));
        return calBegin.getTimeInMillis() / 1000;
    }


    /**
     * 根据秒 得到xx天xx时xx分xx秒
     *
     * @param s 秒
     * @return 得到xx天xx时xx分xx秒
     */
    public static String formatSecTime(long s) {
        long days = s / (60 * 60 * 24);
        long hours = (s % (60 * 60 * 24)) / (60 * 60);
        long minutes = (s % (60 * 60)) / 60;
        long seconds = s % 60;
        String dateTimes;
        if (days > 0) {
            dateTimes = days + "天" + hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        } else if (hours > 0) {
            dateTimes = hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        } else if (minutes > 0) {
            dateTimes = minutes + "分钟"
                    + seconds + "秒";
        } else {
            dateTimes = seconds + "秒";
        }

        return dateTimes;
    }


    /**
     * 获取今天
     *
     * @return String
     */
    public static String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取昨天-年、月、日
     *
     * @return String
     */
    public static String getYesterday(String pattern) {
        Date date = addDay(new Date(), -1);
        return format(date, pattern);
    }

    /**
     * 计算2个日期之间的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 返回相隔天数
     */
    public static long getDatesNumBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 获得2个日期之间的所有的日期
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 返回2个日期之间所有的日期
     */
    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(startDate::plusDays)
                .collect(Collectors.toList());
    }

}

