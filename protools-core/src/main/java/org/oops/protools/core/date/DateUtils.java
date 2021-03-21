package org.oops.protools.core.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.oops.protools.core.string.StringUtils;

/**
 * 基于Java8的时间工具类
 *
 * @author HouGY
 * @since 2021-03-16
 */
public class DateUtils {

    /**
     * java.util.Date EEE MMM zzz 缩写数组
     */
    private final static String[] wtb = { //
        "sun", "mon", "tue", "wed", "thu", "fri", "sat", // 星期
        "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec", //
        "gmt", "ut", "utc", "est", "edt", "cst", "cdt", "mst", "mdt", "pst", "pdt"//
    };

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
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.YEAR);
    }

    /**
     * 获取当前时间月份
     *
     * @return 月份
     */
    public static int getMonth() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.MONTH_OF_YEAR);
    }

    /**
     * 获取当前月的第几天
     *
     * @return 几号
     */
    public static int getMonthOfDay() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.DAY_OF_MONTH);
    }

    /**
     * 格式化日期为字符串
     * Date 类型过于久远，建议使用LocalDateTime
     *
     * @param date    date
     * @param pattern 格式
     * @return 日期字符串
     */
    @Deprecated
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
        //TODO 判断字符串是否是日期
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            localDateTime = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern)).atStartOfDay();
        }
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return localDateTime;
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
    public static Date addDay(long day) {
        LocalDate localDate = LocalDate.now().plusDays(day);
        return localDate2Date(localDate);
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
     * 加/减月份
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
        LocalDateTime localDateTime = LocalDateTime.now().withMonth(1).withDayOfMonth(1);

        if (StringUtils.isBlank(pattern)) {
            pattern = DatePattern.PURE_DATE_PATTERN;
        }

        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getLastMonthFirstDayOfPreviousYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).withMonth(12).withDayOfMonth(1);

        if (StringUtils.isBlank(pattern)) {
            pattern = DatePattern.PURE_DATE_PATTERN;
        }

        return format(localDateTime2Date(localDateTime), pattern);
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
     * 将字符串转日期成Long类型的时间戳(毫秒级)，格式为：pattern
     *
     * @param time
     * @return
     */
    public static Long convertTimeToLong(String time, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        Calendar calBegin = new GregorianCalendar();
        try {
            calBegin.setTime(format.parse(time));
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        long beginTime = calBegin.getTimeInMillis();
        return beginTime;
    }

    /**
     * 将字符串转日期成Long类型的时间戳(秒级)，格式为：pattern
     *
     * @param time
     * @return
     */
    public static Long convertTimeToSecLong(String time, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        Calendar calBegin = new GregorianCalendar();
        try {
            calBegin.setTime(format.parse(time));
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        long beginTime = calBegin.getTimeInMillis() / 1000;
        return beginTime;
    }

    /**
     * 获取到两个时间的中间月份
     *
     * @param y1
     * @param y2
     * @throws ParseException
     */
    public static List<String> getMonth(String y1, String y2) {
        List<String> list = new ArrayList<>();
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM").parse(y1);
            Date endDate = new SimpleDateFormat("yyyy-MM").parse(y2);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            // 获取开始年份和开始月份
            int startYear = calendar.get(Calendar.YEAR);
            int startMonth = calendar.get(Calendar.MONTH);
            // 获取结束年份和结束月份
            calendar.setTime(endDate);
            int endYear = calendar.get(Calendar.YEAR);
            int endMonth = calendar.get(Calendar.MONTH);
            //
            for (int i = startYear; i <= endYear; i++) {
                String date = "";
                if (startYear == endYear) {
                    for (int j = startMonth; j <= endMonth; j++) {
                        if (j < 9) {
                            date = i + "_0" + (j + 1);
                        } else {
                            date = i + "_" + (j + 1);
                        }
                        list.add(date);
                    }

                } else {
                    if (i == startYear) {
                        for (int j = startMonth; j < 12; j++) {
                            if (j < 9) {
                                date = i + "_0" + (j + 1);
                            } else {
                                date = i + "_" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else if (i == endYear) {
                        for (int j = 0; j <= endMonth; j++) {
                            if (j < 9) {
                                date = i + "_0" + (j + 1);
                            } else {
                                date = i + "_" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else {
                        for (int j = 0; j < 12; j++) {
                            if (j < 9) {
                                date = i + "_0" + (j + 1);
                            } else {
                                date = i + "_" + (j + 1);
                            }
                            list.add(date);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 与当前日期比较
     *
     * @param dateStr 比较时间
     * @return 1：大于当前日期，2：小于当前日期，3：等于当前日期
     */
   /* public static int diffNowDate(String dateStr) {
        try {
            Date dEndDate = new Date();
            if (!dateStr.contains(".") && !dateStr.contains("-")) {
                StringBuilder str = new StringBuilder(dateStr);
                str.insert(4, ".");
                str.insert(7, ".");
                dEndDate = DateUtil.parseDateDot(str.toString());
            } else if (dateStr.contains(".")) {
                dEndDate = DateUtil.parseDateDot(dateStr);
            } else if (dateStr.contains("-")) {
                dEndDate = DateUtil.parseDate(dateStr);
            }

            Date nowDate = DateUtil.getNowDate();
            if (dEndDate != null && nowDate != null) {
                if (dEndDate.getTime() > nowDate.getTime()) {
                    // 日期大于当前日期
                    return 1;
                } else if (dEndDate.getTime() < nowDate.getTime()) {
                    // 日期小于当前日期
                    return 2;
                } else { // dEndDate.getTime() == nowDate.getTime()
                    // 日期等于当前日期
                    return 3;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
*/
   /* public static Date parseDateDot(String datestr)
    {
        try
        {
            return new SimpleDateFormat(getDate_pattern_dot).parse(datestr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }*/

   /* public static Date parseDate(String datestr)
    {
        try
        {
            return new SimpleDateFormat(DATE).parse(datestr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }
*/
    /**
     * 取得当前日期
     *
     * @return Date:当前日期
     */
   /* public static Date getNowDate()
    {
        Date now = null;
        SimpleDateFormat dateFormat = null;
        try
        {
            dateFormat = new SimpleDateFormat(getDate_pattern_dot);
            now = dateFormat.parse(dateFormat.format(new Date()));
        }
        catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        return now;
    }
*/

    /**
     * 计算date2与date1相隔天数
     * <pre>
     *    date2         date1
     * 2017-08-05 与 2017-08-04 相隔    1 天
     * 2017-08-05 与 2017-08-05 相隔    0 天
     * 2017-08-05 与 2015-08-05 相隔  731 天
     * 2017-08-05 与 2017-08-06 相隔   -1 天
     * 2017-08-05 与 2019-08-05 相隔 -730 天
     * </pre>
     *
     * @param date1     较小日期
     * @param date2     较大日期
     * @param connector 日期格式的连接符："-", ".", "", "/"等
     * @return 相隔天数，date2小于date1时返回负数。<b>注意：格式解析失败将返回0</b>
     */
    /*public static long diffByDay(String date1, String date2, String connector) {
        StringBuilder sb = new StringBuilder(DATEPATTERN);
        sb.insert(4, connector);
        sb.insert(7, connector);
        String datePattern = sb.toString();
        Date d1 = parseDate(date1, datePattern);
        Date d2 = parseDate(date2, datePattern);
        long daysBetween = 0;
        if (d1 != null && d2 != null) {
            daysBetween = (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
        }
        return daysBetween;
    }
*/
    public static Date parseDate(String datestr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据秒 得到xx天xx时xx分xx秒
     *
     * @param mss
     * @return
     */
    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {
            DateTimes = days + "天" + hours + "小时" + minutes + "分钟"
                + seconds + "秒";
        } else if (hours > 0) {
            DateTimes = hours + "小时" + minutes + "分钟"
                + seconds + "秒";
        } else if (minutes > 0) {
            DateTimes = minutes + "分钟"
                + seconds + "秒";
        } else {
            DateTimes = seconds + "秒";
        }

        return DateTimes;
    }

//    public static void main(String[] args) {
//        System.out.println(diffByDay("2020-08-30","2020-09-08","-"));
//    System.out.println(getYestoday(DateUtil.DATE));
//    }

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
    public static String getYestoday(String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        return new SimpleDateFormat(pattern).format(time);
    }

    /**
     * @param pattern 指定日期格式
     * @return 当月第一天
     */
    public static String getFirstDayOfThisMonth(String pattern) {
        SimpleDateFormat myFormatter = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return myFormatter.format(cal.getTime());
    }

    /**
     * 某年某月某天
     *
     * @param pattern 指定日期格式
     * @return 某年某月某天
     */
    public static String anyDay(String pattern, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    /**
     * 获取任意月任意天
     *
     * @return String
     */
    public static String getAnyYesterday(String pattern, int day, int month) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        cal.add(Calendar.MONTH, month);
        Date time = cal.getTime();
        return new SimpleDateFormat(pattern).format(time);
    }


    /**
     * 获取指定日期月的最后一天 如2020-03-01 返回2020-03-31
     *
     * @param month 指定日期 2020-03-01
     * @return 传入日期月份的最后一天
     */
    public static String getLastDayOfMonth(String month) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("YYYY-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date nowDate = null;
        try {
            nowDate = myFormatter.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return myFormatter.format(calendar.getTime());
    }

    /**
     * 获取指定日期月的第一天 如2020-03-04 返回2020-03-01
     * @param month 指定日期 2020-03-04
     * @return 传入日期月份的第1天
     */
   /* public static String getFirstDayOfMonth(String month) {
        SimpleDateFormat myFormatter = new SimpleDateFormat(DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date nowDate= null;
        try {
            nowDate = myFormatter.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return myFormatter.format(calendar.getTime());}
*/

    /**
     * 获取月份
     *
     * @return String
     */
    public static String getLastMonth(String pattern, Integer month) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month); // 设置为上一个月
        String accDate = format.format(calendar.getTime());
        return accDate;
    }

    /**
     * 获取上个月的同一天
     *
     * @param dateStr
     * @return
     */
    public static Calendar getDateOfLastMonth(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return getDateOfLastMonth(c);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format(yyyy-MM-dd): " + dateStr);
        }
    }

    public static Calendar getDateOfLastMonth(Calendar date) {
        Calendar lastDate = (Calendar) date.clone();
        lastDate.add(Calendar.MONTH, -1);
        return lastDate;
    }

    /**
     * 计算两个日期相隔的天数
     *
     * @param firstString
     * @param secondString
     * @param dateFormat
     * @return
     */
    public static int daysBetweenTwoDate(String firstString, String secondString, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = df.parse(firstString);
            secondDate = df.parse(secondString);
        } catch (Exception e) {
            // 日期型字符串格式错误
            System.out.println("日期型字符串格式错误");
        }
        int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
        return nDay;
    }
}

